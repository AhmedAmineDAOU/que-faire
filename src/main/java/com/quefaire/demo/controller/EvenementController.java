package com.quefaire.demo.controller;

import com.quefaire.demo.entity.Evenement;
import com.quefaire.demo.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/evenements")
public class EvenementController {

    private final EvenementService evenementService;

    @Autowired
    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    @GetMapping
    public List<Evenement> getAllEvenements() {
        return evenementService.getAllEvenements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEvenementById(@PathVariable String id) {
        Evenement evenement = evenementService.getEvenementById(id);
        if (evenement == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(evenement);
    }
}
