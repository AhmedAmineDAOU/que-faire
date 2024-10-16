package com.quefaire.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {

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


    // Relation OneToOne unidirectionnelle avec Pricing
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pricing_id")  // Clé étrangère vers Pricing
    private Pricing pricing;


    private String accessType;
    private String audience;
    private String locale;

    // Relation OneToOne unidirectionnelle avec Location
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")  // Clé étrangère vers Location
    private Location location;

    // Relation OneToOne avec Contact
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    // Relation Many-to-Many avec Tag
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "event_tags",  // Nom de la table de jointure
            joinColumns = @JoinColumn(name = "event_id"),  // Clé étrangère vers Event
            inverseJoinColumns = @JoinColumn(name = "tag_id")  // Clé étrangère vers Tag
    )
    private List<Tag> tags;

    // Relation Many-to-One avec Accessibility
    @ManyToOne
    @JoinColumn(name = "accessibility_id")  // Clé étrangère vers Accessibilite
    private Accessibility accessibility;


    public Event() {
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

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
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

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Accessibility getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Accessibility accessibility) {
        this.accessibility = accessibility;
    }
}