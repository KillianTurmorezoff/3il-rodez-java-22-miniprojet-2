
**Question :** Quelle structure de données pourrait être utilisée pour stocker les relations entre les nœuds du graphe et les informations associées à ces relations, comme les coûts des arêtes ?
**Réponse :** une matrice, donc un tableau a 2 dimensions.

- `public void ajouterNoeud(Noeud<E> noeud)`: Cette méthode ajoute un nœud au graphe si le nœud n'existe pas déjà dans le graphe.

- `public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout)`: Cette méthode ajoute une arête pondérée entre deux nœuds du graphe. Elle prend en paramètre le nœud de départ, le nœud d'arrivée et le coût de l'arête. Si les nœuds de départ et d'arrivée n'existent pas encore dans le graphe, ils sont ajoutés en utilisant la méthode `ajouterNoeud`. Ne pas oublier d'initialiser l'arête à la matrice d'adjacence associée au nœud de départ.

- `public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee)`: Cette méthode renvoie le coût de l'arête entre deux nœuds spécifiés.

- `public List<Noeud<E>> getNoeuds()`: Cette méthode renvoie une liste contenant tous les nœuds du graphe.

- `public List<Noeud<E>> getVoisins(Noeud<E> noeud)`: Cette méthode renvoie une liste contenant tous les voisins d'un nœud spécifié. Si le nœud n'existe pas dans le graphe, elle renvoie une liste vide.

**Question :** Pourquoi pensez-vous que les classes `Noeud` et `Graphe` ont été définies avec des paramètres génériques ?

### Interface `AlgorithmeChemin`

L'interface `fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin<E>` définit un contrat pour les classes qui implémentent des algorithmes de recherche de chemin dans un graphe :

- `List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee)`: Cette méthode est responsable de trouver un chemin entre un nœud de départ et un nœud d'arrivée dans un graphe donné. Elle prend en paramètres le graphe dans lequel la recherche doit être effectuée, ainsi que les nœuds de départ et d'arrivée. Cette méthode renvoie une liste de nœuds représentant le chemin trouvé entre le nœud de départ et le nœud d'arrivée dans le graphe.


**Question :** Pourquoi pensez-vous que la création d'une interface est une bonne pratique dans ce contexte ?

### Classe `AlgorithmeDijkstra`

La classe `fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeDijkstra<E>` implémente l'interface `AlgorithmeChemin<E>` et fournit une implémentation de l'algorithme de Dijkstra pour trouver le chemin le plus court entre deux nœuds dans un graphe pondéré.

Voici les étapes de l'algorithme de Dijkstra pour trouver le chemin le plus court entre un nœud de départ et tous les autres nœuds dans un graphe pondéré :

1. Initialisation des structures de données :
   1. Initialiser un ensemble de coûts pour chaque nœud, avec le coût du nœud de départ mis à zéro et les coûts des autres nœuds mis à l'infini.
   2. Initialiser un ensemble de prédécesseurs pour chaque nœud, avec le prédécesseur de chaque nœud initialisé à null.
   3. Utiliser une file de priorité pour suivre les nœuds à explorer, en les classant par leur coût actuel.
2. Exploration des nœuds :
   1. Tant que la file de priorité n'est pas vide :
      1. Retirer le nœud avec le coût minimum de la file de priorité.
      2. Si ce nœud est le nœud d'arrivée recherché, l'algorithme s'arrête.
      3. Sinon, pour chaque voisin du nœud actuel :
         1. Calculer le coût total pour atteindre ce voisin depuis le nœud actuel.
         2. Si ce coût total est inférieur au coût actuel du voisin, mettre à jour le coût du voisin et son prédécesseur, puis l'ajouter à la file de priorité.
3. Reconstruction du chemin le plus court :
   1. Une fois que le nœud d'arrivée est atteint ou que la file de priorité est vide, l'algorithme s'arrête.
   2. Reconstruire le chemin le plus court en remontant les prédécesseurs à partir du nœud d'arrivée jusqu'au nœud de départ.

Ces étapes permettent à l'algorithme de Dijkstra de trouver efficacement le chemin le plus court entre un nœud de départ et tous les autres nœuds dans un graphe pondéré.

### Classe `AlgorithmeAEtoile`

La classe `fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeAEtoile<E>` implémente l'interface `AlgorithmeChemin<E>`. Elle utilise l'algorithme A* pour trouver un chemin entre un nœud de départ et un nœud d'arrivée dans un graphe donné. 

L'algorithme A* est un algorithme de recherche de chemin dans un graphe qui combine les caractéristiques de l'algorithme de Dijkstra et d'une recherche heuristique. Voici les étapes de l'algorithme A* :

1. Initialisation :
   1. Initialisez deux ensembles :
      - L'ensemble des nœuds à explorer, généralement une file de priorité, triée par le coût total estimé.
      - L'ensemble des nœuds déjà explorés.
    2. Initialisez un tableau pour stocker le coût total estimé pour chaque nœud.
    3. Initialisez un tableau pour stocker les nœuds prédécesseurs.
2. Initialisation des coûts :
    1. Initialisez le coût total estimé de chaque nœud à l'infini, sauf pour le nœud de départ, dont le coût total estimé est défini à partir d'une heuristique spécifique (par exemple, la distance à vol d'oiseau jusqu'à la destination).
    2. Initialisez le coût actuel du nœud de départ à 0.
3. Boucle principale :
    1. Tant que l'ensemble des nœuds à explorer n'est pas vide :
       1. Sélectionnez le nœud avec le coût total estimé le plus bas dans la file de priorité (ou le nœud le plus prometteur).
       2. Si ce nœud est le nœud d'arrivée, arrêtez l'algorithme et reconstruisez le chemin.
       3. Sinon, marquez le nœud comme exploré et examinez ses voisins.
4. Mise à jour des coûts :
   1. Pour chaque voisin non exploré du nœud sélectionné :
      1. Calculez le coût réel pour atteindre ce voisin depuis le nœud sélectionné.
      2. Calculez le coût total estimé pour ce voisin en additionnant son coût réel et l'estimation du coût restant jusqu'à l'arrivée (heuristique).
      3. Si ce coût total estimé est inférieur au coût total estimé actuel pour ce voisin, mettez à jour les coûts et marquez le nœud prédécesseur.
5. Reconstruction du chemin : Une fois que le nœud d'arrivée a été atteint, reconstruisez le chemin en remontant les nœuds prédécesseurs à partir du nœud d'arrivée jusqu'au nœud de départ.

Une fois que le chemin a été reconstruit, il représente le chemin le plus court entre le nœud de départ et le nœud d'arrivée, en tenant compte à la fois des coûts réels et des estimations heuristiques.

### Un adaptateur pour le travail de Philibert

Même s'il a mal travaillé, ce serait dommage de refaire tout ce qu'a fait Philibert !

À la place, on va s'intéresser à une classe supplémentaire, `fr.ecole3il.rodez2023.carte.AdaptateurAlgorithme`. La classe `AdaptateurAlgorithme` agit comme un pont entre le code existant, qui inclut les classes `Carte`, `Noeud` et `Graphe`, et le travail déjà effectué par Philibert sur l'interface graphique. 

Elle fournit une méthode principale, trouverChemin, qui utilise un algorithme spécifié pour trouver le chemin le plus court entre deux cases sur une carte. Pour ce faire, elle crée un graphe représentant la carte à partir des données fournies, puis utilise cet algorithme pour trouver le chemin optimal. Ensuite, elle retourne ce chemin sous forme d'un objet Chemin adapté à l'interface graphique de Philibert.

- `static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee)` : Trouve le chemin le plus court entre deux cases sur la carte en utilisant l'algorithme spécifié.

Je vous conseille quelques méthodes intermédiaires (`KISS`) :

- `static Graphe <Case> creerGraphe(Carte carte)` : Crée un graphe représentant la carte en utilisant les cases comme nœuds et ajoute des arêtes entre les cases voisines.
- `static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur)` : Ajoute des arêtes entre une case donnée et ses cases voisines dans le graphe.
- `static double calculerCout(Case from, Case to)` : Calcule le coût pour se déplacer d'une case à une autre.
- `static afficherChemin(List<Noeud<Case>> chemin)` : Affiche le chemin trouvé dans la console.

Enfin, utilisez cette classe adaptée pour reprendre les fichiers de Philibert et corriger ses erreurs.