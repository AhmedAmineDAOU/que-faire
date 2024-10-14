package com.quefaire.demo.service;
import com.quefaire.demo.entity.Evenement;
import com.quefaire.demo.repository.EvenementRepository;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private EvenementRepository evenementRepository;  // cree une simulation que mon service utilise dans ce cas:  le repository

    @InjectMocks
    private EvenementService evenementService;  // cree une instance du service et injecte dans cet objet les dependances dans ce cas le repo

    // Test pour le cas où l'événement est trouvé par ID
    @Test
    public void testGetEvenementById_Success() {
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
    public void testGetEvenementById_NotFound() {
        // Simule que le repository ne trouve pas d'événement (renvoie Optional.empty)
        when(evenementRepository.findById("1")).thenReturn(Optional.empty());

        // Appelle la méthode du service
        Evenement evenement = evenementService.getEvenementById("1");

        // Vérifie que le service retourne null
        assertNull(evenement);

        // Vérifie que la méthode findById a bien été appelée
        verify(evenementRepository, times(1)).findById("1");
    }
}