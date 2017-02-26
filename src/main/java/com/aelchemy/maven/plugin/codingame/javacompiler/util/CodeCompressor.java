package com.aelchemy.maven.plugin.codingame.javacompiler.util;

public class CodeCompressor {

	private CodeCompressor() {

	}

	public static String compressCode(final String code) {
		StringBuilder compressedCode = new StringBuilder();
		String[] codeLines = code.split("\\n");

		int smallestTabCount = Integer.MAX_VALUE;
		for (String codeLine : codeLines) {
			if (codeLine.startsWith("\r")) {
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

		for (int i = 0; i < codeLines.length; i++) {
			String codeLine = codeLines[i];
			if (codeLine.matches("^\\s*$")) {
				continue;
			}
			compressedCode.append(codeLine.substring(smallestTabCount) + "\n");
		}

		if (compressedCode.length() > 0) {
			return compressedCode.substring(0, compressedCode.length() - 2);
		} else {
			return compressedCode.toString();
		}
	}
}
