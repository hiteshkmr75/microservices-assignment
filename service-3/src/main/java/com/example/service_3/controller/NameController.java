package com.example.service_3.controller;

import com.example.service_3.annotation.LogMethodParam;
import com.example.service_3.dto.PersonRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class NameController {
    private static final Logger log = LoggerFactory.getLogger(NameController.class);

    @PostMapping("/fullname")
    @LogMethodParam
    public String fullName(@RequestBody @Validated PersonRequest personRequest) {
        try {
            log.info("Received: {} {}", personRequest.getName(), personRequest.getSurname());
            return personRequest.getName() + " " + personRequest.getSurname();
        } catch (Exception ex) {
            log.error("Error occurred while processing fullName: {}", ex.getMessage());
            throw ex;
        }
    }
}
