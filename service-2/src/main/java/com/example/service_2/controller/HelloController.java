package com.example.service_2.controller;

import com.example.service_2.annotation.LogMethodParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    @LogMethodParam
    public ResponseEntity<String> hello() {
        log.info("Service-2 /hello called");
        return ResponseEntity.ok("Hello");
    }
}
