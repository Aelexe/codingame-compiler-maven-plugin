package com.aelchemy.maven.plugin.codingame.compiler.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aelchemy.maven.plugin.codingame.compiler.java.JavaDTO;

/**
 * {@link ProjectDTO} is a DTO containing a map of {@link JavaDTO}s representing all .java classes contained within a project.
 * 
 * @author Aelexe
 *
 */
public class ProjectDTO {

	private final Map<String, JavaDTO> classes = new HashMap<String, JavaDTO>();
	private final Map<String, List<JavaDTO>> classesByPackage = new HashMap<String, List<JavaDTO>>();

	/**
	 * Adds a {@link JavaDTO} to the map of classes and the map of classes by package.
	 * 
	 * @param javaDto {@link JavaDTO} to add to the maps.
	 */
	public void addClass(final JavaDTO javaDto) {
		classes.put(javaDto.getFullName(), javaDto);
		if (!classesByPackage.containsKey(javaDto.getPackage())) {
			classesByPackage.put(javaDto.getPackage(), new ArrayList<JavaDTO>());
		}
		classesByPackage.get(javaDto.getPackage()).add(javaDto);
	}

	/**
	 * Returns a boolean indicating where the class map contains the fully qualified class name.
	 * 
	 * @param fullName Fully qualified class name to check the map for.
	 * @return Boolean indicating whether the map contains the class.
	 */
	public boolean containsClass(final String fullName) {
		return classes.containsKey(fullName);
	}

	public Map<String, JavaDTO> getClasses() {
		return classes;
	}

	public Map<String, List<JavaDTO>> getClassesByPackage() {
		return classesByPackage;
	}

	/**
	 * Returns a list of all {@link JavaDTO} containing a main method.
	 * 
	 * @return A list of all {@link JavaDTO} containing a main method.
	 */
	public List<JavaDTO> getMainClasses() {
		List<JavaDTO> mainClasses = new ArrayList<JavaDTO>();

		for (JavaDTO mainClass : classes.values()) {
			mainClasses.add(mainClass);
		}

		return mainClasses;
	}
}
