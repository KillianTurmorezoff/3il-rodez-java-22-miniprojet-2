package fr.ecole3il.rodez2023.carte;

import java.util.ArrayList;
import java.util.List;

import fr.ecole3il.rodez2023.carte.elements.*;
import fr.ecole3il.rodez2023.carte.chemin.elements.*;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.*;

/**
 * La classe AdaptateurAlgorithme agit comme un pont entre le code existant, qui
 * inclut les classes Carte, Noeud et Graphe, et le travail déjà effectué par
 * Philibert sur l'interface graphique.
 */
public class AdaptateurAlgorithme {

	/**
	 * Trouve le chemin le plus court entre deux cases sur la carte en utilisant
	 * l'algorithme spécifié.
	 *
	 * @param algorithme L'algorithme à utiliser pour trouver le chemin.
	 * @param carte      La carte sur laquelle trouver le chemin.
	 * @param xDepart    La coordonnée x de la case de départ.
	 * @param yDepart    La coordonnée y de la case de départ.
	 * @param xArrivee   La coordonnée x de la case d'arrivée.
	 * @param yArrivee   La coordonnée y de la case d'arrivée.
	 * @return Le chemin trouvé sous forme d'un objet Chemin.
	 */
	public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart,
	        int xArrivee, int yArrivee) {
	    Graphe<Case> graphe = creerGraphe(carte);
	    Noeud<Case> depart = new Noeud<>(new Case(carte.getTuile(xDepart, yDepart), xDepart, yDepart));
	    Noeud<Case> arrivee = new Noeud<>(new Case(carte.getTuile(xArrivee, yArrivee), xArrivee, yArrivee));
	    List<Noeud<Case>> cheminNoeuds = algorithme.trouverChemin(graphe, depart, arrivee);
	    if (cheminNoeuds != null) {
	        List<Case> casesChemin = new ArrayList<>();
	        for (Noeud<Case> noeud : cheminNoeuds) {
	            casesChemin.add(noeud.getValeur());
	        }
	        return new Chemin(casesChemin);
	    } else {
	        return new Chemin(new ArrayList<>()); // Retourner une liste vide si aucun chemin n'est trouvé
	    }
	}


	/**
	 * Crée un graphe représentant la carte en utilisant les cases comme nœuds et
	 * ajoute des arêtes entre les cases voisines.
	 *
	 * @param carte La carte à représenter sous forme de graphe.
	 * @return Le graphe créé à partir de la carte.
	 */
	private static Graphe<Case> creerGraphe(Carte carte) {
		Graphe<Case> graphe = new Graphe<>();
		int largeur = carte.getLargeur();
		int hauteur = carte.getHauteur();

		for (int x = 0; x < largeur; x++) {
			for (int y = 0; y < hauteur; y++) {
				Case currentCase = new Case(carte.getTuile(x, y), x, y);
				Noeud<Case> noeud = new Noeud<>(currentCase);
				graphe.ajouterNoeud(noeud);
				ajouterAretesVoisines(graphe, noeud, x, y, largeur, hauteur);
			}
		}
		return graphe;
	}

	/**
	 * Ajoute des arêtes entre une case donnée et ses cases voisines dans le graphe.
	 *
	 * @param graphe      Le graphe auquel ajouter les arêtes.
	 * @param currentCase La case pour laquelle ajouter les arêtes voisines.
	 * @param x           La coordonnée x de la case.
	 * @param y           La coordonnée y de la case.
	 * @param largeur     La largeur de la carte.
	 * @param hauteur     La hauteur de la carte.
	 */
	private static void ajouterAretesVoisines(Graphe<Case> graphe, Noeud<Case> currentCase, int x, int y, int largeur,
			int hauteur) {
		Case currentCaseValue = currentCase.getValeur();

		// Vérifier les cases voisines dans les quatre directions
		if (x > 0) {
			Case voisin = new Case(currentCaseValue.getTuile(), x - 1, y);
			Noeud<Case> noeudVoisin = new Noeud<>(voisin);
			double cout = calculerCout(currentCaseValue, voisin);
			graphe.ajouterArete(currentCase, noeudVoisin, cout);
		}
		if (x < largeur - 1) {
			Case voisin = new Case(currentCaseValue.getTuile(), x + 1, y);
			Noeud<Case> noeudVoisin = new Noeud<>(voisin);
			double cout = calculerCout(currentCaseValue, voisin);
			graphe.ajouterArete(currentCase, noeudVoisin, cout);
		}
		if (y > 0) {
			Case voisin = new Case(currentCaseValue.getTuile(), x, y - 1);
			Noeud<Case> noeudVoisin = new Noeud<>(voisin);
			double cout = calculerCout(currentCaseValue, voisin);
			graphe.ajouterArete(currentCase, noeudVoisin, cout);
		}
		if (y < hauteur - 1) {
			Case voisin = new Case(currentCaseValue.getTuile(), x, y + 1);
			Noeud<Case> noeudVoisin = new Noeud<>(voisin);
			double cout = calculerCout(currentCaseValue, voisin);
			graphe.ajouterArete(currentCase, noeudVoisin, cout);
		}
	}

	/**
	 * Calcule le coût pour se déplacer d'une case à une autre.
	 *
	 * @param from La case de départ.
	 * @param to   La case d'arrivée.
	 * @return Le coût pour se déplacer de la case de départ à la case d'arrivée.
	 */
	private static double calculerCout(Case from, Case to) {
		Tuile tuileDepartTuile = from.getTuile();
		Tuile tuileArriveeTuile = to.getTuile();
		double cout = 1.0; // Coût de base pour se déplacer d'une case à une autre

		// Ajouter des pénalités en fonction des types de tuiles
		if (tuileDepartTuile == Tuile.MONTAGNES || tuileArriveeTuile == Tuile.MONTAGNES) {
			cout += 2.0; // Pénalité pour les montagnes
		}
		if (tuileDepartTuile == Tuile.FORET || tuileArriveeTuile == Tuile.FORET) {
			cout += 1.5; // Pénalité pour les forêts
		}

		return cout;
	}

	/**
	 * Affiche le chemin trouvé dans la console.
	 *
	 * @param chemin Le chemin à afficher.
	 */
	private static void afficherChemin(List<Noeud<Case>> chemin) {
		System.out.println("Chemin trouvé :");
		double coutTotal = 0.0;
		for (Noeud<Case> noeud : chemin) {
			Case caseNoeud = noeud.getValeur();
			System.out.println("[" + caseNoeud.getX() + ", " + caseNoeud.getY() + "] - " + caseNoeud.getTuile());
			if (chemin.indexOf(noeud) > 0) {
				Noeud<Case> noeudPrecedent = chemin.get(chemin.indexOf(noeud) - 1);
				coutTotal += calculerCout(noeudPrecedent.getValeur(), caseNoeud);
			}
		}
		System.out.println("Coût total du chemin : " + coutTotal);
	}
}
