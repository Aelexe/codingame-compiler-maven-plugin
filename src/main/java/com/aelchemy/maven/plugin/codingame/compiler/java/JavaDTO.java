package com.aelchemy.maven.plugin.codingame.compiler.java;

import java.util.HashSet;
import java.util.Set;

/**
 * {@link JavaDTO} is a DTO containing the package name, name and fully qualified imports contained within a .java file.
 * 
 * @author Aelexe
 * 
 */
public class JavaDTO {

	private String sPackage, name, sInterface, base;
	private final Set<String> imports = new HashSet<String>();
	private String code;
	private boolean containsMainMethod;
	private boolean isAbstract, extendsAbstract;
	private boolean isInterface, implementsInterface;
	private boolean isEnum;

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

	public String getInterface() {
		return sInterface;
	}

	public void setInterface(final String sInterface) {
		this.sInterface = sInterface;
		implementsInterface = true;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
		extendsAbstract = true;
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

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setIsAbstract(final boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	public boolean extendsAbstract() {
		return extendsAbstract;
	}

	public boolean isInterface() {
		return isInterface;
	}

	public void setIsInterface(final boolean isInterface) {
		this.isInterface = isInterface;
	}

	public boolean implementsInterface() {
		return implementsInterface;
	}

	public void setIsEnum(boolean isEnum) {
		this.isEnum = isEnum;
	}

	public boolean isEnum() {
		return isEnum;
	}

}
