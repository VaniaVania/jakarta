package com.example.jakarta.model.impl;

import com.example.jakarta.model.ContactInfo;
import com.example.jakarta.model.Finding;

import java.util.List;

public class DefaultFinding implements Finding {
    private Long id;
    private String title;
    private String description;
    private List<String> keywords;
    private ContactInfo defaultContactInfo;

    public DefaultFinding(Long id, String title, String description, List<String> keywords, ContactInfo defaultContactInfo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.keywords = keywords;
        this.defaultContactInfo = defaultContactInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public ContactInfo getDefaultContactInfo() {
        return defaultContactInfo;
    }

    public void setDefaultContactInfo(ContactInfo defaultContactInfo) {
        this.defaultContactInfo = defaultContactInfo;
    }
}
