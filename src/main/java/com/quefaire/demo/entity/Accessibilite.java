package com.quefaire.demo.entity;
import jakarta.persistence.*;



@Entity
public class Accessibilite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean pmr;
    private Boolean blind;
    private Boolean deaf;

    @OneToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Evenement evenement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPmr() {
        return pmr;
    }

    public void setPmr(Boolean pmr) {
        this.pmr = pmr;
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

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
}