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

    public abstract LinkedList<Case> pep();

    public abstract LinkedList<Case> pel();

    public abstract LinkedList<Case> dijkstra();

    public abstract LinkedList<Case> fourmie();

}
