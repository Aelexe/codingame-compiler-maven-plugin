package com.aelchemy.maven.plugin.codingame.compiler.project;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import com.aelchemy.maven.plugin.codingame.compiler.TestUtil;
import com.aelchemy.maven.plugin.codingame.compiler.classs.ClassDTO;
import com.aelchemy.maven.plugin.codingame.compiler.project.ProjectCompiler;
import com.aelchemy.maven.plugin.codingame.compiler.project.ProjectDTO;

public class ProjectCompilerTest {

	@Test
	public void testCompileProject_OneClass() throws IOException, URISyntaxException {
		ClassDTO simpleClass = new ClassDTO();
		simpleClass.setName("SimpleClass");
		simpleClass.setCode(TestUtil.readResourceFile("/code_content/compressed/SimpleClass.java"));

		ProjectDTO project = new ProjectDTO();
		project.addClass(simpleClass);

		TestUtil.assertStringEquals(TestUtil.readResourceFile("/compiled/OneClass.java"), new ProjectCompiler(simpleClass, project).compileProject());
	}

	@Test
	public void testCompileProject_TwoImports() throws IOException, URISyntaxException {
		ClassDTO simpleClass = new ClassDTO();
		simpleClass.setName("SimpleClass");
		simpleClass.setCode(TestUtil.readResourceFile("/code_content/compressed/SimpleClass.java"));

		ClassDTO stringClass = new ClassDTO();
		stringClass.setPackage("string.util");
		stringClass.setName("StringClass");
		stringClass.setCode(TestUtil.readResourceFile("/code_content/compressed/StringClass.java"));

		ClassDTO packagedClass = new ClassDTO();
		packagedClass.setPackage("packaged");
		packagedClass.setName("PackagedClass");
		packagedClass.setCode(TestUtil.readResourceFile("/code_content/compressed/PackagedClass.java"));

		simpleClass.addImport("string.util.StringClass");
		simpleClass.addImport("packaged.PackagedClass");

		ProjectDTO project = new ProjectDTO();
		project.addClass(simpleClass);
		project.addClass(stringClass);
		project.addClass(packagedClass);

		TestUtil.assertStringEquals(TestUtil.readResourceFile("/compiled/TwoImports.java"), new ProjectCompiler(simpleClass, project).compileProject());
	}

	@Test
	public void testCompileProject_NestedImports() throws IOException, URISyntaxException {
		ClassDTO simpleClass = new ClassDTO();
		simpleClass.setName("SimpleClass");
		simpleClass.setCode(TestUtil.readResourceFile("/code_content/compressed/SimpleClass.java"));

		ClassDTO stringClass = new ClassDTO();
		stringClass.setPackage("string.util");
		stringClass.setName("StringClass");
		stringClass.setCode(TestUtil.readResourceFile("/code_content/compressed/StringClass.java"));

		ClassDTO packagedClass = new ClassDTO();
		packagedClass.setPackage("packaged");
		packagedClass.setName("PackagedClass");
		packagedClass.setCode(TestUtil.readResourceFile("/code_content/compressed/PackagedClass.java"));

		simpleClass.addImport("string.util.StringClass");
		stringClass.addImport("packaged.PackagedClass");

		ProjectDTO project = new ProjectDTO();
		project.addClass(simpleClass);
		project.addClass(stringClass);
		project.addClass(packagedClass);

		TestUtil.assertStringEquals(TestUtil.readResourceFile("/compiled/NestedImports.java"), new ProjectCompiler(simpleClass, project).compileProject());
	}
}
