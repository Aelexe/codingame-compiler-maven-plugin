package com.aelchemy.maven.plugin.codingame.javacompiler;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;

/**
 * {@link TestUtil} contains common methods used in the unit tests.
 * 
 * @author Aelexe
 *
 */
public class TestUtil {

	/**
	 * Gets a {@link File} referencing a resource defined by the resource path.
	 * 
	 * @param resourcePath Resource path of the resource to retrieve.
	 * @return {@link File} referencing the resource.
	 * @throws URISyntaxException
	 */
	public static File getResourceFile(final String resourcePath) throws URISyntaxException {
		return new File(TestUtil.class.getClass().getResource(resourcePath).toURI());
	}

	/**
	 * Gets the string contents of a resource defined by the resource path.
	 * 
	 * @param resourcePath Resource path of the resource to retrieve the string contents of.
	 * @return String contents of the resource.
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static String readResourceFile(final String resourcePath) throws IOException, URISyntaxException {
		return FileUtils.readFileToString(getResourceFile(resourcePath), "UTF-8");
	}

	/**
	 * Asserts the actual string matches the expected string with indifference to form of line breaks used.
	 * 
	 * @param expected The expected string
	 * @param actual The actual string.
	 */
	public static void assertStringEquals(final String expected, final String actual) {
		assertEquals(replaceAllLineBreaks(expected), replaceAllLineBreaks(actual));
	}

	/**
	 * Returns the string with any carriage returns replaced with line feeds.
	 * 
	 * @param string The string to remove carriage returns from.
	 * @return The string, sans carriage returns.
	 */
	public static String replaceAllLineBreaks(final String string) {
		return string.replaceAll("\r\n", "\n").replaceAll("\r", "\n");
	}

}
