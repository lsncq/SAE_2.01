package org.example.model;

public class Loup extends Animal {

    private Plateau plateau;

    public Loup(Plateau plateau) {
        this.plateau = plateau;
        this.setNbCase(3);
    }

    public boolean mange(){
        if (plateau.getMouton().getX()==x && plateau.getMouton().getY()==y){
            return true;
        }
        return false;
    }

    public String toString() {
        return "Loup";
    }

}
