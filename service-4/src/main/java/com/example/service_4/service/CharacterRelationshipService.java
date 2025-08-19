package com.example.service_4.service;

import com.example.service_4.entity.CharacterRelationshipEntity;
import com.example.service_4.repository.CharacterRelationshipRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CharacterRelationshipService {
    private final CharacterRelationshipRepository repo;

    public CharacterRelationshipService(CharacterRelationshipRepository repo) { this.repo = repo; }

    public List<Map<String,Object>> nested() {
        List<CharacterRelationshipEntity> all = repo.findAll();
        Map<Long, List<CharacterRelationshipEntity>> children = all.stream()
                .collect(Collectors.groupingBy(n -> n.getParentId() == null ? 0L : n.getParentId()));

        return children.getOrDefault(0L, List.of()).stream()
                .map(r -> buildTree(r, children)).collect(Collectors.toList());
    }

    private Map<String,Object> buildTree(CharacterRelationshipEntity node, Map<Long,List<CharacterRelationshipEntity>> children) {
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("Name", node.getName());
        map.put("Color", node.getColor());
        List<Map<String,Object>> subs = children.getOrDefault(node.getId(), List.of())
                .stream().map(c -> buildTree(c, children)).collect(Collectors.toList());
        if (!subs.isEmpty()) map.put("Sub Classes", subs);
        return map;
    }

}
