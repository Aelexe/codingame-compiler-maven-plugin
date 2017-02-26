package com.aelchemy.maven.plugin.codingame.javacompiler.classs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import com.aelchemy.maven.plugin.codingame.javacompiler.TestUtil;

public class ClassParserTest {

	@Test
	public void testParseClass_SimpleClass() throws IOException, URISyntaxException {
		ClassDTO simpleClass = ClassParser.parseClass(TestUtil.readResourceFile("/base/SimpleClass.java"));

		assertEquals("SimpleClass", simpleClass.getName());
		assertEquals("SimpleClass", simpleClass.getFullName());
		assertTrue(simpleClass.getImports().isEmpty());
		assertEquals(TestUtil.readResourceFile("/code_content/compressed/SimpleClass.java"), simpleClass.getCode());
		assertFalse(simpleClass.containsMainMethod());
	}

	@Test
	public void testParseClass_StringClass() throws IOException, URISyntaxException {
		ClassDTO stringClass = ClassParser.parseClass(TestUtil.readResourceFile("/base/StringClass.java"));

		assertEquals("StringClass", stringClass.getName());
		assertEquals("StringClass", stringClass.getFullName());
		assertTrue(stringClass.getImports().isEmpty());
		assertFalse(stringClass.containsMainMethod());
	}

	@Test
	public void testParseClass_PackagedClass() throws IOException, URISyntaxException {
		ClassDTO packagedClass = ClassParser.parseClass(TestUtil.readResourceFile("/base/PackagedClass.java"));

		assertEquals("PackagedClass", packagedClass.getName());
		assertEquals("com.aelchemy.javamapper.PackagedClass", packagedClass.getFullName());
		assertTrue(packagedClass.getImports().isEmpty());
		assertFalse(packagedClass.containsMainMethod());
	}

	@Test
	public void testParseClass_ImportClass() throws IOException, URISyntaxException {
		ClassDTO importClass = ClassParser.parseClass(TestUtil.readResourceFile("/base/ImportClass.java"));

		assertEquals("ImportClass", importClass.getName());
		assertEquals("ImportClass", importClass.getFullName());
		assertTrue(importClass.getImports().contains("one.Two"));
		assertTrue(importClass.getImports().contains("one.two.Three"));
		assertTrue(importClass.getImports().contains("one.two.three.Four"));
		assertFalse(importClass.containsMainMethod());
	}

	@Test
	public void testParseClass_ContainsMainMethod() throws IOException, URISyntaxException {
		assertTrue(ClassParser.parseClass(TestUtil.readResourceFile("/base/main_method/MainClass.java")).containsMainMethod());
		assertTrue(ClassParser.parseClass(TestUtil.readResourceFile("/base/main_method/ShortParameter.java")).containsMainMethod());
		assertTrue(ClassParser.parseClass(TestUtil.readResourceFile("/base/main_method/NoAccess.java")).containsMainMethod());
		assertTrue(ClassParser.parseClass(TestUtil.readResourceFile("/base/main_method/FinalParameter.java")).containsMainMethod());
		assertTrue(ClassParser.parseClass(TestUtil.readResourceFile("/base/main_method/ArrayOnBack.java")).containsMainMethod());
	}

}
