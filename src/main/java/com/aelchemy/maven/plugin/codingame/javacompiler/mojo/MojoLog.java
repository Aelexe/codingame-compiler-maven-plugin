package com.aelchemy.maven.plugin.codingame.javacompiler.mojo;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.logging.Log;

public class MojoLog {

	private static final String HEADER_BREAK = "------------------------------------------------------------------------";
	private static final String SUBHEADER_PREFIX = "---";

	private static Log log;

	private MojoLog() {

	}

	public static void setLog(final Log log) {
		MojoLog.log = log;
	}

	public static void info(final String message) {
		if (log != null) {
			log.info(message);
		}
	}

	public static void info(final List<File> files) {
		if (log != null) {
			StringBuilder message = new StringBuilder();
			for (int i = 0; i < files.size(); i++) {
				message.append(files.get(i).getName());
				if (i < files.size() - 1) {
					message.append(", ");
				}
			}

			log.info(message.toString());
		}
	}

	public static void headerInfo(final String message) {
		if (log != null) {
			log.info(HEADER_BREAK);
			log.info(message);
			log.info(HEADER_BREAK);
		}
	}

	public static void subheaderInfo(final String message) {
		if (log != null) {
			log.info(SUBHEADER_PREFIX + " " + message + " " + SUBHEADER_PREFIX);
		}
	}

	public static void error(final String message) {
		if (log != null) {
			log.error(message);
		}
	}

	public static void breakLine() {
		if (log != null) {
			log.info("");
		}
	}

}
