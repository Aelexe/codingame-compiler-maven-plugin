package com.aelchemy.maven.plugin.codingame.javacompiler;

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

}
