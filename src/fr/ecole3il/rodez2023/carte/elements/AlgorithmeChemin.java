package fr.ecole3il.rodez2023.carte.elements;

import java.util.List;

public interface AlgorithmeChemin<E> {
    List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee);
}
