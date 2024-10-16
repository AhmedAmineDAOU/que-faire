package com.quefaire.demo.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "accessibility", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"blind", "deaf", "pmr"})
})
public class Accessibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean blind;
    private Boolean deaf;
    private Boolean pmr;

    // Relation One-to-Many avec Event
    @OneToMany(mappedBy = "accessibility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> events;

    // Constructeurs, getters et setters
    public Accessibility() {}

    public Accessibility(Boolean blind, Boolean deaf, Boolean pmr) {
        this.blind = blind;
        this.deaf = deaf;
        this.pmr = pmr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getBlind() {
        return blind;
    }

    public void setBlind(Boolean blind) {
        this.blind = blind;
    }

    public Boolean getDeaf() {
        return deaf;
    }

    public void setDeaf(Boolean deaf) {
        this.deaf = deaf;
    }

    public Boolean getPmr() {
        return pmr;
    }

    public void setPmr(Boolean pmr) {
        this.pmr = pmr;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}