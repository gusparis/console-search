# Console Search Engine

Reads all the text files in the given directory, building an in memory representation of the
files and their contents, and then give a command prompt at which interactive searches can be
performed.

The search should take the words given on the command prompt and return a list of the top 10
(max) matching filenames in rank order, giving the rank score against each match.

## Software prerequisites
* Java 8 ([JDK not JRE](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html))
* Latest [Maven](http://maven.apache.org/download.cgi)
* To verify the installation run in a terminal: `mvn --version` and should appear a message with **Maven** and **Java** version.

## Install and run the project 
1. Download and extract the project.
2. From the folder project run the command `mvn clean package` and the console shows a message **[INFO] BUILD SUCCESS** when the build finished without errors.
3. In the project folder there is a new `/target` folder with a jar file inside.
4. To run the application in the `/target` folder run this command `java -jar schibsted-demo.jar /foo/bar`

## Things to consider
* You must enter your search words separated by a blank.
* A word is a match if it's preceded by a blank, then every letter matches and it's followed by a blank, a comma or a dot.

## Example of use
```
$ java ­jar schibsted-demo.jar /foo/bar
14 files read in directory /foo/bar
search>
search> to be or not to be
filename1 : 100%
filename2 : 95%
search>
search> cats
no matches found
search> :quit
```