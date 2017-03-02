package com.aelchemy.maven.plugin.codingame.compiler.util;

import org.apache.commons.lang3.StringUtils;

/**
 * {@link CodeCompressor} contains functionality for compressing code.
 * 
 * @author Aelexe
 *
 */
public class CodeCompressor {

	private CodeCompressor() {

	}

	/**
	 * Compresses the provided code, removing unnecessary lines and tabs.
	 * 
	 * @param code Code to compress.
	 * @return The compressed code.
	 */
	public static String compressCode(final String code) {
		StringBuilder compressedCode = new StringBuilder();

		// Get the code lines split by a line separator.
		String[] codeLines = code.split("(\\r)?\\n");

		// Count the minimum amount of tabs common amongst lines, to be trimmed off later.
		int smallestTabCount = Integer.MAX_VALUE;
		for (String codeLine : codeLines) {
			// Skip empty lines.
			if (StringUtils.isBlank(codeLine)) {
				continue;
			}
			int tabCount = 0;
			for (char characters : codeLine.toCharArray()) {
				if (characters == '\t') {
					tabCount++;
				} else {
					break;
				}
			}
			if (tabCount < smallestTabCount) {
				smallestTabCount = tabCount;
			}
		}

		for (String codeLine : codeLines) {
			// Skip empty lines.
			if (StringUtils.isBlank(codeLine)) {
				continue;
			}

			// Append the line to the compressed code, less the common amount of tabs.
			compressedCode.append(codeLine.substring(smallestTabCount) + System.lineSeparator());
		}

		// If there is any code at all, use set length to trim off the trailing line separator.
		if (compressedCode.length() > 0) {
			compressedCode.setLength(compressedCode.length() - System.lineSeparator().length());
		}

		return compressedCode.toString();
	}
}
