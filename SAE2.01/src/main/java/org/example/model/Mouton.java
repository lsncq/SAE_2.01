package org.example.model;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;

public class Mouton extends Animal{

    private ArrayList<Element> nourriture;

    public Mouton(Plateau plateau) {
        this.plateau = plateau;
        this.nourriture = new ArrayList<>();
    }

    public boolean mange(){
        System.out.println(pep());
        System.out.println(pep());
        nourriture.add(plateau.getCase(x,y).getType());
        if (nourriture ==null || nourriture.getLast()==Element.Roche){
            return false;
        }
        if (nourriture.getLast() == Element.Cactus) this.setNbCase(1);
        if (nourriture.getLast() == Element.Herbe) this.setNbCase(2);
        if (nourriture.getLast()  == Element.Marguerite) this.setNbCase(4);
        return true;
    }

    public String toString() {
        return "Mouton";
    }

    public ArrayList<Element> getNourriture() {
        return nourriture;
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

    public LinkedList<Case> dijkstra(){
        return null;
    }

    public LinkedList<Case> fourmie(){
//        INITIALISER la matrice des phéromones τ[i][j] à 0 chaque arête (i, j)
//        POUR chaque itération de 1 à N_ITERATIONS FAIRE
//          POUR chaque fourmi de 1 à N_ANTS FAIRE
//              PLACER la fourmi au point de départ
//              INITIALISER la liste du chemin courant avec le point de départ
//              INITIALISER l’ensemble des nœuds visités
//              TANT QUE la fourmi n’a pas atteint la sortie ET qu’il existe des mouvements possibles FAIRE
//                  POUR chaque voisin non visité j du nœud courant i
//                      CALCULER la probabilité de choisir j :
//                      P[i][j] = (τ[i][j]^α) * (η[i][j]^β) / SOMME sur tous les voisins k de (τ[i][k]^α) * (η[i][k]^β)
//                      où η[i][j] = 1
//                  CHOISIR le prochain nœud j selon la distribution de probabilité P[i][j]
//                  AJOUTER j au chemin courant et à l’ensemble des visités
//                  SE DÉPLACER vers j
//              FIN TANT QUE
//                  SI la sortie est atteinte
//                      ENREGISTRER le chemin et sa longueur
//          FIN POUR
//          ÉVAPORER les phéromones sur toutes les arêtes :
//          τ[i][j] = (1 - ρ) * τ[i][j]  pour tout (i, j)
//          POUR chaque chemin trouvé par une fourmi
//              POUR chaque arête (i, j) du chemin
//                  AJOUTER une quantité Δτ[i][j] = Q / longueur_du_chemin aux phéromones τ[i][j]
//              FIN POUR
//          FIN POUR
//        FIN POUR
//
//        RENVOYER le meilleur chemin trouvé (plus court chemin vers la sortie)
        LinkedList<Case> LeChemin = new LinkedList<>();
        double[][] pheromones = new double[plateau.length()][plateau.height()];
        double proba ;
        // toute les pheromones a 1
        for (int i = 0; i < plateau.length(); i++) {
            for (int j = 0; j < plateau.height(); j++) {
                pheromones[i][j] = 1;
            }
        }
        for (int i = 0; i < 100; i++) {
            for (int ant  = 0; ant < 5; ant++) {
                ArrayList<Case> chemin = new ArrayList<>();
                chemin.add(plateau.getCase(x,y));



            }
        }
        return LeChemin;
    }

}
