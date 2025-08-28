package com.example.service_4;

import com.example.service_4.entity.CharacterRelationshipEntity;
import com.example.service_4.repository.CharacterRelationshipRepository;
import com.example.service_4.service.CharacterRelationshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/character")
public class CharacterRelationshipController {

    private final CharacterRelationshipService characterRelationshipService;

    private final CharacterRelationshipRepository characterRelationshipRepository;

    public CharacterRelationshipController(CharacterRelationshipService characterRelationshipService,
                                           CharacterRelationshipRepository characterRelationshipRepository) {
        this.characterRelationshipService = characterRelationshipService;
        this.characterRelationshipRepository = characterRelationshipRepository;
    }



    @GetMapping("/{id}")
    public ResponseEntity<CharacterRelationshipEntity> getById(@PathVariable Long id) {
        return characterRelationshipRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> nested() {
        return ResponseEntity.ok(characterRelationshipService.nested());
    }

}
