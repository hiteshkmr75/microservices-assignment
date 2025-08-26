package com.example.service_2.controller;

import com.example.service_2.controller.HelloController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class HelloControllerTest {

    @InjectMocks
    private HelloController helloController;

    @Test
    void helloReturnsHelloMessage() {
        ResponseEntity<String> response = helloController.hello();
        Assertions.assertEquals("Hello", response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
