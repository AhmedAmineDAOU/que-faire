package com.quefaire.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Localisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressName;
    private String addressStreet;
    private String addressZipcode;
    private String addressCity;
    private Double latitude;
    private Double longitude;

    @OneToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Evenement evenement;

    // Getters et Setters
}
