package com.aelchemy.maven.plugin.codingame.compiler.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aelchemy.maven.plugin.codingame.compiler.util.CodeCompressor;
import com.aelchemy.maven.plugin.codingame.compiler.util.CommentSanitiser;

/**
 * {@link JavaParser} contains functionality for creating {@link JavaDTO}s from .java files.
 * 
 * @author Aelexe
 *
 */
public class JavaParser {

	private static final Pattern MAIN_METHOD_PATTERN = Pattern.compile("static\\s+void\\s+main\\((final\\s)?String(\\[\\])?\\s+.+(\\[\\])?\\)");

	private JavaParser() {

	}

	/**
	 * Parses a .java file, returning a {@link JavaDTO} containing the class/interface's package name, name, imports and
	 * code content.
	 * 
	 * @param javaFile The string contents of the .java file to parse.
	 * @return {@link JavaDTO} containing the parsed class/interface details.
	 */
	public static JavaDTO parseClass(final String javaFile) {
		// Sanitise comments from the java file.
		String sanitisedJavaFile = CommentSanitiser.sanitise(javaFile);

		JavaDTO javaDto = new JavaDTO();

		// Split the java file into words so we can look for keywords.
		String[] split = sanitisedJavaFile.split("\\s+");

		for (int i = 0; i < split.length; i++) {
			String word = split[i];
			if (word.equals("package")) {
				String sPackage = split[i + 1];
				if (sPackage.endsWith(";")) {
					sPackage = sPackage.substring(0, sPackage.length() - 1);
				}
				javaDto.setPackage(sPackage);
			} else if (word.equals("import")) {
				String sImport = split[i + 1];
				if (sImport.endsWith(";")) {
					sImport = sImport.substring(0, sImport.length() - 1);
				}
				javaDto.addImport(sImport);
			} else if (word.equals("class")) {
				javaDto.setName(split[i + 1]);
				if (split[i + 2].equals("implements")) {
					javaDto.setInterface(split[i + 3]);
				}
				break;
			} else if (word.equals("interface")) {
				javaDto.setName(split[i + 1]);
				javaDto.setIsInterface(true);
				break;
			}
		}

		// Regular expression to capture the code content of the class.
		Pattern pattern = Pattern.compile(javaDto.getName() + "(?s:.*?)\\{(?s:(.*))\\}$");
		Matcher matcher = pattern.matcher(sanitisedJavaFile);
		while (matcher.find()) {
			javaDto.setCode(CodeCompressor.compressCode(matcher.group(1)));
			break;
		}

		// Regular expression to detect main method.
		javaDto.setContainsMainMethod(MAIN_METHOD_PATTERN.matcher(javaDto.getCode()).find());

		return javaDto;
	}
}