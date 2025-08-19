package com.example.service_4;

import com.example.service_4.entity.CharacterRelationshipEntity;
import com.example.service_4.repository.CharacterRelationshipRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataSeeder {
    @Bean
    public CommandLineRunner loadData(CharacterRelationshipRepository repo) {
        return args -> {
            if (repo.count() == 0) { // only seed if empty
                repo.saveAll(List.of(
                        new CharacterRelationshipEntity(1L, 0L, "Warrior", "red"),
                        new CharacterRelationshipEntity(2L, 0L, "Wizard", "green"),
                        new CharacterRelationshipEntity(3L, 0L, "Priest", "white"),
                        new CharacterRelationshipEntity(4L, 0L, "Rogue", "yellow"),
                        new CharacterRelationshipEntity(5L, 1L, "Fighter", "blue"),
                        new CharacterRelationshipEntity(6L, 1L, "Paladin", "lightblue"),
                        new CharacterRelationshipEntity(7L, 1L, "Ranger", "lightgreen"),
                        new CharacterRelationshipEntity(8L, 2L, "Mage", "grey"),
                        new CharacterRelationshipEntity(9L, 2L, "Specialist wizard", "lightgrey"),
                        new CharacterRelationshipEntity(10L, 3L, "Cleric", "red"),
                        new CharacterRelationshipEntity(11L, 3L, "Druid", "green"),
                        new CharacterRelationshipEntity(12L, 3L, "Priest of specific mythos", "white"),
                        new CharacterRelationshipEntity(13L, 4L, "Thief", "yellow"),
                        new CharacterRelationshipEntity(14L, 4L, "Bard", "blue"),
                        new CharacterRelationshipEntity(15L, 13L, "Assassin", "lightblue")
                ));
            }
        };
    }
}
