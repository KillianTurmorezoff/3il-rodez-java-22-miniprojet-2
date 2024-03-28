package fr.ecole3il.rodez2023.carte.application;

import fr.ecole3il.rodez2023.carte.*;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeAEtoile;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeDijkstra;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.manipulateurs.GenerateurCarte;

/**
 
@author p.roquart

*/
public class ExempleCLI {

    public static void main(String[] args) {
        GenerateurCarte generateur = new GenerateurCarte();
        Carte test = generateur.genererCarte(100, 100);

        AlgorithmeChemin<Case> algoDijkstra = new AlgorithmeDijkstra<>();
        Chemin cheminDijkstra = AdaptateurAlgorithme.trouverChemin(algoDijkstra, test, 0, 0, 50, 50);
        cheminDijkstra.afficherChemin();

        AlgorithmeChemin<Case> algoAEtoile = new AlgorithmeAEtoile<>();
        Chemin cheminAEtoile = AdaptateurAlgorithme.trouverChemin(algoAEtoile, test, 0, 0, 50, 50);
        cheminAEtoile.afficherChemin();
    }

}