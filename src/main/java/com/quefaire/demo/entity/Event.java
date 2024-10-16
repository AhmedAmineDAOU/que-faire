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


    // Relation OneToOne unidirectionnelle avec Location
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pricing_id")  // Clé étrangère vers Location
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


}