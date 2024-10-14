package com.quefaire.demo.service;

import com.quefaire.demo.entity.Evenement;
import com.quefaire.demo.repository.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
