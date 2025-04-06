package com.example.jakarta.service;

import com.example.jakarta.model.Finding;
import com.example.jakarta.model.impl.DefaultFinding;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface FindingService {
    void addFinding(DefaultFinding finding);
    void removeFinding(Long id);
    List<Finding> findAll();
    Finding findById(Long id);
    List<DefaultFinding> searchByKeywords(List<String> keywords);
}