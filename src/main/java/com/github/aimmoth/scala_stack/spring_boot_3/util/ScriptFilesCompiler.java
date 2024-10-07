package com.github.aimmoth.scala_stack.spring_boot_3.util;

import com.github.aimmoth.scala_stack.js_compiler.ScalaStackJsCompiler;
import com.github.aimmoth.scala_stack.scala3.compiler_util.JavaConverter;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Component
@Log
public class ScriptFilesCompiler {

    private com.github.aimmoth.scala_stack.js_compiler.Classpath classpath;
    private JavaConverter javaConverter;
    private ScalaStackJsCompiler scalaStackJsCompiler;

    public String apply(String folder) {
        var scriptSrc = new File(folder);
        var scripts = readScripts(scriptSrc);

        log.info("Compiling ...");
        var script = javaConverter.compileFast(scalaStackJsCompiler, classpath, scripts);
        return script;
    }

    private List<String> readScripts(File rootDirectory) {
        log.info("Files:" + Arrays.asList(rootDirectory.listFiles()));
        var content = rootDirectory.listFiles();
        log.info("Read files ...");
        var files = findScalaJsFiles(rootDirectory);
        return files.map(file -> {
                    try {
                        log.info("Reading source file " + file.getAbsolutePath());
                        return Files.readAllLines(file.toPath()).stream().collect(Collectors.joining("\n"));
                    } catch (IOException e) {
                        log.log(Level.WARNING, e.getMessage(), e);
                        return "// Could not read " + file.getAbsolutePath();
                    }
                })
                .collect(Collectors.toList());
    }

    private Stream<File> findScalaJsFiles(File folder) {
        return Arrays.asList(folder.listFiles())
            .stream()
            .flatMap(file -> {
                if (file.isDirectory()) {
                    var dir = new File(file.getAbsolutePath());
                    return findScalaJsFiles(dir);
                } else {
                    return Stream.of(file);
                }
            })
            .filter(file -> file.getName().endsWith(".scala") && !file.getName().endsWith("package.scala"))
            ;
    }
}
