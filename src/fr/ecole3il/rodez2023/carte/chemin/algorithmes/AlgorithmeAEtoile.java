package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.*;

import java.util.*;

public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Double> coutActuel = new HashMap<>();
        Map<Noeud<E>, Double> coutEstime = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();

        PriorityQueue<Noeud<E>> aExplorer = new PriorityQueue<>(Comparator.comparingDouble(coutEstime::get));
        Set<Noeud<E>> dejaExplores = new HashSet<>();

        // Initialisation des coûts
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            coutActuel.put(noeud, Double.POSITIVE_INFINITY);
            coutEstime.put(noeud, estimerCout(noeud, arrivee));
            predecesseurs.put(noeud, null);
        }

        coutActuel.put(depart, 0.0);
        aExplorer.add(depart);

        while (!aExplorer.isEmpty()) {
            Noeud<E> courant = aExplorer.poll();

            if (courant == arrivee) {
                // Reconstruction du chemin
                return reconstruireChemin(predecesseurs, arrivee);
            }

            dejaExplores.add(courant);

            for (Noeud<E> voisin : graphe.getVoisins(courant)) {
                if (dejaExplores.contains(voisin)) continue;

                double nouveauCout = coutActuel.get(courant) + graphe.getCoutArete(courant, voisin);
                if (nouveauCout < coutActuel.get(voisin)) {
                    predecesseurs.put(voisin, courant);
                    coutActuel.put(voisin, nouveauCout);
                    double coutTotalEstime = nouveauCout + estimerCout(voisin, arrivee);
                    coutEstime.put(voisin, coutTotalEstime);
                    aExplorer.add(voisin);
                }
            }
        }

        // Aucun chemin trouvé
        return null;
    }

    // Estimation du coût restant jusqu'à l'arrivée (heuristique)
    private double estimerCout(Noeud<E> noeud, Noeud<E> arrivee) {
        // Implémentez votre propre heuristique ici
        // Pour l'exemple, je vais simplement retourner une valeur fixe
        return 1.0;
    }

    // Reconstruction du chemin à partir des prédécesseurs
    private List<Noeud<E>> reconstruireChemin(Map<Noeud<E>, Noeud<E>> predecesseurs, Noeud<E> arrivee) {
        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> courant = arrivee;
        while (courant != null) {
            chemin.add(courant);
            courant = predecesseurs.get(courant);
        }
        Collections.reverse(chemin);
        return chemin;
    }
}
