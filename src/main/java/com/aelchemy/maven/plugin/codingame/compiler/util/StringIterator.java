package com.aelchemy.maven.plugin.codingame.compiler.util;

public class StringIterator {

	private static final char ESCAPE = '\\';
	private static final char NULL = '\0';

	private final String string;
	private int index = -1;

	public StringIterator(final String string) {
		this.string = string;
	}

	public char getChar() {
		return string.charAt(index);
	}

	public char getChar(final int indexOffset) {
		int offsettedIndex = index + indexOffset;
		if (offsettedIndex < 0 || offsettedIndex > string.length() - 1) {
			return NULL;
		} else {
			return string.charAt(offsettedIndex);
		}
	}

	public boolean isEscaped() {
		int escapeIndex = index - 1, escapeCount = 0;

		while (escapeIndex >= 0) {
			if (string.charAt(escapeIndex) == ESCAPE) {
				escapeCount++;
				escapeIndex--;
			} else {
				break;
			}
		}

		return escapeCount > 0 ? escapeCount % 2 != 0 : false;
	}

	public boolean hasNext() {
		return index < string.length() - 1;
	}

	public char next() {
		if (index < string.length() - 1) {
			index++;
		}

		return getChar();
	}

}
