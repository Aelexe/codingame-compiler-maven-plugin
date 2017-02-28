package com.aelchemy.maven.plugin.codingame.compiler.mojo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.aelchemy.maven.plugin.codingame.compiler.java.JavaDTO;
import com.aelchemy.maven.plugin.codingame.compiler.project.ProjectCompiler;
import com.aelchemy.maven.plugin.codingame.compiler.project.ProjectDTO;
import com.aelchemy.maven.plugin.codingame.compiler.project.ProjectParser;

@Mojo(name = "cg-compile", defaultPhase = LifecyclePhase.COMPILE)
public class CompileMojo extends AbstractMojo {

	@Parameter(defaultValue = "${project.name}", readonly = true, required = true)
	private String projectName = "";

	@Parameter(defaultValue = "${project.build.sourceDirectory}", readonly = true, required = true)
	private final File sourceDirectory = new File("");

	@Parameter(defaultValue = "${project.build.directory}", readonly = true, required = true)
	private final File buildDirectory = new File("");

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		MojoLog.setLog(getLog());

		MojoLog.headerInfo("CodinGame compiling " + projectName);
		MojoLog.breakLine();

		ProjectDTO project;
		try {
			project = ProjectParser.parseProject(sourceDirectory);
		} catch (IOException e) {
			MojoLog.error("Unable to parse project from source directory: " + sourceDirectory.getAbsolutePath());
			return;
		}

		for (JavaDTO mainClass : project.getMainClasses()) {
			if (mainClass.containsMainMethod()) {
				MojoLog.subheaderInfo("Compiling for " + mainClass.getName());
				String compiledCode = new ProjectCompiler(mainClass, project).compileProject();
				File compiledFile = new File(buildDirectory.getAbsolutePath() + "\\cg-compile\\" + mainClass.getName() + ".java");
				compiledFile.getParentFile().mkdirs();
				try {
					FileUtils.writeStringToFile(compiledFile, compiledCode, "UTF-8");
					MojoLog.error("Compiled code written to: " + compiledFile.getAbsolutePath());
				} catch (IOException e) {
					e.printStackTrace();
					MojoLog.error("Unable to write compiled code to file: " + compiledFile.getAbsolutePath());
				}
			}
		}
	}

}
