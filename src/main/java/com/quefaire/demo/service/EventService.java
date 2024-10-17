package com.quefaire.demo.service;

import com.quefaire.demo.entity.*;
import com.quefaire.demo.repository.EventRepository;
import com.quefaire.demo.util.CollectionsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EventService {


    private final EventRepository eventRepository;

    /**
     * Le passage de l'injection par champ (field injection) à l'injection par constructeur
     * <p>
     * 1. **Immutabilité** : En déclarant les dépendances comme `final`, nous garantissons que
     * les dépendances injectées (comme `EventRepository`) ne seront jamais réassignées après
     * l'instanciation de la classe, ce qui améliore la sécurité et la robustesse du code.
     * <p>
     * 2. **Testabilité** : L'injection par constructeur facilite l'écriture de tests unitaires,
     * car elle permet de créer manuellement des instances de cette classe avec des dépendances simulées (mock).
     * <p>
     * 3. **Fiabilité** : Cela garantit que toutes les dépendances nécessaires sont fournies
     * lors de l'instanciation, évitant les erreurs potentielles liées à des dépendances manquantes.
     * <p>
     * 4. **Meilleures pratiques** : L'injection par constructeur est recommandée par rapport à l'injection
     * par champ car elle favorise une conception plus claire, améliore la maintenabilité et respecte
     * les principes d'encapsulation.
     */


    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEvent(String id) {
        return eventRepository.findById(id).orElse(null);
    }
    // Autres méthodes pour ajouter, mettre à jour, supprimer un événement à venir.

    // Fonction de mise à jour d'un événement
    public Event updateEvent(String id, Event updatedEvent) {
        // Vérifier si l'événement avec cet ID existe
        return eventRepository.findById(id)
                .map(existingEvenement -> {
                    // Appliquer les modifications sur l'événement existant
                    existingEvenement.setTitle(updatedEvent.getTitle());
                    existingEvenement.setLeadText((updatedEvent.getLeadText()));
                    existingEvenement.setDescription(updatedEvent.getDescription());
                    existingEvenement.setDateStart(updatedEvent.getDateStart());
                    existingEvenement.setDateEnd(updatedEvent.getDateEnd());
                    existingEvenement.setDateDescription(updatedEvent.getDateDescription());
                    existingEvenement.setCoverUrl(updatedEvent.getCoverUrl());
                    existingEvenement.setCoverCredit(updatedEvent.getCoverCredit());
                    existingEvenement.setPricing(updatedEvent.getPricing());
                    existingEvenement.setAudience(updatedEvent.getAudience());
                    existingEvenement.setLocale(updatedEvent.getLocale());
                    existingEvenement.setAccessType(updatedEvent.getAccessType());
                    existingEvenement.setLocation(updatedEvent.getLocation());
                    existingEvenement.setAccessibility(updatedEvent.getAccessibility());
                    existingEvenement.setContact(updatedEvent.getContact());
                    CollectionsUtil.updateList(existingEvenement.getTags(), updatedEvent.getTags(), Tag::getName);
                    return eventRepository.save(existingEvenement);
                }).orElseThrow(() -> new NoSuchElementException("Événement introuvable avec l'ID : " + id));
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }
}
