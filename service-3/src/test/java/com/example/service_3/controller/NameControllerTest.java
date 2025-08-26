package com.example.service_3.controller;

import com.example.service_3.controller.NameController;
import com.example.service_3.dto.PersonRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NameControllerTest {

    @InjectMocks
    private NameController nameController;

    @Test
    void fullNameReturnsConcatenatedNameAndSurname() {
        PersonRequest personRequest = new PersonRequest("John", "Doe");

        String result = nameController.fullName(personRequest);

        Assertions.assertEquals("John Doe", result);
    }

    @Test
    void fullNameHandlesEmptyName() {
        PersonRequest personRequest = new PersonRequest("", "Doe");

        String result = nameController.fullName(personRequest);

        Assertions.assertEquals(" Doe", result);
    }

    @Test
    void fullNameHandlesEmptySurname() {
        PersonRequest personRequest = new PersonRequest("John", "");

        String result = nameController.fullName(personRequest);

        Assertions.assertEquals("John ", result);
    }

    @Test
    void fullNameHandlesEmptyNameAndSurname() {
        PersonRequest personRequest = new PersonRequest("", "");

        String result = nameController.fullName(personRequest);

        Assertions.assertEquals(" ", result);
    }
}