package com.example.jakarta.service.dao;

import com.example.jakarta.model.Finding;
import com.example.jakarta.model.ContactInfo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class FindingDAO {

    @PersistenceContext(unitName = "found-items-pu")
    private EntityManager em;

    public void create(Finding f) {
        em.persist(f);
    }

    public void createContactInfo(ContactInfo contactInfo) {
        em.persist(contactInfo);
    }

    public Finding read(Long id) {
        return em.find(Finding.class, id);
    }

    public void update(Finding f) {
        em.merge(f);
    }

    public void delete(Long id) {
        em.remove(read(id));
    }

    public List<Finding> findByKeyword(String keyword) {
        return em.createQuery(
                        "SELECT f FROM Finding f WHERE :keyword MEMBER OF f.keywords", Finding.class)
                .setParameter("keyword", keyword)
                .getResultList();
    }


}
