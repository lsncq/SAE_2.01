package org.example.demo;

import java.util.ArrayList;

public class Loup extends Animal {

    private Plateau plateau;

    public Loup(Plateau plateau) {
        this.plateau = plateau;
        this.setNbCase(3);
        this.enVie = true;
        this.vu = false;
    }

    public boolean voir(){
        ArrayList<Case> resultat = new ArrayList<>();
        resultat.add(plateau.getCase(x,y));
        ArrayList<Case> provisoire = new ArrayList<>();
        for (int klm=0; klm<5; klm++){
            for (Case i : resultat){
                provisoire.addAll(i.voisin());
            }
        }
        return true;
    }

    public boolean mange(){
        if (plateau.getMouton().getX()==x && plateau.getMouton().getY()==y){
            plateau.getMouton().setEnVie(false);
            return true;
        }
        return false;
    }

}
