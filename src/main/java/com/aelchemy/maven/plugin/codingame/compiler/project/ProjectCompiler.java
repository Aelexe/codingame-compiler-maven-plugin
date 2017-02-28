package com.aelchemy.maven.plugin.codingame.compiler.project;

import java.util.HashSet;
import java.util.Set;

import com.aelchemy.maven.plugin.codingame.compiler.java.JavaDTO;

/**
 * {@link ProjectCompiler} contains the functionality for compiling a {@link ProjectDTO} into a single .java file.
 * 
 * @author Aelexe
 *
 */
public class ProjectCompiler {

	private final ProjectDTO project;
	private final JavaDTO rootClass;
	private final StringBuilder compiledProject = new StringBuilder();
	/** List of classes that have already been included in the compilation. */
	private final Set<JavaDTO> includedClasses = new HashSet<JavaDTO>();

	public ProjectCompiler(final JavaDTO rootClass, final ProjectDTO project) {
		this.project = project;
		this.rootClass = rootClass;
	}

	/**
	 * Compiles the project using {@link ProjectCompiler#rootClass} as a base.
	 * 
	 * @return The compiled .java file representing the project.
	 */
	public String compileProject() {
		// Only compiled if it hasn't already been compiled.
		if (compiledProject.length() == 0) {
			appendRootClass();
		}

		return compiledProject.toString();
	}

	/**
	 * Appends the root class to the project compilation. Every import and their imports along with every class in the
	 * same package is included within the top level of the root class.
	 */
	private void appendRootClass() {
		// Add the class to the included classes.
		includedClasses.add(rootClass);

		// Apend the default imports.
		compiledProject.append("import java.util.*;\r\nimport java.io.*;\r\nimport java.math.*;\r\n");

		// Append the class opening and code to the compilation.
		compiledProject.append("class " + rootClass.getName() + " {\r\n" + rootClass.getCode());

		// Append new line between class code and import code.
		compiledProject.append("\r\n");

		// Append imports.
		for (String classImport : rootClass.getImports().toArray(new String[0])) {
			if (project.containsClass(classImport)) {
				JavaDTO importDto = project.getClasses().get(classImport);
				if (!includedClasses.contains(importDto)) {
					appendClass(importDto);
				}
			}
		}

		// Append classes in the same package (as they may be used without imports).
		for (JavaDTO packageClass : project.getClassesByPackage().get(rootClass.getPackage())) {
			if (!includedClasses.contains(packageClass)) {
				appendClass(packageClass);
			}
		}

		// Append the closing brace.
		compiledProject.append("}\r\n");
	}

	/**
	 * Appends the {@link JavaDTO} to the compilation, followed by any of its imports.
	 * 
	 * @param javaDto The {@link JavaDTO} to append to the compilation.
	 */
	private void appendClass(final JavaDTO javaDto) {
		// Add the class to the included classes and append the class opening and code to the compilation.
		includedClasses.add(javaDto);
		if (javaDto.isInterface()) {
			compiledProject.append("private static interface " + javaDto.getName());
		} else {
			compiledProject.append("private static class " + javaDto.getName());
			if (javaDto.implementsInterface()) {
				compiledProject.append(" implements " + javaDto.getInterface());
			}
		}
		compiledProject.append(" {\r\n" + javaDto.getCode());

		// Append new line between class code and import code.
		compiledProject.append("\r\n");

		// Append the closing brace.
		compiledProject.append("}\r\n");

		// Append imports.
		for (String classImport : javaDto.getImports().toArray(new String[0])) {
			if (project.containsClass(classImport)) {
				JavaDTO importDto = project.getClasses().get(classImport);
				if (!includedClasses.contains(importDto)) {
					appendClass(importDto);
				}
			}
		}

		// Append classes in the same package (as they may be used without imports).
		for (JavaDTO packageClass : project.getClassesByPackage().get(javaDto.getPackage())) {
			if (!includedClasses.contains(packageClass)) {
				appendClass(packageClass);
			}
		}
	}

}
