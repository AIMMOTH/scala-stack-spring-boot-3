package com.github.aimmoth.scala_stack.spring_boot_3.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Data
public class EnvironmentModel {

    private String libsPath;
    private String srcPath;
    private String javascriptUrl;
}
