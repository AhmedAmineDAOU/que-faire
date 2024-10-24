package com.quefaire.demo.controller;

import com.quefaire.demo.entity.Event;
import com.quefaire.demo.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EventController.class)
class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @BeforeEach
    public void resetMocks() {
        // Réinitialiser les mocks avant chaque test
        reset(eventService);
    }

    @Test
    void getEventByIdIfEventFound() throws Exception {
        // Simule le comportement du service pour renvoyer un événement lorsque l'ID "1" est demandé
        Event mockEvent = new Event();
        mockEvent.setId("1");
        mockEvent.setTitle("Titre de l'événement");
        when(eventService.getEvent("1")).thenReturn(mockEvent);
        // Simule une requête GET à /events/1 et vérifie la réponse
        mockMvc.perform(get("/api/events/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Vérifie que le statut est 200 OK
                .andExpect(jsonPath("$.id").value("1"))  // Vérifie que le champ "id" est bien "1"
                .andExpect(jsonPath("$.title").value("Titre de l'événement"));  // Vérifie le titre de l'événement
    }

    @Test
    void getEventByIdIfEventNotFound() throws Exception {
        // Simule le comportement du service pour renvoyer null (l'événement n'existe pas)
        when(eventService.getEvent("1")).thenReturn(null);
        // Simule une requête GET à /events/1 et vérifie la réponse 404 Not Found
        mockMvc.perform(get("/api/events/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());  // Vérifie que le statut est 404 Not Found
    }

    @Test
    void createEvent() throws Exception {
        Event mockEvent = new Event();
        mockEvent.setId("1");
        mockEvent.setTitle("Event title");
        when(eventService.saveEvent(any(Event.class))).thenReturn(mockEvent);
        String eventJson = "{\"id\": \"1\", \"title\": \"Event title\"}";
        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventJson))
                .andExpect(jsonPath("$.id").value("1"))  // Verify the ID in the response
                .andExpect(jsonPath("$.title").value("Event title"));  // Verify the title in the response
        verify(eventService, times(1)).saveEvent(any(Event.class));
    }


}