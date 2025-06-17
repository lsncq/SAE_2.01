package org.example.model;

import java.util.*;

public class Mouton extends Animal{

    private ArrayList<Element> nourriture;

    public Mouton(Plateau plateau) {
        this.plateau = plateau;
        this.nourriture = new ArrayList<>();
    }

    public boolean mange(){
        nourriture.add(plateau.getCase(x,y).getType());
        if (nourriture ==null || nourriture.getLast()==Element.Roche){
            return false;
        }
        if (nourriture.getLast() == Element.Cactus) this.setNbCase(1);
        if (nourriture.getLast() == Element.Herbe) this.setNbCase(2);
        if (nourriture.getLast()  == Element.Marguerite) this.setNbCase(4);
        return true;
    }

    public String toString() {
        return "Mouton";
    }

    public ArrayList<Element> getNourriture() {
        return nourriture;
    }

    public boolean fuite(){
        //La vision prend en compte les obstacles
        HashSet<Case> vision = new HashSet<>(); // le set evite les doublons
        ArrayList<Case> cases = new ArrayList<>();
        vision.add(plateau.getCase(x,y));
        for (int i = 0; i < 5; i++){
            for (Case c : vision){
                cases.addAll(c.voisin());
            }
            vision.addAll(cases);
        }
        Case loup = plateau.getCase(plateau.getLoup().getX(), plateau.getLoup().getY());
        return vision.contains(loup);
    }

    public LinkedList<Case> pep(){
        LinkedList<Case> pile = new LinkedList<>();
        LinkedList<Case> cases = new LinkedList<>();
        Case current = plateau.getCase(x, y);
        cases.add(current);
        pile.add(current);

        while (current != plateau.getCaseFinal()) {
            LinkedList<Case> voisins = new LinkedList<>();

            for (Case neighbor : current.voisin()) {
                if (!cases.contains(neighbor)) {
                    voisins.add(neighbor);
                }
            }
            if (!voisins.isEmpty()) {
                current = voisins.getLast();
                cases.add(current);
                pile.add(current);
            }else {
                pile.removeLast();
                current = pile.getLast();
            }
        }
        return pile;
    }

    public LinkedList<Case> dijkstra() {
        Case depart = plateau.getCase(x, y); // Position du mouton
        Case arrivee = plateau.getCaseFinal(); // Position de la sortie
        int length = plateau.length(); // Dimension du plateau
        int height = plateau.height(); // Dimension du plateau
        int[][] distance = new int[length][height]; // Création d'un tableau vide
        Case[][] precedent = new Case[length][height]; // Ce tableau nous permettera d'obtenir le chemin
        for (int i = 0; i < length; i++){
            for (int j = 0; j < height; j++){
                distance[i][j] = Integer.MAX_VALUE; // Integer.MAX_VALUE nous permet d'obtenir la plus grande valeur possible , facilitant ainsi le choix du minimum
            }
        }
        distance[depart.getX()][depart.getY()] = 0; // départ à 0 car position du mouton

        LinkedList<Case> queue = new LinkedList<>(); // Initialisation d'une file pour contenir des cases à visiter
        queue.add(depart); // ajout de la case départ

        while (!queue.isEmpty()) { // Tant qu'il reste des cases à explorer
            Case current = queue.poll(); // On récupère et on enlève la première cade de la file
            for (Case voisin : current.voisin()) { // Pour chaque voisin (haut, gauche, bas, droit)
                int d = distance[current.getX()][current.getY()] + 1; // On récupère la distance pour aller à ce voisin, chaque déplacement coûte 1
                if (d < distance[voisin.getX()][voisin.getY()]) { // Si on trouve un chemin plus court pour aller à ce voisin

                    distance[voisin.getX()][voisin.getY()] = d; // alors on met a jour la distance minimale connue
                    precedent[voisin.getX()][voisin.getY()] = current; // On enregistre que pour aller à ce voisin il faut être sur la case current
                    queue.add(voisin); // On ajoute le voisin à la file

                }
            }
        }

        // Reconstruction du chemin
        LinkedList<Case> chemin = new LinkedList<>(); // Création d'une nouvelle liste pour le chemin final
        Case c = arrivee; // On commence le chemin par la case d'arrivée
        while (c != null) {
            chemin.addFirst(c); // On ajoute chaque case au début de la liste
            c = precedent[c.getX()][c.getY()]; // On remonte le chemin en suivant  jusqu'à revenir au point de départ
        }
        return chemin; // On retourne le chemin final
    }

    public LinkedList<Case> AStar() {
        Case depart = plateau.getCase(x, y); // Position du mouton
        Case arrivee = plateau.getCaseFinal(); // Position de la sortie
        int length = plateau.length(); // Dimension du plateau
        int height = plateau.height(); // Dimension du plateau
        int intervale = 0;
        int[][] distance = new int[length][height]; // Création d'un tableau vide
        Case[][] precedent = new Case[length][height]; // Ce tableau nous permettera d'obtenir le chemin
        for (int i = 0; i < length; i++){
            for (int j = 0; j < height; j++){
                distance[i][j] = Integer.MAX_VALUE; // Integer.MAX_VALUE nous permet d'obtenir la plus grande valeur possible , facilitant ainsi le choix du minimum
            }
        }
        distance[depart.getX()][depart.getY()] = 0; // départ à 0 car position du mouton

        LinkedList<Case> queue = new LinkedList<>(); // Initialisation d'une file pour contenir des cases à visiter
        queue.add(depart); // ajout de la case départ
        Case current = queue.getFirst(); // obtenir le premier de la liste

        while (!current.equals(arrivee)) {
            if (intervale %3 == 0){ //  Evite de tombe sur un chemin trop rapidement, qui ne surestime pas la distance
                queue.sort(Comparator.comparing(Case::compareM));//changement par rapport a dijkstra, compare pour trouver le poids minimum
            }
            intervale++; // incrémente intervale
            current = queue.pollFirst(); // supprime le premier de la liste
            for (Case voisin : current.voisin()) {
                int d = distance[current.getX()][current.getY()] + 1; // On récupère la distance pour aller à ce voisin, chaque déplacement coûte 1
                if (d < distance[voisin.getX()][voisin.getY()]) { // Si on trouve un chemin plus court pour aller à ce voisin

                    distance[voisin.getX()][voisin.getY()] = d; // alors on met a jour la distance minimale connue
                    precedent[voisin.getX()][voisin.getY()] = current; // On enregistre que pour aller à ce voisin il faut être sur la case current
                    queue.add(voisin); // On ajoute le voisin à la file

                }
            }
        }

        // Reconstruction du chemin
        LinkedList<Case> chemin = new LinkedList<>();
        Case c = arrivee;
        while (c != null) {
            chemin.addFirst(c);
            c = precedent[c.getX()][c.getY()];
        }
        return chemin;
    }

    public Case alea(ArrayList<Case> cases, double[] proba) {
        //Permet de choisir aleatoirement une case parmi une liste de case , avec leur probabilités respective
        double p = Math.random();
        double somme = 0.0;
        for (int j = 0; j < proba.length; j++) {
            somme += proba[j];
            if (p <= somme) {
                return cases.get(j);
            }
        }
        return cases.getLast();
    }

    public LinkedList<Case> fourmi(int iterations) {
        int length = plateau.length();
        int height = plateau.height();
        double[][] pheromones = new double[length][height]; // Tableau de pheromones , indique la probabilités de tombé sur la case
        double alpha = 1; // Contrôle l’importance relative de l’intensité et de la visibilité d’une arête.
        double evaporation = 0.0005;
        int nAnts = 25;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                Case c = plateau.getCase(i, j); // On ajuste les pheromones par rapport au type de la case
                if (c.getType().equals(Element.Herbe)){
                    pheromones[i][j] = 1;
                }else if (c.getType().equals(Element.Cactus)){
                    pheromones[i][j] = 0.5;
                }else if(c.getType().equals(Element.Marguerite)){
                    pheromones[i][j] = 2;
                }else if(c.getType().equals(Element.Roche)){
                    pheromones[i][j] = 0;
                }
            }
        }

        ArrayList<Case> meilleurChemin = null;
        int meilleurLongueur = Integer.MAX_VALUE;

        for (int iter = 0; iter < iterations; iter++) {
            ArrayList<ArrayList<Case>> lesChemins = new ArrayList<>();

            for (int ant = 0; ant < nAnts; ant++) {
                Case current = plateau.getCase(x,y);
                ArrayList<Case> path = new ArrayList<>();
                HashSet<Case> visited = new HashSet<>();
                path.add(current);
                visited.add(current);

                while (!current.equals(plateau.getCaseFinal()) && !current.voisin().isEmpty()) {
                    // Filter unvisited neighbors
                    ArrayList<Case> availableNeighbors = new ArrayList<>();
                    for (Case neighbor : current.voisin()) {
                        if (!visited.contains(neighbor)) {
                            availableNeighbors.add(neighbor);
                        }
                    }
                    if (availableNeighbors.isEmpty()) break;

                    // Calculate probabilities
                    double[] proba = new double[availableNeighbors.size()];
                    double sum = 0.0;
                    for (int i = 0; i < availableNeighbors.size(); i++) {
                        Case neighbor = availableNeighbors.get(i);
                        proba[i] = Math.pow(pheromones[neighbor.getX()][neighbor.getY()], alpha);
                        sum += proba[i];
                    }
                    for (int i = 0; i < proba.length; i++) {
                        proba[i] /= sum;
                    }

                    Case next = alea(availableNeighbors, proba);
                    path.add(next);
                    visited.add(next);
                    current = next;
                }

                if (current.equals(plateau.getCaseFinal())) {
                    lesChemins.add(path);
                    if (path.size() < meilleurLongueur) {
                        meilleurLongueur = path.size();
                        meilleurChemin = new ArrayList<>(path);
                    }
                }
            }

            // Evaporate pheromones
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < height; j++) {
                    pheromones[i][j] *= (1 - evaporation);
                }
            }

            // Update pheromones
            for (ArrayList<Case> path : lesChemins) {
                double delta = 1.0 / path.size();
                for (Case c : path) {
                    pheromones[c.getX()][c.getY()] += delta;
                }
            }
        }

        if (meilleurChemin == null) {
            return new LinkedList<>();
        }
        return new LinkedList<>(meilleurChemin);
    }

}
