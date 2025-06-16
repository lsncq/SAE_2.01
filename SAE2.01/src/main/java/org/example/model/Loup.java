package org.example.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;

public class Loup extends Animal {

    public Loup(Plateau plateau) {
        this.plateau = plateau;
        this.setNbCase(3);
    }

    public boolean mange(){
        return plateau.getMouton().getX() == x && plateau.getMouton().getY() == y;
    }

    public String toString() {
        return "Loup";
    }

    public boolean attack(){
        HashSet<Case> vision = new HashSet<>();
        ArrayList<Case> cases = new ArrayList<>();
        vision.add(plateau.getCase(x,y));
        for (int i = 0; i < 5; i++){
            for (Case c : vision){
                cases.addAll(c.voisin());
            }
            vision.addAll(cases);
        }
        Case mouton = plateau.getCase(plateau.getMouton().getX(), plateau.getMouton().getY());
        return vision.contains(mouton);
    }

    public LinkedList<Case> pep(){
        LinkedList<Case> pile = new LinkedList<>();
        LinkedList<Case> cases = new LinkedList<>();
        Case current = plateau.getCase(x, y);
        cases.add(current);
        pile.add(current);
        Case caseMouton = plateau.getCase(plateau.getMouton().getX(), plateau.getMouton().getY());
        while (current != caseMouton) {
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
        Case depart = plateau.getCase(x, y);
        Case arrivee = plateau.getCase(plateau.getMouton().getX(), plateau.getMouton().getY());
        int length = plateau.length();
        int height = plateau.height();
        int[][] distance = new int[length][height];
        Case[][] precedent = new Case[length][height];
        for (int i = 0; i < length; i++){
            for (int j = 0; j < height; j++){
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        distance[depart.getX()][depart.getY()] = 0;

        LinkedList<Case> queue = new LinkedList<>();
        queue.add(depart);

        while (!queue.isEmpty()) {
            Case current = queue.poll();
            for (Case voisin : current.voisin()) {
                int d = distance[current.getX()][current.getY()] + 1;
                if (d < distance[voisin.getX()][voisin.getY()]) {

                    distance[voisin.getX()][voisin.getY()] = d;
                    precedent[voisin.getX()][voisin.getY()] = current;
                    queue.add(voisin);

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

    public LinkedList<Case> AStar() {
        Case depart = plateau.getCase(x, y);
        Case arrivee = plateau.getCase(plateau.getMouton().getX(), plateau.getMouton().getY());
        int length = plateau.length();
        int height = plateau.height();
        int intervale = 0;
        int[][] distance = new int[length][height];
        Case[][] precedent = new Case[length][height];
        for (int i = 0; i < length; i++){
            for (int j = 0; j < height; j++){
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        distance[depart.getX()][depart.getY()] = 0;

        LinkedList<Case> queue = new LinkedList<>();
        queue.add(depart);

        while (!queue.isEmpty()) {
            if (intervale %3 == 0){
                queue.sort(Comparator.comparing(Case::compareL));//changement par rapport a dijkstra
            }
            intervale++;
            Case current = queue.pollFirst();
            for (Case voisin : current.voisin()) {
                int d = distance[current.getX()][current.getY()] + 1;
                if (d < distance[voisin.getX()][voisin.getY()]) {

                    distance[voisin.getX()][voisin.getY()] = d;
                    precedent[voisin.getX()][voisin.getY()] = current;
                    queue.add(voisin);

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

    private Case alea(ArrayList<Case> cases, double[] proba) {
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
        double[][] pheromones = new double[length][height];
        double alpha = 1;
        double evaporation = 0.001;
        int nAnts = 25;

        // pheromones to 1
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                pheromones[i][j] = 1.0;
            }
        }

        ArrayList<Case> bestPath = null;
        int bestLength = Integer.MAX_VALUE;

        for (int iter = 0; iter < iterations; iter++) {
            ArrayList<ArrayList<Case>> validPaths = new ArrayList<>();

            for (int ant = 0; ant < nAnts; ant++) {
                Case current = plateau.getCase(x,y);
                ArrayList<Case> path = new ArrayList<>();
                HashSet<Case> visited = new HashSet<>();
                path.add(current);
                visited.add(current);

                while (!current.equals(plateau.getCase(plateau.getMouton().getX(),plateau.getMouton().getY())) && !current.voisin().isEmpty()) {
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

                if (current.equals(plateau.getCase(plateau.getMouton().getX(),plateau.getMouton().getY()))) {
                    validPaths.add(path);
                    if (path.size() < bestLength) {
                        bestLength = path.size();
                        bestPath = new ArrayList<>(path);
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
            for (ArrayList<Case> path : validPaths) {
                double delta = 1.0 / path.size();
                for (Case c : path) {
                    pheromones[c.getX()][c.getY()] += delta;
                }
            }
        }

        if (bestPath == null) {
            return new LinkedList<>();
        }
        return new LinkedList<>(bestPath);
    }

}
