package com.aelchemy.maven.plugin.codingame.javacompiler.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import com.aelchemy.maven.plugin.codingame.javacompiler.TestUtil;

public class CommentSanitiserTest {

	@Test
	public void testSanitise_SimpleClass() throws IOException, URISyntaxException {
		assertEquals(TestUtil.readResourceFile("/comment_sanitised/SimpleClass.java"), CommentSanitiser.sanitise(TestUtil.readResourceFile("/base/SimpleClass.java")));
	}

	@Test
	public void testSanitise_StringClass() throws IOException, URISyntaxException {
		assertEquals(TestUtil.readResourceFile("/comment_sanitised/StringClass.java"), CommentSanitiser.sanitise(TestUtil.readResourceFile("/base/StringClass.java")));
	}

}
