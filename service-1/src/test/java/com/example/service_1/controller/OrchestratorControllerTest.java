package com.example.service_1.controller;

import com.example.service_1.dto.PersonRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrchestratorControllerTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrchestratorController orchestratorController;

    @Value("${service2.url:http://localhost:8082}")
    private String service2;

    @Value("${service3.url:http://localhost:8083}")
    private String service3;

    @Test
    void healthReturnsUp() {
        ResponseEntity<String> response = orchestratorController.health();
        Assertions.assertEquals("Up", response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void combinedMessageWhenServicesRespondSuccessfully() {
        PersonRequest personRequest = new PersonRequest("John", "Doe");
        String service2Response = "Hello";
        String service3Response = "John Doe";

        when(restTemplate.getForObject(service2 + "/hello", String.class)).thenReturn(service2Response);
        when(restTemplate.postForObject(eq(service3 + "/fullname"), eq(personRequest), eq(String.class))).thenReturn(service3Response);

        ResponseEntity<String> result = orchestratorController.combine(personRequest);

        Assertions.assertEquals("Hello John Doe", result.getBody());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void combineThrowsExceptionWhenService2Fails() {
        PersonRequest personRequest = new PersonRequest("John", "Doe");

        when(restTemplate.getForObject(service2 + "/hello", String.class)).thenThrow(new RestClientException("Service 2 error"));

        ResponseEntity<String> result = orchestratorController.combine(personRequest);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertTrue(result.getBody().contains("Service 2 error"));
    }

    @Test
    void combineThrowsExceptionWhenService3Fails() {
        PersonRequest personRequest = new PersonRequest("John", "Doe");
        String service2Response = "Hello";

        when(restTemplate.getForObject(service2 + "/hello", String.class)).thenReturn(service2Response);
        when(restTemplate.postForObject(eq(service3 + "/fullname"), eq(personRequest), eq(String.class))).thenThrow(new RestClientException("Service 3 error"));

        ResponseEntity<String> result = orchestratorController.combine(personRequest);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        Assertions.assertTrue(result.getBody().contains("Service 3 error"));
    }
}