package com.quefaire.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class CollectionsUtil {
    private CollectionsUtil() {
    }

    /**
     * Méthode générique pour mettre à jour une collection OneToMany ou ManyToMany.
     *
     * @param existingList     La liste existante de l'entité (par exemple, List<Contact>, List<Tag>).
     * @param updatedList      La liste mise à jour contenant les nouvelles données.
     * @param identityFunction Fonction permettant d'identifier chaque élément (par exemple, par son ID ou une clé unique).
     * @param <T>              Le type générique des éléments dans la liste.
     */
    public static <T> void updateList(List<T> existingList, List<T> updatedList, Function<T, Object> identityFunction) {
        if (existingList == null) {
            existingList = new ArrayList<>();
        }
        if (updatedList == null) {
            updatedList = new ArrayList<>();
        }
        // Ajouter les nouveaux éléments
        for (T updatedItem : updatedList) {
            // si le nouveau element n'existe pas dans la liste
            if (existingList.stream().noneMatch(e -> Objects.equals(identityFunction.apply(e), identityFunction.apply(updatedItem)))) {
                existingList.add(updatedItem);
            }
        }
        // Supprimer les éléments qui ne sont plus présents
        List<T> finalUpdatedList = updatedList;
        existingList.removeIf(existingItem -> finalUpdatedList.stream()
                .noneMatch(itUpdateEvenement -> Objects.equals(identityFunction.apply(itUpdateEvenement), identityFunction.apply(existingItem))));
    }
}
