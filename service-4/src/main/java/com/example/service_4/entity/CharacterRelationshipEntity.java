package com.example.service_4.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "character")
public class CharacterRelationshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "parent_id")
    private Long parentId;
    private String name;
    private String color;

    public CharacterRelationshipEntity() {

    }
    public CharacterRelationshipEntity(long id, long parentId, String name, String color) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
