package org.example.model;

import java.io.RandomAccessFile;
import java.util.*;
import java.util.random.RandomGenerator;

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

    public LinkedList<Case> pel(){
        LinkedList<Case> file = new LinkedList<>();
        LinkedList<Case> cases = new LinkedList<>();
        Case current = plateau.getCase(x, y);
        cases.add(current);
        file.add(current);

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
                file.add(current);
            }else {
                file.removeFirst();
                current = file.getFirst();
            }
        }
        return file;
    }

    public LinkedList<Case> dijkstra(){
        return null;
    }

    private Case alea(ArrayList<Case> cases, double[] proba) {
        double p = Math.random();
        double cumulative = 0.0;
        for (int j = 0; j < proba.length; j++) {
            cumulative += proba[j];
            if (p <= cumulative) {
                return cases.get(j);
            }
        }
        // Fallback: return the last case if rounding errors occur
        return cases.get(cases.size() - 1);
    }

    public LinkedList<Case> fourmi() {
        int width = plateau.length();
        int height = plateau.height();
        double[][] pheromones = new double[width][height];
        double alpha = 2.0;
        double beta = 1.0;
        double evaporation = 0.05;
        int iterations = 100;
        int nAnts = 5;
        double Q = 1.0;

        // Initialize pheromones to 1
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pheromones[i][j] = 1.0;
            }
        }

        ArrayList<Case> bestPath = null;
        int bestLength = Integer.MAX_VALUE;

        for (int iter = 0; iter < iterations; iter++) {
            ArrayList<ArrayList<Case>> validPaths = new ArrayList<>();

            for (int ant = 0; ant < nAnts; ant++) {
                Case current = plateau.getCase(x,y); // Use your method to get the start
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
                        // η[i][j] = 1, so omitted
                        proba[i] = Math.pow(pheromones[neighbor.getX()][neighbor.getY()], alpha);
                        sum += proba[i];
                    }
                    for (int i = 0; i < proba.length; i++) {
                        proba[i] /= sum;
                    }

                    // Select next node
                    Case next = alea(availableNeighbors, proba);
                    path.add(next);
                    visited.add(next);
                    current = next;
                }

                if (current.equals(plateau.getCaseFinal())) {
                    validPaths.add(path);
                    if (path.size() < bestLength) {
                        bestLength = path.size();
                        bestPath = new ArrayList<>(path);
                    }
                }
            }

            // Evaporate pheromones
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    pheromones[i][j] *= (1 - evaporation);
                }
            }

            // Update pheromones based on valid paths
            for (ArrayList<Case> path : validPaths) {
                double delta = Q / path.size();
                for (Case c : path) {
                    pheromones[c.getX()][c.getY()] += delta;
                }
            }
        }

        if (bestPath == null) {
            // No path found
            return new LinkedList<>();
        }
        return new LinkedList<>(bestPath);
    }

}
