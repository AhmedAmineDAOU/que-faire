package com.quefaire.demo.entity;
import jakarta.persistence.*;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contactUrl;
    private String contactPhone;
    private String contactMail;
    private String contactFacebook;
    private String contactTwitter;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Evenement evenement;

    // Getters et Setters
}