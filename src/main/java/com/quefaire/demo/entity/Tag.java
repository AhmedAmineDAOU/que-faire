package com.quefaire.demo.entity;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Par exemple, "Expo"

    @ManyToMany(mappedBy = "tags")
    private List<Evenement> evenements;

    // Getters et Setters
}
