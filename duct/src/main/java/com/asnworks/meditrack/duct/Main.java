package com.asnworks.meditrack.duct;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import com.asnworks.meditrack.duct.utils.MediTrackPipeline;
import com.asnworks.meditrack.duct.utils.Tool;

import avro.shaded.com.google.common.collect.ImmutableMap;

/**
 * Main class for the tools. 
 * 
 * Command to run
 * 
 * duct -fs hdfs://192.168.49.128:8020 /user/cloudera/meditrack/artifact-repository/ARCHIVE/<>
 */
public class Main {

	private static final Map<String, Tool> TOOLS = ImmutableMap.<String, Tool>builder()
			.put("duct", new MediTrackPipeline()).build();

	/**
	 * The first argument is the command to run. The other arguments are
	 * arguments specific for that command.
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		try {
			new Main().run(args);
		} catch (Exception e) {
			System.out.println("Exception running tool" + e);
			e.printStackTrace();
		}
	}

	private void run(String[] args) throws Exception {

		if (args.length == 0) {
			System.err.println("Invalid run arguments: " + Arrays.toString(args));
		}

		String command = args[0];
		String[] toolArgs = ArrayUtils.subarray(args, 1, args.length);

		Tool tool = TOOLS.get(command);
		if (tool == null) {
			System.err.println(String.format("Command %s doesn't exist.", command));
			return;
		}

		System.out.println(String.format("Running %s with arguments %s.", command, Arrays.toString(toolArgs)));

		tool.run(toolArgs);
	}

}