package org.login_page.server.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ExampleController {
    @GetMapping("/v1/example")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> exampleController() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello world");
    }
}
