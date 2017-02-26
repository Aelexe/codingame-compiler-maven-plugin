/**
 * String class is a class with tricky strings.
 */
public class StringClass {
	
	// A string with a comment inside it.
	private String trickyStringOne = "// Not actually a comment.";
	
	// A string with a multi comment inside it.
	private String trickyStringTwo = "/** Not actually a comment either. */";
	
	// A string with two multi comments either side of an escaped speech mark.
	private String trickyStringThree = "/** Not actually a comment */\" // Not actually a comment either.";
	
	// A string with a comment inside and outside the string, with an escaped backslash before a speech mark.
	private String trickyStringFour = "// This one isn't a comment. \\"/* But this one is! */;

}