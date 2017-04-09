package com.asnworks.meditrack.duct.utils;



import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.security.AccessControlException;

import com.google.common.collect.Lists;

/**
 * File utilities that work both on local as on HDFS file system.
 */
public final class HdfsTools {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    private final Configuration conf;

    private HdfsTools(Configuration conf) {
        this.conf = conf;
    }

    /**
     * Factory method to get {@link HdfsTools} for the default configuration. This is the method that will give tools for the local file
     * system when running locally or for the HDFS when running on the cluster.
     * 
     * @return {@link HdfsTools} with a default configuration (file or hdfs).
     */
    public static HdfsTools forDefaultFileSystem() {
        return forConfiguration(new Configuration());
    }

    /**
     * Factory method to get {@link HdfsTools} for the local file system.
     * 
     * @return {@link HdfsTools} that acts on local file system.
     */
    public static HdfsTools forLocalFileSystem() {
        return forName("file:///");
    }

    /**
     * Factory method to get {@link HdfsTools} for the HDFS described by the Hadoop configuration files in the specified directory.
     * 
     * @param hadoopConfDir path to directory containing the hadoop config xml files.
     * @return {@link HdfsTools} that acts on HDFS defined by configuration files in hadoopConfDir.
     */
    public static HdfsTools forHdfs(String hadoopConfDir) {
        Configuration conf = new Configuration();
        conf.addResource(new Path(hadoopConfDir, "core-site.xml"));
        conf.addResource(new Path(hadoopConfDir, "hdfs-site.xml"));
        return forConfiguration(conf);
    }

    /**
     * Factory method to get {@link HdfsTools} for the file system described by the specified name, which is a URL containting the protocol,
     * host and port number for the requested file system. For a HDFS file system, the name should start with hdfs://.
     * 
     * @param fileSystemName the URL describing the file system
     * @return {@link HdfsTools} that acts on the file system defined by the specified file system name.
     */
    public static HdfsTools forName(String fileSystemName) {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", fileSystemName);
        return forConfiguration(conf);
    }

    /**
     * Factory method to get {@link HdfsTools} for the file system specified in the configuration.
     * 
     * @param configuration configuration
     * @return {@link HdfsTools} that acts on the filesystem defined by the configuration
     */
    public static HdfsTools forConfiguration(Configuration configuration) {
        return new HdfsTools(configuration);
    }

    /**
     * Returns the configuration that is used to find the file system for this object. This is a copy of the configuration, so changes made
     * to the returned configuration have no effect on the {@link HdfsTools} instance.
     * 
     * @return the configuration used by this instance
     */
    public Configuration getConfiguration() {
        return new Configuration(conf);
    }

    /**
     * Get {@link OutputStream} to the file specified by the path. If the parent directory of path is missing, this will be created.
     * 
     * @param path file for which an output stream will be returned.
     * @return {@link OutputStream}
     * @throws IOException when the file could not be found or opened
     */
    public OutputStream getOutputStream(String path) throws IOException {
        Path pt = new Path(path);
        FileSystem fs = getFileSystem();
        fs.mkdirs(pt.getParent());
        return fs.create(pt, true);
    }

    /**
     * Get {@link BufferedWriter} to the file specified by the path. If the parent directory of path is missing, this will be created.
     * 
     * @param path path to the file to write to
     * @return a {@link BufferedWriter} to the file
     * @throws IOException when the file could not be found or opened
     */
    public BufferedWriter getWriter(String path) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(getOutputStream(path), UTF8));
    }

    /**
     * Get {@link OutputStream} to the file specified by the path. If the parent directory of path is missing, this will be created.
     * 
     * @param path file for which an output stream will be returned.
     * @return {@link OutputStream}
     * @throws IOException when the file could not be found or opened
     */
    public OutputStream getOutputStreamForAppend(String path) throws IOException {
        Path pt = new Path(path);
        FileSystem fs = getFileSystem();
        if (fs.exists(pt)) {
            return fs.append(pt);
        } else {
            fs.mkdirs(pt.getParent());
            return fs.create(pt, true);
        }
    }

    /**
     * Get {@link BufferedWriter} to the file specified by the path. If the parent directory of path is missing, this will be created.
     * 
     * @param path path to the file to append to
     * @return a {@link BufferedWriter} to the file
     * @throws IOException when the file could not be found or opened
     */
    public BufferedWriter getAppender(String path) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(getOutputStreamForAppend(path), UTF8));
    }

    /**
     * Returns an {@link InputStream} to read the file specified by the path.
     * 
     * @param path path to the file to read
     * @return an {@link InputStream} to the file
     * @throws IOException when the file could not be found or opened from the file system
     */
    public InputStream getInputStream(String path) throws IOException {
        FileSystem fs = getFileSystem();
        return fs.open(new Path(path));
    }

    /**
     * Returns a reader to read the file specified by the path.
     * 
     * @param path path to the file to read
     * @return a {@link Reader} to the file
     * @throws IOException when the file could not be found or opened from the file system
     */
    public Reader getReader(String path) throws IOException {
        return new InputStreamReader(getInputStream(path), UTF8);
    }

    /**
     * Make the given file and all non-existent parents into directories.
     * 
     * @param path
     * @throws IOException
     */
    public void mkdirs(String path) throws IOException {
        mkdirs(new Path(path));
    }

    /**
     * Make the given file and all non-existent parents into directories.
     * 
     * @param path
     * @throws IOException
     */
    public void mkdirs(Path path) throws IOException {
        FileSystem fs = getFileSystem();
        fs.mkdirs(path);
    }

    /**
     * Renames src to dst. Can take place on local fs or remote DFS.
     * 
     * @param srcPath
     * @param dstPath
     * @throws IOException
     */
    public void rename(String srcPath, String dstPath) throws IOException {
        FileSystem fs = getFileSystem();
        fs.rename(new Path(srcPath), new Path(dstPath));
    }

    /**
     * Create file, existing files will be overwritten.
     * 
     * @param path
     * @throws IOException
     */
    public void createFile(String path) throws IOException {
        Path pt = new Path(path);
        createFile(pt);
    }

    /**
     * Create file, existing files will be overwritten.
     * 
     * @param path
     * @throws IOException
     */
    public void createFile(Path path) throws IOException {
        FileSystem fs = getFileSystem();

        fs.create(path);
        fs.close();
    }

    /**
     * Get list of all files in given path that comply to regex.
     * 
     * @param path directory for which to list the files.
     * @param regex regular expression the file names must comply to.
     * @return Collection of file names in path that comply to regex. Empty collection if no complying files found.
     * @throws IOException
     */
    public List<String> listFiles(String path, final String regex) throws IOException {
        return list(path, regex, ListOptions.FILES);
    }

    /**
     * Get list of all directories in given path that comply to regex.
     * 
     * @param path directory for which to list the files and directories.
     * @param regex regular expression the file /directory must comply to.
     * @return Collection of file and directory names in path that comply to regex. Empty collection if no complying files found.
     * @throws IOException
     */
    public List<String> listDirectories(String path, final String regex) throws IOException {
        return list(path, regex, ListOptions.DIRECTORIES);
    }

    /**
     * Get list of all files and directories in given path that comply to regex.
     * 
     * @param path directory for which to list the files and directories.
     * @param regex regular expression the file /directory must comply to.
     * @return Collection of file and directory names in path that comply to regex. Empty collection if no complying files found.
     * @throws IOException
     */
    public List<String> listAll(String path, final String regex) throws IOException {
        return list(path, regex, ListOptions.ALL);
    }

    /**
     * Get list of all files and/or directories in given path that comply to regex.
     * 
     * @param path directory for which to list the files and directories.
     * @param regex regular expression the file /directory must comply to.
     * @param options the {@link ListOptions} that determines which type of files are returned
     * @return Collection of file and directory names in path that comply to regex. Empty collection if no complying files found.
     * @throws IOException
     */
    public List<String> list(String path, final String regex, ListOptions options) throws IOException {

        FileSystem fs = getFileSystem();

        Path pt = new Path(path);
        PathFilter filter = new PathFilter() {

            public boolean accept(Path pathToFilter) throws PatternSyntaxException {
                return pathToFilter.getName().matches(regex);
            }
        };

        FileStatus[] status;
        try {
            status = fs.listStatus(pt, filter);
        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }

        List<String> fileList = new ArrayList<String>();
        for (FileStatus fileStatus : status) {
            if (ListOptions.DIRECTORIES.equals(options) && fileStatus.isDirectory() ||
                ListOptions.FILES.equals(options) && !fileStatus.isDirectory() ||
                ListOptions.ALL.equals(options)) {
                fileList.add(fileStatus.getPath().getName());
            }
        }

        if (fileList.isEmpty()) {
            return Collections.emptyList();
        }

        return fileList;
    }

    /**
     * This method returns if the given path is a file or not
     * 
     * @param path -path of directory or file
     * @return -true if the given path is a file
     * @throws IOException
     */
    public boolean isFile(String path) throws IOException {
        FileSystem fs = getFileSystem();
        Path pt = new Path(path);
        return fs.isFile(pt);

    }

    /**
     * This method returns the FileStatus list
     * 
     * @param path
     * @return
     * @throws IOException
     */
    public List<FileStatus> listStatus(Path path) throws IOException {

        FileSystem fs = getFileSystem();
        FileStatus[] status = null;
        try {
            status = fs.listStatus(path);
        } catch (FileNotFoundException e) {
            return Collections.emptyList();
        }

        return Lists.newArrayList(status);

    }

    /**
     * Read the lines in a file.
     * 
     * @param path path to the file to read
     * @return the list of Strings representing each line in the file, never <code>null</code>
     * @throws IOException when the file system cannot be accessed or when the file could not be opened
     */
    public List<String> readLines(String path) throws IOException {
        Reader reader = getReader(path);
        try {
            return IOUtils.readLines(reader);
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    /**
     * Delete path recursively.
     * 
     * @param path
     * @throws IOException
     */
    public void deletePathRecursive(String path) throws IOException {
        Path pt = new Path(path);
        deletePathRecursive(pt);
    }

    /**
     * Delete path recursively.
     * 
     * @param path
     * @throws IOException
     */
    public void deletePathRecursive(Path path) throws IOException {
        FileSystem fs = getFileSystem();
        fs.delete(path, true);
    }

    /**
     * Delete path
     * 
     * @param path
     * @throws IOException
     */
    public void delete(String path) throws IOException {
        FileSystem fs = getFileSystem();
        Path pt = new Path(path);
        fs.delete(pt, false);
    }

    /**
     * Returns {@code true} if and only if the file with the specified path exists.
     * 
     * @param path path
     * @return {@code true} if the file exists, {@code false} otherwise
     * @throws IOException when the file system cannot be accessed
     */
    public boolean exists(String path) throws IOException {
        return exists(new Path(path));
    }

    public boolean exists(Path path) throws IOException {
        FileSystem fs = getFileSystem();
        return fs.exists(path);
    }

    /**
     * Returns {@code true} if the specified file path can be read, i.e. if the user has read permissions on this file. Returns
     * {@code false} if it can't be read.
     * 
     * @param path path to a file
     * @return {@code true} if the file can be read, {@code false} otherwise
     * @throws IOException when the path is not a file or if the path doesn't exist or if some other I/O related problem occurs
     */
    public boolean isReadable(String path) throws IOException {
        if (!exists(path)) {
            throw new IOException(String.format("Path %s does not exist", path));
        }
        if (!isFile(path)) {
            throw new IOException(String.format("Path %s is not a file", path));
        }
        // just reads from the path to see if we are allowed to
        InputStream inputStream = null;
        try {
            inputStream = getInputStream(path.toString());
        } catch (AccessControlException e) {
            return false;
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return true;
    }

    /**
     * Returns {@code true} if the specified directory path can be written to, i.e. if the user has write permissions on this directory.
     * Returns {@code false} if it can't be written to.
     * <p>
     * Implementation note: This method tests the directory write permissions by creating a file in the directory to chek if it is allowed.
     * 
     * @param path path to a directory
     * @return {@code true} if the directory can be read, {@code false} otherwise
     * @throws IOException when the path is not a directory or if the path doesn't exist or if some other I/O related problem occurs
     */
    public boolean isWritable(String path) throws IOException {
        if (!exists(path)) {
            throw new IOException(String.format("Path %s does not exist", path));
        }
        if (isFile(path)) {
            throw new IOException(String.format("Path %s is not a directory", path));
        }
        // just try to create this file to see if we are allowed to
        // what are that odds that this file exists already?
        String tryFilePath = new Path(path, ".try" + System.currentTimeMillis()).toString();
        OutputStream outputStream = null;
        try {
            outputStream = getOutputStream(tryFilePath.toString());
        } catch (AccessControlException e) {
            return false;
        } finally {
            IOUtils.closeQuietly(outputStream);
            if (exists(tryFilePath)) {
                delete(tryFilePath);
            }
        }
        return true;
    }

    /**
     * Copy file from local srcPath to HDFS dstPath.
     * 
     * @param srcPath path to local file
     * @param dstPath path to HDFS destination
     * @throws IOException
     */
    public void uploadToHdfs(String srcPath, String dstPath) throws IOException {
        FileSystem fs = getFileSystem();
        fs.copyFromLocalFile(new Path(srcPath), new Path(dstPath));
    }

    /**
     * Copy file from local srcPath to HDFS dstPath with given replication factor
     * 
     * @param srcPath path to local file
     * @param dstPath path to HDFS destination
     * @throws IOException
     */
    public void uploadToHdfs(String srcPath, String dstPath, int replication) throws IOException {
        FileSystem fs = getFileSystem();
        Path hdfsPath = new Path(dstPath);
        fs.copyFromLocalFile(new Path(srcPath), hdfsPath);
        fs.setReplication(hdfsPath, (short)replication);
    }

    public FileStatus getFileStatus(String path) throws IOException {
        FileSystem fs = getFileSystem();
        Path hdfsPath = new Path(path);
        return fs.getFileStatus(hdfsPath);
    }

    private FileSystem getFileSystem() throws IOException {
        return FileSystem.get(conf);
    }

    public enum ListOptions {
        FILES,
        DIRECTORIES,
        ALL
    }

    public void downloadFromHdfs(String srcPath, String dstPath) throws IOException {

        FileSystem fs = getFileSystem();
        fs.copyToLocalFile(new Path(srcPath), new Path(dstPath));
    }

    /**
     * Copy all content from the source path to the destination path, replicating the directory structure. The source path is retained.
     * 
     * @param src
     * @param dst
     * @throws IOException
     */

    public void copyFolderContent(Path src, Path dst) throws IOException {
        FileSystem fs = getFileSystem();
        copyFolderContent(fs, src, dst);

    }

    private void copyFolderContent(FileSystem fs, Path src, Path dst) throws IOException {

        FileStatus[] files;
        try {
            files = fs.listStatus(src);
        } catch (FileNotFoundException e) {
            return;
        }
        if (files != null) {
            for (FileStatus f : files) {
                if (f.isDirectory()) {
                    Path newDst = new Path(dst, f.getPath().getName());

                    if (!fs.exists(newDst)) {
                        fs.mkdirs(newDst);
                    }
                    copyFolderContent(fs, f.getPath(), newDst);
                } else {
                    Path newDstFile = new Path(dst, f.getPath().getName());
                    Path SrcFile = new Path(src, f.getPath().getName());
                    if (!fs.exists(dst)) {
                        fs.mkdirs(dst);
                    } else if (!fs.getFileStatus(dst).isDirectory()) {
                        throw new IOException("Unable to copy file, parent destination is a file; " + f.getPath() + " to " + newDstFile);
                    }
                    FileUtil.copy(getFileSystem(), SrcFile, getFileSystem(), newDstFile, false, getConfiguration());

                }
            }
        }

    }

    /**
     * Method to copy file from src path to destination path
     * 
     * @param fs
     * @param src
     * @param dst
     * @throws IOException
     */
    public void copyFile(String src, String dst) throws IOException {
        FileSystem fs = getFileSystem();
        copyFile(fs, new Path(src), new Path(dst));
    }

    /**
     * Move all content from the source path to the destination path, replicating the directory structure. The source path is removed.
     * 
     * @param src
     * @param dst
     * @throws IOException
     */
    public void moveFolderContent(Path src, Path dst) throws IOException {
        FileSystem fs = getFileSystem();
        moveFolderContent(fs, src, dst);
        deletePathRecursive(src);
    }

    private void moveFolderContent(FileSystem fs, Path src, Path dst) throws IOException {
        FileStatus[] files;
        try {
            files = fs.listStatus(src);
        } catch (FileNotFoundException e) {
            return;
        }
        if (files != null) {
            for (FileStatus f : files) {
                if (f.isDirectory()) {
                    Path newDst = new Path(dst, f.getPath().getName());
                    if (!fs.exists(newDst)) {
                        fs.mkdirs(newDst);
                    }
                    moveFolderContent(fs, f.getPath(), newDst);
                } else {
                    Path newDstFile = new Path(dst, f.getPath().getName());
                    if (fs.exists(newDstFile)) {
                        if (!fs.delete(newDstFile, false)) {
                            throw new IOException("Unable to delete file before rename; " + newDstFile);
                        }
                    } else {
                        if (!fs.exists(dst)) {
                            fs.mkdirs(dst);
                        } else if (!fs.getFileStatus(dst).isDirectory()) {
                            throw new IOException("Unable to move file, parent destination is a file; " + f.getPath() + " to " + newDstFile);
                        }
                    }

                    if (!fs.rename(f.getPath(), newDstFile)) {
                        throw new IOException("Unable to move file; " + f.getPath() + " to " + newDstFile);
                    }
                }
            }
        }
    }

    /**
     * Method to copy file from src path to destination path
     * 
     * @param fs
     * @param src
     * @param dst
     * @throws IOException
     */
    private void copyFile(FileSystem fs, Path src, Path dst) throws IOException {
        FileUtil.copy(fs, src, fs, dst, false, getConfiguration());
    }
}
