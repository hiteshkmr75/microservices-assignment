package com.example.service_4;

import com.example.service_4.entity.CharacterRelationshipEntity;
import com.example.service_4.repository.CharacterRelationshipRepository;
import com.example.service_4.service.CharacterRelationshipService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CharacterRelationshipControllerTest {

    @Mock
    private CharacterRelationshipService characterRelationshipService;

    @Mock
    private CharacterRelationshipRepository characterRelationshipRepository;

    @InjectMocks
    private CharacterRelationshipController characterRelationshipController;

    public CharacterRelationshipControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("GetById Tests")
    class GetByIdTests {

        @Test
        @DisplayName("Returns entity when ID exists")
        void returnsEntityWhenIdExists() {
            CharacterRelationshipEntity entity = new CharacterRelationshipEntity(1L, 0L, "Warrior", "red");
            when(characterRelationshipRepository.findById(1L)).thenReturn(Optional.of(entity));

            ResponseEntity<CharacterRelationshipEntity> response = characterRelationshipController.getById(1L);

            assertEquals(ResponseEntity.ok(entity), response);
        }

        @Test
        @DisplayName("Returns not found when ID does not exist")
        void returnsNotFoundWhenIdDoesNotExist() {
            when(characterRelationshipRepository.findById(99L)).thenReturn(Optional.empty());

            ResponseEntity<CharacterRelationshipEntity> response = characterRelationshipController.getById(99L);

            assertTrue(response.getStatusCode().is4xxClientError());
        }
    }
}
