package fr.ecole3il.rodez2023.carte.elements;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AlgorithmeChemin<E> {

    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        // Liste pour stocker les nœuds déjà visités
        List<Noeud<E>> visites = new ArrayList<>();
        
        // File pour le parcours en largeur (BFS)
        Queue<Noeud<E>> file = new LinkedList<>();
        file.offer(depart); // Ajouter le nœud de départ dans la file
        
        while (!file.isEmpty()) {
            Noeud<E> courant = file.poll(); // Retirer le premier nœud de la file
            visites.add(courant); // Marquer le nœud comme visité
            
            if (courant.equals(arrivee)) {
                // Si le nœud courant est le nœud d'arrivée, renvoyer les nœuds visités comme chemin
                return visites;
            }
            
            // Explorer les voisins du nœud courant
            for (Noeud<E> voisin : graphe.getVoisins(courant)) {
                if (!visites.contains(voisin)) {
                    // Si le voisin n'a pas été visité, l'ajouter à la file
                    file.offer(voisin);
                }
            }
        }
        
        // Si aucun chemin n'a été trouvé, renvoyer une liste vide
        return new ArrayList<>();
    }
}
