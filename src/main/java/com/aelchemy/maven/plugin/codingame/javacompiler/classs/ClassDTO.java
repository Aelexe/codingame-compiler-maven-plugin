package com.aelchemy.maven.plugin.codingame.javacompiler.classs;

import java.util.HashSet;
import java.util.Set;

/**
 * {@link ClassDTO} is a DTO containing the package name, class name and fully qualified imports contained within a .java file.
 * 
 * @author Aelexe
 *
 */
public class ClassDTO {

	private String sPackage, name;
	private final Set<String> imports = new HashSet<String>();
	private String code;
	private boolean containsMainMethod;

	public String getPackage() {
		return sPackage;
	}

	public void setPackage(final String sPackage) {
		this.sPackage = sPackage;
	}

	public String getName() {
		return name;
	}

	/**
	 * Returns the concatenated package name and class name, or class name if package name is null.
	 * 
	 * @return The concatenated package name and class name.
	 */
	public String getFullName() {
		return sPackage != null ? sPackage + "." + name : name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Set<String> getImports() {
		return imports;
	}

	public void addImport(final String sImport) {
		imports.add(sImport);
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public boolean containsMainMethod() {
		return containsMainMethod;
	}

	public void setContainsMainMethod(final boolean containsMainMethod) {
		this.containsMainMethod = containsMainMethod;
	}

}
