package com.aelchemy.maven.plugin.codingame.compiler.project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.aelchemy.maven.plugin.codingame.compiler.classs.ClassDTO;
import com.aelchemy.maven.plugin.codingame.compiler.classs.ClassParser;
import com.aelchemy.maven.plugin.codingame.compiler.mojo.MojoLog;

public class ProjectParser {

	private ProjectParser() {

	}

	public static ProjectDTO parseProject(final File projectRoot) throws IOException {
		ProjectDTO project = new ProjectDTO();

		MojoLog.subheaderInfo("Crawling .java files from path: " + projectRoot.getAbsolutePath());
		List<File> javaFiles = crawlForJava(projectRoot);
		MojoLog.info(javaFiles);
		MojoLog.breakLine();

		MojoLog.subheaderInfo("Parsing class files");
		for (File javaFile : javaFiles) {
			ClassDTO classDto = ClassParser.parseClass(FileUtils.readFileToString(javaFile, "UTF-8"));
			project.addClass(classDto);
		}
		MojoLog.info("Complete.");
		MojoLog.breakLine();

		return project;
	}

	private static List<File> crawlForJava(final File projectRoot) {
		return recursiveCrawlForJava(projectRoot, new ArrayList<File>());
	}

	private static List<File> recursiveCrawlForJava(final File current, final List<File> javaFiles) {
		if (current.isDirectory()) {
			File[] files = current.listFiles();
			for (File file : files) {
				recursiveCrawlForJava(file, javaFiles);
			}
		} else {
			if (current.getName().endsWith(".java")) {
				javaFiles.add(current);
			}
		}
		return javaFiles;
	}
}
