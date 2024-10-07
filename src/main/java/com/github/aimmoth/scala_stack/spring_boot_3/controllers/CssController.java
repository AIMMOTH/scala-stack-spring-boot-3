package com.github.aimmoth.scala_stack.spring_boot_3.controllers;

import com.github.aimmoth.scala_stack.scala3.www.StyleCss;
import lombok.AllArgsConstructor;
import com.github.aimmoth.scala_stack.open_api.api.controller.StyleCssApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class CssController implements StyleCssApi {

    @Override
    public ResponseEntity<String> getStyle() {
        return ResponseEntity.ok(new StyleCss().apply());
    }
}