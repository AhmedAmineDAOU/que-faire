package com.quefaire.demo.service;

import com.quefaire.demo.entity.Evenement;
import com.quefaire.demo.repository.EvenementRepository;
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
class EvenementServiceTest {

    @Mock
    private EvenementRepository evenementRepository;  // cree une simulation de ma repository

    @InjectMocks
    private EvenementService evenementService;  // cree une instance du service et injecte dans cet objet les dependances dans ce cas le repo

    // Test pour le cas où l'événement est trouvé par ID
    @Test
    public void testGetEvenementByIdFound() {
        // Simule un événement trouvé par le repository
        Evenement mockEvenement = new Evenement("1", "Titre de l'événement");
        when(evenementRepository.findById("1")).thenReturn(Optional.of(mockEvenement));
        // Appelle la méthode du service et vérifie le résultat
        Evenement evenement = evenementService.getEvenementById("1");
        // Vérifie que l'événement retourné est correct
        assertNotNull(evenement);
        assertEquals("1", evenement.getId());
        assertEquals("Titre de l'événement", evenement.getTitle());
        // Vérifie que la méthode findById a bien été appelée une seule fois
        verify(evenementRepository, times(1)).findById("1");
    }

    // Test pour le cas où l'événement n'est pas trouvé par ID
    @Test
    public void testGetEvenementByIdNotFound() {
        // Simule que le repository ne trouve pas d'événement (renvoie Optional.empty)
        when(evenementRepository.findById("1")).thenReturn(Optional.empty());
        // Appelle la méthode du service
        Evenement evenement = evenementService.getEvenementById("1");
        // Vérifie que le service retourne null
        assertNull(evenement);
        // Vérifie que la méthode findById a bien été appelée
        verify(evenementRepository, times(1)).findById("1");
    }

    @Test
    public void testUpdateEvenementSuccess() {
        //given
        String eventId = "123";
        Evenement existingEvenement = new Evenement();
        existingEvenement.setId(eventId);
        existingEvenement.setTitle("Old Title");
        existingEvenement.setLeadText("Old Lead Text");
        Evenement updatedEvenement = new Evenement();
        updatedEvenement.setTitle("New Title");
        updatedEvenement.setLeadText("New Lead Text");

        /* dis à Mockito que lorsque le repository appelle la méthode findById() avec l'ID eventId,
        il doit simuler le retour de l'événement existant existingEvenement
         */
        when(evenementRepository.findById(eventId)).thenReturn(Optional.of(existingEvenement));

        /* dis à mockito de capturer tout save() executee sur les objet de class Evenement et
            retourner l'objet existingEvenement pour verifier sa mise a jour
         */
        when(evenementRepository.save(any(Evenement.class))).thenReturn(existingEvenement);
        // executer la methode de mon service
        Evenement result = evenementService.updateEvenement(eventId, updatedEvenement);
        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
        assertEquals("New Lead Text", result.getLeadText());
        verify(evenementRepository, times(1)).findById(eventId);
        verify(evenementRepository, times(1)).save(any(Evenement.class));
    }

    @Test
    public void testUpdateNonExistingEvenementShouldThrowException() {
        when(evenementRepository.findById(anyString())).thenReturn(Optional.empty());
        Evenement e = new Evenement();
        String eventId = "123";
        e.setTitle("New Title");
        e.setLeadText("New Lead Text");
        assertThrows(NoSuchElementException.class, () -> {
            evenementService.updateEvenement(eventId, e);
        });
        verify(evenementRepository, times(1)).findById(eventId);
        verify(evenementRepository, never()).save(any(Evenement.class));
    }

}