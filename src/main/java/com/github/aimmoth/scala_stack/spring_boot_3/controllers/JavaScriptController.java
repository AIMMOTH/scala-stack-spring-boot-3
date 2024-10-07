package com.github.aimmoth.scala_stack.spring_boot_3.controllers;

import com.github.aimmoth.scala_stack.spring_boot_3.config.EnvironmentModel;
import com.github.aimmoth.scala_stack.spring_boot_3.util.ScriptFilesCompiler;
import lombok.AllArgsConstructor;
import com.github.aimmoth.scala_stack.open_api.api.controller.AppJsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class JavaScriptController implements AppJsApi {

    private EnvironmentModel environment;
    private ScriptFilesCompiler scriptCompiler;

    @Override
    public ResponseEntity<String> getAppScript() {
        return ResponseEntity.ok(scriptCompiler.apply(environment.getSrcPath()));
    }
}
