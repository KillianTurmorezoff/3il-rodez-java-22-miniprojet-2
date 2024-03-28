package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        // Initialisation des structures de données
        Map<Noeud<E>, Double> couts = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>((n1, n2) -> {
            double diff = couts.get(n1) - couts.get(n2);
            if (diff == 0)
                return 0;
            return diff < 0 ? -1 : 1;
        });

        // Initialiser les coûts des nœuds
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            couts.put(noeud, Double.POSITIVE_INFINITY);
            predecesseurs.put(noeud, null);
        }
        couts.put(depart, 0.0); // Coût du départ est 0

        filePriorite.offer(depart); // Ajouter le nœud de départ dans la file de priorité

        // Exploration des nœuds
        while (!filePriorite.isEmpty()) {
            Noeud<E> noeudActuel = filePriorite.poll(); // Retirer le nœud avec le coût minimum
            if (noeudActuel.equals(arrivee))
                break; // Arrêter si on atteint le nœud d'arrivée

            // Explorer les voisins du nœud actuel
            for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
                double nouveauCout = couts.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);
                if (nouveauCout < couts.get(voisin)) {
                    couts.put(voisin, nouveauCout);
                    predecesseurs.put(voisin, noeudActuel);
                    filePriorite.offer(voisin); // Mettre à jour la file de priorité avec le nouveau coût
                }
            }
        }

        // Reconstruction du chemin le plus court
        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> noeud = arrivee;
        while (noeud != null) {
            chemin.add(0, noeud); // Ajouter le nœud au début de la liste (pour obtenir l'ordre correct)
            noeud = predecesseurs.get(noeud);
        }

        return chemin;
    }
}
