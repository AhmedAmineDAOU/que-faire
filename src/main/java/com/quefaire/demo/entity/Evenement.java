package com.quefaire.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;



@Entity
public class Evenement {

    @Id
    private String id; // Correspond à l'ID unique dans le JSON ou GeoJSON.

    private String title;

    @Lob
    private String leadText;

    @Lob
    private String description;

    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;

    private String dateDescription;
    private String coverUrl;
    private String coverCredit;
    private String priceType;

    @Lob
    private String priceDetail;

    private String accessType;
    private String audience;
    private String locale;

    @OneToOne(mappedBy = "evenement", cascade = CascadeType.ALL)
    private Localisation localisation;

    @OneToMany(mappedBy = "evenement", cascade = CascadeType.ALL)
    private List<Contact> contacts;

    @ManyToMany
    @JoinTable(name = "evenement_tags",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @OneToOne(mappedBy = "evenement", cascade = CascadeType.ALL)
    private Accessibilite accessibilite;

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLeadText() {
        return leadText;
    }

    public void setLeadText(String leadText) {
        this.leadText = leadText;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateDescription() {
        return dateDescription;
    }

    public void setDateDescription(String dateDescription) {
        this.dateDescription = dateDescription;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCoverCredit() {
        return coverCredit;
    }

    public void setCoverCredit(String coverCredit) {
        this.coverCredit = coverCredit;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getPriceDetail() {
        return priceDetail;
    }

    public void setPriceDetail(String priceDetail) {
        this.priceDetail = priceDetail;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Accessibilite getAccessibilite() {
        return accessibilite;
    }

    public void setAccessibilite(Accessibilite accessibilite) {
        this.accessibilite = accessibilite;
    }
}