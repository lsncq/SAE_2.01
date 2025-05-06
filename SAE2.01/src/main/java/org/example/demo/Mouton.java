package org.example.demo;

public class Mouton extends Animal{

    private Plateau plateau;
    private Element nourriture;

    public Mouton(Plateau plateau) {
        this.plateau = plateau;
    }

    public boolean mange(){
        nourriture = plateau.getCase(x,y).getType();
        if (nourriture ==null || nourriture==Element.Roche){
            return false;
        }
        if (nourriture == Element.Cactus) this.setNbCase(1);
        if (nourriture == Element.Herbe) this.setNbCase(2);
        if (nourriture == Element.Marguerite) this.setNbCase(4);
        return true;
    }

}
