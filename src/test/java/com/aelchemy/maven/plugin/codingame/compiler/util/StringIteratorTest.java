package com.aelchemy.maven.plugin.codingame.compiler.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.aelchemy.maven.plugin.codingame.compiler.util.StringIterator;

public class StringIteratorTest {

	@Test
	public void testHasNext() {
		StringIterator iterator = new StringIterator("abcde");

		for (int i = 0; i < 5; i++) {
			assertTrue(iterator.hasNext());
			iterator.next();
		}
		assertFalse(iterator.hasNext());
	}

	@Test
	public void testNextOutOfBounds() {
		StringIterator iterator = new StringIterator("12345");

		for (int i = 0; i < 10; i++) {
			iterator.next();
		}
	}

}
