package com.aelchemy.maven.plugin.codingame.javacompiler.project;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import com.aelchemy.maven.plugin.codingame.javacompiler.TestUtil;

public class ProjectParserTest {

	@Test
	public void testParseProject_Base() throws URISyntaxException, IOException {
		assertProjectContainsClasses(ProjectParser.parseProject(TestUtil.getResourceFile("/base")), "ImportClass", "com.aelchemy.javamapper.PackagedClass", "SimpleClass",
				"StringClass");
	}

	@Test
	public void testParseProject_Sanitised() throws URISyntaxException, IOException {
		assertProjectContainsClasses(ProjectParser.parseProject(TestUtil.getResourceFile("/comment_sanitised")), "SimpleClass", "StringClass");
	}

	/**
	 * Asserts a {@link ProjectDTO} contains the expected fully qualified .java class names.
	 * 
	 * @param project {@link ProjectDTO} to check.
	 * @param fullNames Expected fully qualified .java class names.
	 */
	private void assertProjectContainsClasses(final ProjectDTO project, final String... fullNames) {
		for (String fullName : fullNames) {
			assertTrue("Project did not contain class: " + fullName, project.containsClass(fullName));
		}
	}
}
