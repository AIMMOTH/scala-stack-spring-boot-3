package com.github.aimmoth.scala_stack.spring_boot_3;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        log.info("Staring application ...");
        SpringApplication.run(MainApplication.class, args);
    }
}
