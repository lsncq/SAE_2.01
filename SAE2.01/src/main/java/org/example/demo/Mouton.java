package org.example.demo;

public class Mouton extends Animal{

    private Plateau plateau;
    private Plante nourriture;

    public Mouton(Plateau plateau) {
        this.plateau = plateau;
    }

    public void mange(){
        this.nbCase=3;
    }

}
