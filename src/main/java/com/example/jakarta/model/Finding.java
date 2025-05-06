package com.example.jakarta.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "finding")
public class Finding {

    @Id
    @GeneratedValue
    private Long id;

    @Nonnull
    private String title;

    @Nonnull
    private String description;

    @ElementCollection
    private List<String> keywords;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_info_id")
    private ContactInfo contactInfo;

    public Finding() {}

    public Finding(@Nonnull String title, @Nonnull String description, List<String> keywords, ContactInfo contactInfo) {
        this.title = title;
        this.description = description;
        this.keywords = keywords;
        this.contactInfo = contactInfo;
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

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}
