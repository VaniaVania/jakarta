package com.example.jakarta.service.impl;

import com.example.jakarta.exceptions.TitleBlankException;
import com.example.jakarta.model.Finding;
import com.example.jakarta.service.dao.FindingDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Objects;

@Stateless
public class FindingService {

    @Inject
    private FindingDAO dao;

    @Transactional()
    public Finding createFinding(Finding finding) {

        if (Objects.nonNull(finding.getContactInfo())) {
            dao.createContactInfo(finding.getContactInfo());
        }
        dao.create(finding);

        if(finding.getTitle().isBlank()){
            throw new TitleBlankException();
        }
        return finding;
    }

    public Finding getFinding(Long id) {
        return dao.read(id);
    }

    public Finding updateFinding(Long id, Finding finding) {
        Finding dbFinding = dao.read(id);
        finding.setId(dbFinding.getId());
        finding.getContactInfo()
                .setId(dbFinding.getContactInfo().getId());

        dao.update(finding);
        return finding;
    }

    public void deleteFinding(Long id) {
        dao.delete(id);
    }

    public List<Finding> searchByKeyword(String keyword) {
        return dao.findByKeyword(keyword);
    }


}