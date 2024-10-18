package com.quefaire.demo.controller;

import com.quefaire.demo.entity.Event;
import com.quefaire.demo.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class EventController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/api/events")
    public List<Event> eventSummary() {
        return eventService.getAllEvents();
    }

    @GetMapping(value = "/api/events/{id}")
    public ResponseEntity<Object> eventDetails(@PathVariable String id) {
        Event event = eventService.getEvent(id);
        if (event == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(event);
    }

    @PostMapping(value = "/api/events")
    public ResponseEntity<Event> createEvent(@RequestBody Event newEvent) {
        Event event = eventService.saveEvent(newEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }
}
