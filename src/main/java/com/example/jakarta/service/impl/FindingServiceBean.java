package com.example.jakarta.service.impl;

import com.example.jakarta.model.Finding;
import com.example.jakarta.model.impl.DefaultFinding;
import com.example.jakarta.service.FindingService;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class FindingServiceBean implements FindingService {

    private final List<DefaultFinding> findings = new ArrayList<>();
    private long idCounter = 1L;

    @Override
    public void addFinding(DefaultFinding finding) {
        finding.setId(idCounter++);
        findings.add(finding);
    }

    @Override
    public void removeFinding(Long id) {
        findings.removeIf(f -> f.getId().equals(id));
    }

    @Override
    public List<Finding> findAll() {
        return new ArrayList<>(findings);
    }

    @Override
    public Finding findById(Long id) {
        return findings.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<DefaultFinding> searchByKeywords(List<String> keywords) {
        return findings.stream()
                .filter(f -> f.getKeywords() != null && f.getKeywords().stream().anyMatch(keywords::contains))
                .collect(Collectors.toList());
    }
}