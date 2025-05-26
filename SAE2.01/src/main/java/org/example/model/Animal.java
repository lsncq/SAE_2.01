package org.example.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public abstract class Animal {

    protected Plateau plateau;
    protected int x; // Position en x de l'animal dans le plateau
    protected int y; // Position en y de l'animal dans le plateau
    protected int nbCase; // Nombre de case que l'animal peut se deplacer

    public void deplace(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract boolean mange();

    public void setNbCase(int nbCase) {
        this.nbCase = nbCase;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getNbCase() {
        return nbCase;
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

}
