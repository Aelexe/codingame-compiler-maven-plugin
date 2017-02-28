import java.util.*;
import java.io.*;
import java.math.*;
class SimpleClass {
private static String staticString = "This is a static string.";
private int zero = 0;
public SimpleClass() {
}
public int getZero() {
	return zero;
}
public void setZero(int zero) {
	this.zero = zero;
}
private static class StringClass implements InterfaceClass {
private String trickyStringOne = "// Not actually a comment.";
private String trickyStringTwo = "/** Not actually a comment either. */";
private String trickyStringThree = "/** Not actually a comment */\" // Not actually a comment either.";
private String trickyStringFour = "// This one isn't a comment. \\";
}
private static interface InterfaceClass {
public String getTheString(int stringIndex);
public String getTheInt(int x, int y);
public void beTheGuy();
}
}
