package com.aelchemy.maven.plugin.codingame.javacompiler.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aelchemy.maven.plugin.codingame.javacompiler.classs.ClassDTO;

/**
 * {@link ProjectDTO} is a DTO containing a map of {@link ClassDTO}s representing all .java classes contained within a project.
 * 
 * @author Aelexe
 *
 */
public class ProjectDTO {

	private final Map<String, ClassDTO> classes = new HashMap<String, ClassDTO>();
	private final Map<String, List<ClassDTO>> classesByPackage = new HashMap<String, List<ClassDTO>>();

	/**
	 * Adds a {@link ClassDTO} to the map of classes and the map of classes by package.
	 * 
	 * @param classDto {@link ClassDTO} to add to the maps.
	 */
	public void addClass(final ClassDTO classDto) {
		classes.put(classDto.getFullName(), classDto);
		if (!classesByPackage.containsKey(classDto.getPackage())) {
			classesByPackage.put(classDto.getPackage(), new ArrayList<ClassDTO>());
		}
		classesByPackage.get(classDto.getPackage()).add(classDto);
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

	public Map<String, ClassDTO> getClasses() {
		return classes;
	}

	public Map<String, List<ClassDTO>> getClassesByPackage() {
		return classesByPackage;
	}

	/**
	 * Returns a list of all {@link ClassDTO} containing a main method.
	 * 
	 * @return A list of all {@link ClassDTO} containing a main method.
	 */
	public List<ClassDTO> getMainClasses() {
		List<ClassDTO> mainClasses = new ArrayList<ClassDTO>();

		for (ClassDTO mainClass : classes.values()) {
			mainClasses.add(mainClass);
		}

		return mainClasses;
	}
}
