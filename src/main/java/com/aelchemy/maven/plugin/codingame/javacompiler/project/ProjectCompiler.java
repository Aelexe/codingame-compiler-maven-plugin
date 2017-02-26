package com.aelchemy.maven.plugin.codingame.javacompiler.project;

import java.util.HashSet;
import java.util.Set;

import com.aelchemy.maven.plugin.codingame.javacompiler.classs.ClassDTO;

public class ProjectCompiler {

	public static void main(final String[] args) {
	}

	private final ProjectDTO project;
	private final ClassDTO rootClass;
	private final StringBuilder compiledProject = new StringBuilder();
	private final Set<ClassDTO> includedClasses = new HashSet<ClassDTO>();

	public ProjectCompiler(final ClassDTO rootClass, final ProjectDTO project) {
		this.project = project;
		this.rootClass = rootClass;
	}

	public String compileProject() {
		if (compiledProject.length() == 0) {
			appendClass(rootClass, 0);
		}

		return compiledProject.toString();
	}

	private void appendClass(final ClassDTO classDto, final int depth) {
		// Add the class to the included classes and append the class opening and code to the compilation.
		includedClasses.add(classDto);

		if (depth == 0) {
			compiledProject.append("class " + classDto.getName() + " {\r\n" + classDto.getCode());
		} else {
			compiledProject.append("private static class " + classDto.getName() + " {\r\n" + classDto.getCode());
		}

		// Append new line between class code and import code.
		compiledProject.append("\r\n");

		// Append imports.
		for (String classImport : classDto.getImports().toArray(new String[0])) {
			if (project.containsClass(classImport)) {
				ClassDTO importDto = project.getClasses().get(classImport);
				if (!includedClasses.contains(importDto)) {
					appendClass(importDto, depth + 1);
				}
			}
		}

		// Append classes in the same package (as they may be used without imports).
		for (ClassDTO packageClass : project.getClassesByPackage().get(classDto.getPackage())) {
			if (!includedClasses.contains(packageClass)) {
				appendClass(packageClass, depth + 1);
			}
		}

		// Append the closing brace.
		compiledProject.append("}\r\n");
	}

}
