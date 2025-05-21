package org.example.model;

public abstract class Animal {

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
}
