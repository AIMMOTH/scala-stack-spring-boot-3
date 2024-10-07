package com.github.aimmoth.scala_stack.spring_boot_3.controllers;

import com.github.aimmoth.scala_stack.open_api.api.controller.PageHtmlApi;
import com.github.aimmoth.scala_stack.scala3.www.PageHtml;
import com.github.aimmoth.scala_stack.spring_boot_3.config.EnvironmentModel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class HtmlController implements PageHtmlApi {

    private EnvironmentModel environment;

    @Override
    public ResponseEntity<String> getPage() {
        return ResponseEntity.ok(new PageHtml().apply(environment.getJavascriptUrl()));
    }
}