package com.example.service_1.controller;

import com.example.service_1.dto.PersonRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrchestratorController {
    private static final Logger log = LoggerFactory.getLogger(OrchestratorController.class);

    private final RestTemplate restTemplate;
    private final String service2;
    private final String service3;

    public OrchestratorController(RestTemplate restTemplate,
                                  @Value("${service2.url:http://localhost:8082}") String service2,
                                  @Value("${service3.url:http://localhost:8083}") String service3) {
        this.restTemplate = restTemplate;
        this.service2 = service2;
        this.service3 = service3;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Up");
    }

    @PostMapping("/combine")
//    @LogMethodParam
    public String combine(@RequestBody @Validated PersonRequest dto) {
        String hello = restTemplate.getForObject(service2 + "/hello", String.class);
        String fullName = restTemplate.postForObject(service3 + "/fullname", dto, String.class);
        return hello + " " + fullName;
    }
}
