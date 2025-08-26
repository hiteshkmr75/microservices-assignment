package com.example.service_4.service;

import com.example.service_4.entity.CharacterRelationshipEntity;
import com.example.service_4.repository.CharacterRelationshipRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CharacterRelationshipServiceTest {

    @Mock
    private CharacterRelationshipRepository repo;

    @InjectMocks
    private CharacterRelationshipService service;

    @Test
    void nestedReturnsEmptyListWhenNoDataExists() {
        when(repo.findAll()).thenReturn(List.of());

        List<Map<String, Object>> result = service.nested();

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void nestedBuildsTreeStructureCorrectly() {
        CharacterRelationshipEntity parent = new CharacterRelationshipEntity(1L, null, "Parent", "Red");
        CharacterRelationshipEntity child = new CharacterRelationshipEntity(2L, 1L, "Child", "Blue");
        when(repo.findAll()).thenReturn(List.of(parent, child));

        List<Map<String, Object>> result = service.nested();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Parent", result.get(0).get("Name"));
        Assertions.assertEquals("Red", result.get(0).get("Color"));
        Assertions.assertTrue(((List<?>) result.get(0).get("Sub Classes")).size() > 0);
    }

    @Test
    void nestedHandlesEntitiesWithoutParentId() {
        CharacterRelationshipEntity entity = new CharacterRelationshipEntity(1L, null, "Orphan", "Green");
        when(repo.findAll()).thenReturn(List.of(entity));

        List<Map<String, Object>> result = service.nested();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Orphan", result.get(0).get("Name"));
        Assertions.assertEquals("Green", result.get(0).get("Color"));
        Assertions.assertNull(result.get(0).get("Sub Classes"));
    }

    @Test
    void nestedHandlesMultipleRootNodes() {
        CharacterRelationshipEntity root1 = new CharacterRelationshipEntity(1L, null, "Root1", "Yellow");
        CharacterRelationshipEntity root2 = new CharacterRelationshipEntity(2L, null, "Root2", "Purple");
        when(repo.findAll()).thenReturn(List.of(root1, root2));

        List<Map<String, Object>> result = service.nested();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Root1", result.get(0).get("Name"));
        Assertions.assertEquals("Root2", result.get(1).get("Name"));
    }
}