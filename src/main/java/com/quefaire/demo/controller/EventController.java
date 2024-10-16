package com.quefaire.demo.controller;

import com.quefaire.demo.entity.Event;
import com.quefaire.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/evenements")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvenements() {
        return eventService.getAllEvenements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEvenementById(@PathVariable String id) {
        Event event = eventService.getEvenementById(id);
        if (event == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(event);
    }
}
