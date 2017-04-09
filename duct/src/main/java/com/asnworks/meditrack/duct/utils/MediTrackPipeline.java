package com.asnworks.meditrack.duct.utils;

import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.hadoop.fs.Path;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import com.asnworks.meditrack.model.Patient;

public class MediTrackPipeline extends Tool {

	@Argument(required = true, usage = "Output path for fallout and success files")
	private String outputPath;

	@Option(required = false, name = "-fs", aliases = "--fileSystem", usage = "The name of the HDFS file system to use. If not specified, the local file system will be used for the input and output.")
	private String fileSystemName;

	private DataFileWriter<Patient> patientWriter = null;
	private File patientArchiveFile = null;
	private String patientArchivePath = null;

	@Override
	protected void run() throws IOException {

		System.out.println("Intake-To-Avro Tool started running");
		configureFileSystem(fileSystemName);
		initOutputFiles();
		doWork();
		System.out.println("Uploading files to HDFS");
		upload();

	}

	private void initOutputFiles() {
		patientArchiveFile = new File("patients.avro");

		patientArchivePath = getFullOutputPath(new Path(patientArchiveFile.getName()), outputPath);
	}

	private void doWork() throws IOException {
		Patient patient = new Patient(111, "AAA", "A", 21, "Male", "Address", "9591543252", "email@email.com");
		Patient patient1 = new Patient(222, "BBB", "B", 22, "Male", "Address1", "9591543253", "email1@email.com");
		
		patientWriter = new DataFileWriter<Patient>(new SpecificDatumWriter<Patient>(Patient.class));
		patientWriter.create(patient.getSchema(), patientArchiveFile);
		
		patientWriter.append(patient);
		patientWriter.append(patient1);

		patientWriter.flush();
		patientWriter.close();

	}

	private void upload() throws IOException {

		if (patientArchiveFile.exists() && patientArchiveFile.isFile()) {
			hdfsTools.uploadToHdfs(patientArchiveFile.getName(), patientArchivePath);
		}

	}

}
