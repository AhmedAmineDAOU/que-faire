package com.quefaire.demo.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "accessibilite", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"blind", "deaf", "pmr"})
})
public record Accessibilite (
    @Id
    Long id,
    Boolean blind,
    Boolean deaf,
    Boolean pmr
){}