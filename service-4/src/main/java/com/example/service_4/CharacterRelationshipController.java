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

    /*
        seed initial data into the DB using a Spring bean - DataSeeder
     */
//    @PostMapping("/seed")
//    public String seed() {
//        characterRelationshipRepository.saveAll(List.of(
//                new CharacterRelationshipEntity(1L,0L,"Warrior","red"),
//                new CharacterRelationshipEntity(2L,0L,"Wizard","green"),
//                new CharacterRelationshipEntity(3L,0L,"Priest","white"),
//                new CharacterRelationshipEntity(4L,0L,"Rogue","yellow"),
//                new CharacterRelationshipEntity(5L,1L,"Fighter","blue"),
//                new CharacterRelationshipEntity(6L,1L,"Paladin","lighblue"),
//                new CharacterRelationshipEntity(7L,1L,"Ranger","lighgreen"),
//                new CharacterRelationshipEntity(8L,2L,"Mage","grey"),
//                new CharacterRelationshipEntity(9L,2L,"Specialist wizard","lightgrey"),
//                new CharacterRelationshipEntity(10L,3L,"Cleric","red"),
//                new CharacterRelationshipEntity(11L,3L,"Druid","green"),
//                new CharacterRelationshipEntity(12L,3L,"Priest of specific mythos","white"),
//                new CharacterRelationshipEntity(13L,4L,"Thief","yellow"),
//                new CharacterRelationshipEntity(14L,4L,"Bard","blue"),
//                new CharacterRelationshipEntity(15L,13L,"Assassin","lighblue")
//        ));
//        return "Seeded";
//    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterRelationshipEntity> getById(@PathVariable Long id) {
        return characterRelationshipRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Map<String,Object>> nested() {
        return characterRelationshipService.nested();
    }

}
