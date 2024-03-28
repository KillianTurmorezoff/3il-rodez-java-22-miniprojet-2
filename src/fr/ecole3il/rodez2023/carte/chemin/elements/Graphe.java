package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graphe<E> {
    private List<Noeud<E>> noeuds;
    private Map<Noeud<E>, Map<Noeud<E>, Double>> matriceAdjacence;

    public Graphe() {
        this.noeuds = new ArrayList<>();
        this.matriceAdjacence = new HashMap<>();
    }

    public void ajouterNoeud(Noeud<E> noeud) {
        if (!noeuds.contains(noeud)) {
            noeuds.add(noeud);
            matriceAdjacence.put(noeud, new HashMap<>());
        }
    }

    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        // Vérifier si les nœuds de départ et d'arrivée existent dans le graphe
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);

        // Ajouter l'arête pondérée à la matrice d'adjacence du nœud de départ
        matriceAdjacence.get(depart).put(arrivee, cout);
    }

    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        // Vérifier si les nœuds de départ et d'arrivée existent dans la matrice d'adjacence
        if (matriceAdjacence.containsKey(depart) && matriceAdjacence.get(depart).containsKey(arrivee)) {
            // Renvoyer le coût de l'arête entre les nœuds spécifiés
            return matriceAdjacence.get(depart).get(arrivee);
        } else {
            // Si l'arête n'existe pas, renvoyer une valeur indiquant l'absence de cette arête (par exemple Double.POSITIVE_INFINITY)
            return Double.POSITIVE_INFINITY; // Vous pouvez également renvoyer une autre valeur pour indiquer l'absence de l'arête
        }
    }

    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        if (matriceAdjacence.containsKey(noeud)) {
            // Renvoyer une copie de la liste des voisins du nœud pour éviter les modifications non autorisées
            return new ArrayList<>(matriceAdjacence.get(noeud).keySet());
        } else {
            // Si le nœud n'existe pas dans le graphe, renvoyer une liste vide
            return new ArrayList<>();
        }
    }

    public List<Noeud<E>> getNoeuds() {
        // Renvoyer une copie de la liste des nœuds pour éviter les modifications non autorisées
        return new ArrayList<>(noeuds);
    }
}
