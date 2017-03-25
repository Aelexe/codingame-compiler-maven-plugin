package com.aelchemy.maven.plugin.codingame.compiler.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import com.aelchemy.maven.plugin.codingame.compiler.TestUtil;

public class JavaParserTest {

	@Test
	public void testParseClass_SimpleClass() throws IOException, URISyntaxException {
		JavaDTO simpleClass = JavaParser.parseClass(TestUtil.readResourceFile("/base/SimpleClass.java"));

		assertEquals("SimpleClass", simpleClass.getName());
		assertEquals("SimpleClass", simpleClass.getFullName());
		assertTrue(simpleClass.getImports().isEmpty());
		TestUtil.assertStringEquals(TestUtil.readResourceFile("/code_content/compressed/SimpleClass.java"), simpleClass.getCode());
		assertFalse(simpleClass.containsMainMethod());
	}

	@Test
	public void testParseClass_StringClass() throws IOException, URISyntaxException {
		JavaDTO stringClass = JavaParser.parseClass(TestUtil.readResourceFile("/base/StringClass.java"));

		assertEquals("StringClass", stringClass.getName());
		assertEquals("StringClass", stringClass.getFullName());
		assertTrue(stringClass.getImports().isEmpty());
		assertFalse(stringClass.containsMainMethod());
	}

	@Test
	public void testParseClass_PackagedClass() throws IOException, URISyntaxException {
		JavaDTO packagedClass = JavaParser.parseClass(TestUtil.readResourceFile("/base/PackagedClass.java"));

		assertEquals("PackagedClass", packagedClass.getName());
		assertEquals("com.aelchemy.javamapper.PackagedClass", packagedClass.getFullName());
		assertTrue(packagedClass.getImports().isEmpty());
		assertFalse(packagedClass.containsMainMethod());
	}

	@Test
	public void testParseClass_ImportClass() throws IOException, URISyntaxException {
		JavaDTO importClass = JavaParser.parseClass(TestUtil.readResourceFile("/base/ImportClass.java"));

		assertEquals("ImportClass", importClass.getName());
		assertEquals("ImportClass", importClass.getFullName());
		assertTrue(importClass.getImports().contains("one.Two"));
		assertTrue(importClass.getImports().contains("one.two.Three"));
		assertTrue(importClass.getImports().contains("one.two.three.Four"));
		assertFalse(importClass.containsMainMethod());
	}

	@Test
	public void testParseClass_AbstractClass() throws IOException, URISyntaxException {
		JavaDTO abstractClass = JavaParser.parseClass(TestUtil.readResourceFile("/base/AbstractClass.java"));

		assertEquals("AbstractClass", abstractClass.getName());
		assertEquals("AbstractClass", abstractClass.getFullName());
		assertTrue(abstractClass.getImports().isEmpty());
		assertFalse(abstractClass.containsMainMethod());
		assertTrue(abstractClass.isAbstract());
	}

	@Test
	public void testParseClass_ExtendsClass() throws IOException, URISyntaxException {
		JavaDTO extendsClass = JavaParser.parseClass(TestUtil.readResourceFile("/base/ExtendsClass.java"));

		assertEquals("ExtendsClass", extendsClass.getName());
		assertEquals("ExtendsClass", extendsClass.getFullName());
		assertTrue(extendsClass.getImports().isEmpty());
		assertFalse(extendsClass.containsMainMethod());
		assertTrue(extendsClass.extendsAbstract());
		assertEquals("BaseClass", extendsClass.getBase());
	}

	@Test
	public void testParseClass_InterfaceClass() throws IOException, URISyntaxException {
		JavaDTO interfaceClass = JavaParser.parseClass(TestUtil.readResourceFile("/base/InterfaceClass.java"));

		assertEquals("InterfaceClass", interfaceClass.getName());
		assertEquals("InterfaceClass", interfaceClass.getFullName());
		assertTrue(interfaceClass.getImports().isEmpty());
		assertFalse(interfaceClass.containsMainMethod());
		assertTrue(interfaceClass.isInterface());
	}

	@Test
	public void testParseClass_ImplementsClass() throws IOException, URISyntaxException {
		JavaDTO implementsClass = JavaParser.parseClass(TestUtil.readResourceFile("/base/ImplementsClass.java"));

		assertEquals("ImplementsClass", implementsClass.getName());
		assertEquals("ImplementsClass", implementsClass.getFullName());
		assertTrue(implementsClass.getImports().isEmpty());
		assertFalse(implementsClass.containsMainMethod());
		assertFalse(implementsClass.isInterface());
		assertTrue(implementsClass.implementsInterface());
		assertEquals("AnInterface", implementsClass.getInterface());
	}

	@Test
	public void testParseClass_AbstractImplementsClass() throws IOException, URISyntaxException {
		JavaDTO abstractImplementsClass = JavaParser.parseClass(TestUtil.readResourceFile("/base/AbstractImplementsClass.java"));

		assertEquals("AbstractImplementsClass", abstractImplementsClass.getName());
		assertEquals("AbstractImplementsClass", abstractImplementsClass.getFullName());
		assertTrue(abstractImplementsClass.getImports().isEmpty());
		assertFalse(abstractImplementsClass.containsMainMethod());
		assertTrue(abstractImplementsClass.isAbstract());
		assertFalse(abstractImplementsClass.isInterface());
		assertTrue(abstractImplementsClass.implementsInterface());
		assertEquals("AnInterface", abstractImplementsClass.getInterface());
	}

	@Test
	public void testParseClass_Enum() throws IOException, URISyntaxException {
		JavaDTO enumClass = JavaParser.parseClass(TestUtil.readResourceFile("/base/Enum.java"));

		assertEquals("DaysOfTheWeek", enumClass.getName());
		assertEquals("DaysOfTheWeek", enumClass.getFullName());
		assertTrue(enumClass.getImports().isEmpty());
		assertFalse(enumClass.containsMainMethod());
		assertFalse(enumClass.isAbstract());
		assertFalse(enumClass.isInterface());
		assertFalse(enumClass.implementsInterface());
		assertTrue(enumClass.isEnum());
	}

	@Test
	public void testParseClass_ContainsMainMethod() throws IOException, URISyntaxException {
		assertTrue(JavaParser.parseClass(TestUtil.readResourceFile("/base/main_method/MainClass.java")).containsMainMethod());
		assertTrue(JavaParser.parseClass(TestUtil.readResourceFile("/base/main_method/ShortParameter.java")).containsMainMethod());
		assertTrue(JavaParser.parseClass(TestUtil.readResourceFile("/base/main_method/NoAccess.java")).containsMainMethod());
		assertTrue(JavaParser.parseClass(TestUtil.readResourceFile("/base/main_method/FinalParameter.java")).containsMainMethod());
		assertTrue(JavaParser.parseClass(TestUtil.readResourceFile("/base/main_method/ArrayOnBack.java")).containsMainMethod());
	}

}
