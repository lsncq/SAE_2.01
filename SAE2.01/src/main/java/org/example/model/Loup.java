package org.example.model;

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

}
