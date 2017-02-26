package com.aelchemy.maven.plugin.codingame.javacompiler.classs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aelchemy.maven.plugin.codingame.javacompiler.util.CodeCompressor;
import com.aelchemy.maven.plugin.codingame.javacompiler.util.CommentSanitiser;

/**
 * {@link ClassParser} contains functionality for creating {@link ClassDTO}s from .java files.
 * 
 * @author Aelexe
 *
 */
public class ClassParser {

	private static final Pattern MAIN_METHOD_PATTERN = Pattern.compile("static\\s+void\\s+main\\((final\\s)?String(\\[\\])?\\s+.+(\\[\\])?\\)");

	private ClassParser() {

	}

	/**
	 * Parses a .java file, returning a {@link ClassDTO} containing the class' package name, class name, imports and TODO code content.
	 * 
	 * @param javaFile The string contents of the .java file to parse.
	 * @return {@link ClassDTO} containing the parsed class' details.
	 */
	public static ClassDTO parseClass(final String javaFile) {
		// Sanitise comments from the java file.
		String sanitisedJavaFile = CommentSanitiser.sanitise(javaFile);

		ClassDTO classDto = new ClassDTO();

		// Split the java file into words so we can look for keywords.
		String[] split = sanitisedJavaFile.split("\\s+");

		for (int i = 0; i < split.length; i++) {
			if (split[i].equals("package")) {
				String sPackage = split[i + 1];
				if (sPackage.endsWith(";")) {
					sPackage = sPackage.substring(0, sPackage.length() - 1);
				}
				classDto.setPackage(sPackage);
			} else if (split[i].equals("import")) {
				String sImport = split[i + 1];
				if (sImport.endsWith(";")) {
					sImport = sImport.substring(0, sImport.length() - 1);
				}
				classDto.addImport(sImport);
			} else if (split[i].equals("class")) {
				classDto.setName(split[i + 1]);
				// Break as class name is the last key detail.
				break;
			}
		}

		// Regular expression to capture the code content of the class.
		Pattern pattern = Pattern.compile(classDto.getName() + "(?s:.*?)\\{(?s:(.*))\\}$");
		Matcher matcher = pattern.matcher(sanitisedJavaFile);
		while (matcher.find()) {
			classDto.setCode(CodeCompressor.compressCode(matcher.group(1)));
			break;
		}

		// Regular expression to detect main method.
		classDto.setContainsMainMethod(MAIN_METHOD_PATTERN.matcher(classDto.getCode()).find());

		return classDto;
	}
}