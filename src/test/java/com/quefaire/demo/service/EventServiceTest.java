package com.quefaire.demo.service;

import com.quefaire.demo.entity.Event;
import com.quefaire.demo.repository.EventRepository;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
    Utilise l'annotation @ExtendWith(MockitoExtension.class) pour activer Mockito dans JUnit 5.
    Cette annotation permet d'initialiser les mocks (@Mock) et d'injecter correctement les mocks dans
    les objets annotés avec @InjectMocks.
 */
@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;  // cree une simulation de ma repository

    @InjectMocks
    private EventService eventService;  // cree une instance du service et injecte dans cet objet les dependances dans ce cas le repo

    // Test pour le cas où l'événement est trouvé par ID
    @Test
    public void testGetEvenementByIdFound() {
        // Simule un événement trouvé par le repository
        Event mockEvent = new Event();
        mockEvent.setId("1");
        mockEvent.setTitle("Titre de l'événement");
        when(eventRepository.findById("1")).thenReturn(Optional.of(mockEvent));
        // Appelle la méthode du service et vérifie le résultat
        Event event = eventService.getEvenementById("1");
        // Vérifie que l'événement retourné est correct
        assertNotNull(event);
        assertEquals("1", event.getId());
        assertEquals("Titre de l'événement", event.getTitle());
        // Vérifie que la méthode findById a bien été appelée une seule fois
        verify(eventRepository, times(1)).findById("1");
    }

    // Test pour le cas où l'événement n'est pas trouvé par ID
    @Test
    public void testGetEvenementByIdNotFound() {
        // Simule que le repository ne trouve pas d'événement (renvoie Optional.empty)
        when(eventRepository.findById("1")).thenReturn(Optional.empty());
        // Appelle la méthode du service
        Event event = eventService.getEvenementById("1");
        // Vérifie que le service retourne null
        assertNull(event);
        // Vérifie que la méthode findById a bien été appelée
        verify(eventRepository, times(1)).findById("1");
    }

    @Test
    public void testUpdateEvenementSuccess() {
        //given
        String eventId = "123";
        Event existingEvent = new Event();
        existingEvent.setId(eventId);
        existingEvent.setTitle("Old Title");
        existingEvent.setLeadText("Old Lead Text");
        Event updatedEvent = new Event();
        updatedEvent.setTitle("New Title");
        updatedEvent.setLeadText("New Lead Text");

        /* dis à Mockito que lorsque le repository appelle la méthode findById() avec l'ID eventId,
        il doit simuler le retour de l'événement existant existingEvent
         */
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(existingEvent));

        /* dis à mockito de capturer tout save() executee sur les objet de class Event et
            retourner l'objet existingEvent pour verifier sa mise a jour
         */
        when(eventRepository.save(any(Event.class))).thenReturn(existingEvent);
        // executer la methode de mon service
        Event result = eventService.updateEvenement(eventId, updatedEvent);
        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
        assertEquals("New Lead Text", result.getLeadText());
        verify(eventRepository, times(1)).findById(eventId);
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    public void testUpdateNonExistingEvenementShouldThrowException() {
        when(eventRepository.findById(anyString())).thenReturn(Optional.empty());
        Event e = new Event();
        String eventId = "123";
        e.setTitle("New Title");
        e.setLeadText("New Lead Text");
        assertThrows(NoSuchElementException.class, () -> {
            eventService.updateEvenement(eventId, e);
        });
        verify(eventRepository, times(1)).findById(eventId);
        verify(eventRepository, never()).save(any(Event.class));
    }

}