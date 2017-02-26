package com.aelchemy.maven.plugin.codingame.javacompiler.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import com.aelchemy.maven.plugin.codingame.javacompiler.TestUtil;

public class CodeCompressorTest {

	@Test
	public void testCompressCode_SimpleClass() throws IOException, URISyntaxException {
		assertEquals(TestUtil.readResourceFile("/code_content/compressed/SimpleClass.java"),
				CodeCompressor.compressCode(TestUtil.readResourceFile("/code_content/raw/SimpleClass.java")));
	}

	@Test
	public void testCompressCode_StringClass() throws IOException, URISyntaxException {
		assertEquals(TestUtil.readResourceFile("/code_content/compressed/StringClass.java"),
				CodeCompressor.compressCode(TestUtil.readResourceFile("/code_content/raw/StringClass.java")));
	}

	@Test
	public void testCompressCode_PackagedClass() throws IOException, URISyntaxException {
		assertEquals(TestUtil.readResourceFile("/code_content/compressed/PackagedClass.java"),
				CodeCompressor.compressCode(TestUtil.readResourceFile("/code_content/raw/PackagedClass.java")));
	}

	@Test
	public void testCompressCode_ImportClass() throws IOException, URISyntaxException {
		assertEquals(TestUtil.readResourceFile("/code_content/compressed/ImportClass.java"),
				CodeCompressor.compressCode(TestUtil.readResourceFile("/code_content/raw/ImportClass.java")));
	}

}
