package org.example.model;

import java.util.LinkedList;

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

    public LinkedList<Case> pep(){
        LinkedList<Case> pile = new LinkedList<>();
        LinkedList<Case> cases = new LinkedList<>();
        Case current = plateau.getCase(x, y);
        cases.add(current);
        pile.add(current);
        Case caseMouton = plateau.getCase(plateau.getMouton().getX(), plateau.getMouton().getY());
        while (current != caseMouton) {
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
        Case caseMouton = plateau.getCase(plateau.getMouton().getX(), plateau.getMouton().getY());
        while (current != caseMouton) {
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

    public LinkedList<Case> fourmie(){
        return null;
    }

}
