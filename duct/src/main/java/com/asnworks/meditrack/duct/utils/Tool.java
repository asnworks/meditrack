package com.asnworks.meditrack.duct.utils;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * @author Sudarsan
 * 
 *
 */
public abstract class Tool {

	protected HdfsTools hdfsTools;

	protected Configuration conf;

	protected FileSystem fs;

	public void run(String[] args) throws Exception {
		CmdLineParser parser = new CmdLineParser(this);
		try {
			System.out.println(args[0] + "      " + args[1]);
			parser.parseArgument(args);
		} catch (final CmdLineException e) {
			parser.printUsage(System.err);
			System.err.println("Invalid arguments for the command.");
			return;
		}

		run();
	}

	protected abstract void run() throws Exception;

	protected void configureFileSystem(String fileSystemName) throws IOException {
		hdfsTools = getHdfsTools(fileSystemName);
		conf = hdfsTools.getConfiguration();
		fs = FileSystem.get(conf);
	}

	private HdfsTools getHdfsTools(String fileSystemName) {
		if (fileSystemName != null) {
			return HdfsTools.forName(fileSystemName);
		}
		return HdfsTools.forLocalFileSystem();
	}

	protected String getFullOutputPath(Path path, String outputPath) {
		return new Path(outputPath, path.getName()).toString();
	}

}
