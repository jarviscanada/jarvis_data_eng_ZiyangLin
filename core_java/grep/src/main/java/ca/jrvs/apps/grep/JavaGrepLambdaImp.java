package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JavaGrepLambdaImp extends JavaGrepImp {

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    public static void main(String[] args) {
        // testing arguments: .*IllegalArgumentException.* ./grep/src /tmp/grep.out
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }

        BasicConfigurator.configure();

        JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
        javaGrepLambdaImp.setRegex(args[0]);
        javaGrepLambdaImp.setRootPath(args[1]);
        javaGrepLambdaImp.setOutFile(args[2]);

        try {
            javaGrepLambdaImp.process();
        } catch (Exception e) {
            javaGrepLambdaImp.logger.error(e.getMessage(), e);
        }

    }

    @Override
    public List<File> listFiles(String rootDir) throws IOException {
        List<File> result = new ArrayList<>();

        // use Files.walk() method (take Path as parameter) to scan through the whole directory, then
        //     use stream to filter out all regular files, and add them to the result.
        try {
            Files.walk(Paths.get(rootDir))
                    .filter(Files::isRegularFile)
                    .filter(f -> !f.toFile().isHidden())
                    .forEach(file -> result.add(file.toFile()));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        return result;
    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> lines = new ArrayList<>();

        // use Files.lines to open a file (it takes Path as parameter) and read and add each line to lines.
        try {
            Files.lines(Paths.get(inputFile.getAbsolutePath())).forEach(lines::add);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        return lines;
    }

}
