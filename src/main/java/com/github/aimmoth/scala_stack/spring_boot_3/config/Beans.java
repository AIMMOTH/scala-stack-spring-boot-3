package com.github.aimmoth.scala_stack.spring_boot_3.config;

import lombok.extern.java.Log;

import com.github.aimmoth.scala_stack.js_compiler.ScalaStackJsCompiler;
import com.github.aimmoth.scala_stack.scala3.compiler_util.JavaConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Configuration
@Log
public class Beans {

    @Value("${scala_stack.environment}")
    private String env;
    @Value("${scala_stack.compiler.libs}")
    private String libsPath;
    @Value("${scala_stack.compiler.src}")
    private String srcPath;
    @Value("${scala_stack.javascriptUrl}")
    private String javascriptUrl;

    @Bean
    public EnvironmentModel getEnvironmentModel() {
        return new EnvironmentModel(libsPath, srcPath, javascriptUrl);
    }

    @Bean
    public com.github.aimmoth.scala_stack.js_compiler.Classpath getClasspath(ScalaStackJsCompiler scalaStackJsCompiler,JavaConverter javaConverter) {
        scala.Function1<String, InputStream> loader = (filename) -> {
            try {
                var uri = new URI(filename);
                var file = ResourceUtils.getFile(uri.getPath());
                return new FileInputStream(file);
            } catch (Exception e) {
                log.log(Level.WARNING, e.getMessage(), e);
                return null;
            }
        };
        try {
            var libsPathFile = new File(libsPath);
            var libFiles = Arrays.asList(libsPathFile.listFiles());
            log.info("Files in libs dir:" + libFiles);
            var libs = libFiles.stream().map(File::getName).collect(Collectors.toSet());
            return javaConverter.createClasspath(scalaStackJsCompiler, loader, libsPath, libs);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
            return javaConverter.createClasspath(scalaStackJsCompiler, loader, libsPath, new HashSet<>());
        }
    }

    @Bean
    public JavaConverter getJavaConverter() {
        return new JavaConverter();
    }

    @Bean
    public ScalaStackJsCompiler getScalaStackJsCompiler() {
        return new ScalaStackJsCompiler();
    }
}
