package com.aelchemy.maven.plugin.codingame.compiler.util;

public class CommentSanitiser {

	private static enum ParseState {
		NONE, SINGLE_COMMENT, MULTI_COMMENT, STRING
	}

	private static final char SLASH = '/';
	private static final char ASTERISK = '*';
	private static final char DOUBLE_QUOTE = '"';
	private static final char NEW_LINE = '\n';
	private static final char TAB = '\t';

	private CommentSanitiser() {

	}

	public static String sanitise(final String classFile) {
		StringIterator iterator = new StringIterator(classFile);
		StringBuilder parsedClassFile = new StringBuilder();

		ParseState state = ParseState.NONE;

		while (iterator.hasNext()) {
			char character = iterator.next();
			if (state == ParseState.NONE) {
				if (character == SLASH && iterator.getChar(+1) == SLASH) {
					state = ParseState.SINGLE_COMMENT;
				} else if (character == SLASH && iterator.getChar(+1) == ASTERISK) {
					state = ParseState.MULTI_COMMENT;
					while (parsedClassFile.length() > 0 && parsedClassFile.charAt(parsedClassFile.length() - 1) == TAB) {
						parsedClassFile.delete(parsedClassFile.length() - 1, parsedClassFile.length());
					}
				} else {
					if (character == DOUBLE_QUOTE) {
						state = ParseState.STRING;
					}
					parsedClassFile.append(character);
				}
			} else if (state == ParseState.SINGLE_COMMENT) {
				if (character == NEW_LINE) {
					state = ParseState.NONE;
					iterator.next();
				}
			} else if (state == ParseState.MULTI_COMMENT) {
				if (character == SLASH && iterator.getChar(-1) == ASTERISK) {
					state = ParseState.NONE;
					if (iterator.getChar(+2) == NEW_LINE) {
						iterator.next();
						iterator.next();
					}
				}
			} else if (state == ParseState.STRING) {
				parsedClassFile.append(character);
				if (character == DOUBLE_QUOTE && !iterator.isEscaped()) {
					state = ParseState.NONE;
				}
			}
		}

		return parsedClassFile.toString();
	}

}
