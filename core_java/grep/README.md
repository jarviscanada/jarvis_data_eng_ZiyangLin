# Introduction

#### Application:
This application is a Java 8 reproduction of the `grep` Linux command. Given a root directory, a targeted regex pattern, and an output destination file,
the program will write to the output file all lines in all files under the root directory that matches the given regex pattern.

#### Technology Learned:
- *Java 8*: Inheritance and Interfaces, Unit Test, Logger, Stream API and Lambda Expressions, I/O
- *Development*: Maven, IDE (shortcuts, configurations, etc.), Docker, JVM/JRE/JDK

# Usage

#### Run with IDE:
1. Clone the `core_java/grep` repository, and import the project using an IDE.
2. Configure the running arguments to be: `rootDir regexPattern outFile` (e.g. `.*IllegalArgumentException.* ./grep/src /tmp/grep.out`) 
3. Run the program with the `main` method in either `JavaGrepImp.java` or `JavaGrepLambdaImp.java`

#### Run with Terminal:
```
cd core_java/grep # go to project directory
mvn clean compile package #compile and package your Java code

java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp [regex] [rootDir] [outFile] # run the program
```
Note that if the program throws an OutOfMemoryException, we can add `-Xms` and `-Xmx` options to increase the Heap size in the command to run this program. 

# Pseudocode
The main method for this program is `process()` that automates the entire workflow, and its pseudocode is as below.
```$xslt
// class inputs: rootDir, pattern, outFile
process():
    matchedLines = []
    for file in listFilesRecursively(rootDir)
      for line in readLines(file)
          if containsPattern(line, pattern)
            matchedLines.add(line)
    
    writeToFile(matchedLines, outFile)
```

# Performance Issue
This program read and load all lines of files into the Heap memory, and then filter them against the provided pattern.
When a directory has significantly large files, the Heap memory may not be enough to store them, under which an `OutOfMemoryError` may occur. One may need to manually increase the Heap size for the program to run.

# Improvement
1. This application is currently designed to be single-threaded, which might be slow when reading a large number of files. A possible enhancement on its efficiency can be to introduce multi-thread reading.
2. This application searches the targeted root directory recursively for all files, but allowing users to determine whether recursive file searched is desired can provide better flexibility.
3. Other linux `grep` functionalities with options such as `-i -v -c` can be implemented in the future.