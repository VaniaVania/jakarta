package com.example.jakarta.service.impl;

import com.example.jakarta.model.Finding;
import com.example.jakarta.service.dao.FindingDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@Stateless
public class FindingService {

    @Inject
    private FindingDAO dao;

    @Transactional
    public Finding createFinding(Finding finding) {
        if (finding.getContactInfo() != null && finding.getContactInfo().getId() == null) {
            dao.createContactInfo(finding.getContactInfo());
        }
        dao.create(finding);
        return finding;
    }

    public Finding getFinding(Long id) {
        return dao.read(id);
    }

    public Finding updateFinding(Long id, Finding finding) {
        Finding dbFinding = dao.read(id);
        finding.setId(dbFinding.getId());
        finding.getContactInfo().setId(dbFinding.getContactInfo().getId());

        dao.update(finding);
        return finding;
    }

    public List<Finding> searchByKeyword(String keyword) {
        return dao.findByKeyword(keyword);
    }

    public List<Finding> getAllFindings(int page, int size) {
        return dao.findAll(page, size);
    }

    public List<Finding> searchByKeywordWithPagination(String keyword, int page, int size) {
        return dao.findByKeywordWithPagination(keyword, page, size);
    }

    public boolean deleteFinding(Long id) {
        return dao.deleteIfExists(id);
    }


}