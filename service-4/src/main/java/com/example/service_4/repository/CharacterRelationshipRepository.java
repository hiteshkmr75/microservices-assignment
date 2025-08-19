package com.example.service_4.repository;

import com.example.service_4.entity.CharacterRelationshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRelationshipRepository extends JpaRepository<CharacterRelationshipEntity, Long> {
}
