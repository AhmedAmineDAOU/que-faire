package com.quefaire.demo.service;

import com.quefaire.demo.entity.*;
import com.quefaire.demo.repository.EvenementRepository;
import com.quefaire.demo.util.CollectionsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EvenementService {


    private final EvenementRepository evenementRepository;

    /**
     * Le passage de l'injection par champ (field injection) à l'injection par constructeur
     * <p>
     * 1. **Immutabilité** : En déclarant les dépendances comme `final`, nous garantissons que
     * les dépendances injectées (comme `EvenementRepository`) ne seront jamais réassignées après
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
    public EvenementService(EvenementRepository evenementRepository) {
        this.evenementRepository = evenementRepository;
    }

    public List<Evenement> getAllEvenements() {
        return evenementRepository.findAll();
    }

    public Evenement getEvenementById(String id) {
        return evenementRepository.findById(id).orElse(null);
    }
    // Autres méthodes pour ajouter, mettre à jour, supprimer un événement à venir.

    // Fonction de mise à jour d'un événement
    public Evenement updateEvenement(String id, Evenement updatedEvenement) {
        // Vérifier si l'événement avec cet ID existe
        return evenementRepository.findById(id)
                .map(existingEvenement -> {
                    // Appliquer les modifications sur l'événement existant
                    existingEvenement.setTitle(updatedEvenement.getTitle());
                    existingEvenement.setLeadText((updatedEvenement.getLeadText()));
                    existingEvenement.setDescription(updatedEvenement.getDescription());
                    existingEvenement.setDateStart(updatedEvenement.getDateStart());
                    existingEvenement.setDateEnd(updatedEvenement.getDateEnd());
                    existingEvenement.setDateDescription(updatedEvenement.getDateDescription());
                    existingEvenement.setCoverUrl(updatedEvenement.getCoverUrl());
                    existingEvenement.setCoverCredit(updatedEvenement.getCoverCredit());
                    existingEvenement.setPriceType(updatedEvenement.getPriceType());
                    existingEvenement.setPriceDetail(updatedEvenement.getPriceDetail());
                    existingEvenement.setPriceType(updatedEvenement.getPriceType());
                    existingEvenement.setAudience(updatedEvenement.getAudience());
                    existingEvenement.setLocale(updatedEvenement.getLocale());
                    existingEvenement.setAccessType(updatedEvenement.getAccessType());
                    existingEvenement.setLocalisation(updatedEvenement.getLocalisation());
                    existingEvenement.setAccessibilite(updatedEvenement.getAccessibilite());
                    CollectionsUtil.updateList(existingEvenement.getContacts(), updatedEvenement.getContacts(), Contact::getContactPhone);
                    CollectionsUtil.updateList(existingEvenement.getTags(), updatedEvenement.getTags(), Tag::getName);
                    return evenementRepository.save(existingEvenement);
                }).orElseThrow(() -> new NoSuchElementException("Événement introuvable avec l'ID : " + id));
    }
}
