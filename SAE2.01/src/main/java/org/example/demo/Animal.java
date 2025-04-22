package org.example.demo;

public abstract class Animal {

    protected int x; // Position en x de l'animal dans le plateau
    protected int y; // Position en y de l'animal dans le plateau
    protected int nbCase; // Nombre de case que l'animal peut se deplacer
    protected Boolean enVie; // Indique si il est en vie
    protected Boolean vu; // Indique si l'animal a vu un danger/une proie

    public void deplace(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void mange();

    public void setNbCase(int nbCase) {
        this.nbCase = nbCase;
    }

    public void setEnVie(Boolean enVie) {
        this.enVie = enVie;
    }

    public void setVu(Boolean vu) {
        this.vu = vu;
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
    public Boolean getEnVie() {
        return enVie;
    }
    public Boolean getVu() {
        return vu;
    }


}
