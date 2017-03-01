# codingame-compiler-maven-plugin
A maven plugin for the compiling of Java projects into a single file for CodinGame contests/puzzles.

#### What does this plugin do?
* Searches for any class within your project with a main method and attempts to compress it and its dependencies into a single class.

#### Why would I want that?
* The contests/puzzles at [CodinGame](https://www.codingame.com/) only allow one top level class. This plugin allows you to develop your solutions across multiple files instead of fighting with a one file behemoth.

## Requirements
* Java 1.8 and Maven 3

You can probably get it working with earlier versions if you're willing to fight the .pom.

## Installation
* Pull the project and install it in your local maven repository.

* Add the plugin to your .pom.
```xml
<plugin>
	<groupId>com.aelchemy</groupId>
	<artifactId>codingame-compiler-maven-plugin</artifactId>
	<executions>
		<execution>
			<phase>compile</phase>
			<goals>
				<goal>cg-compile</goal>
			</goals>
		</execution>
	</executions>
</plug
```

* Run `mvn compile` and look for the compiled output/s in `your-build-folder\cig-compile\`
