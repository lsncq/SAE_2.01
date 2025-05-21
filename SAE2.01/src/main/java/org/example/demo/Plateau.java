package org.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class Plateau {

    private Case[][] cases;
    private Case caseFinal;
    private Mouton mouton;
    private Loup loup;

    public Plateau() {
        this(3,3);
    }

    public Plateau(int nbX, int nbY) {
        this.cases = new Case[nbX][nbY];
        for (int i = 0; i < nbX; i++) {
            for (int j = 0; j < nbY; j++) {
                if (i==0)cases[i][j] = new Case(this, i, j,Element.Roche);
                else if (i==nbX-1)cases[i][j] = new Case(this, i, j,Element.Roche);
                else if (j==0)cases[i][j] = new Case(this, i,j,Element.Roche);
                else if (j==nbY-1)cases[i][j] = new Case(this, i,j,Element.Roche);
                else cases[i][j] = new Case(this, i,j,Element.Herbe);
            }
        }
        this.mouton = new Mouton(this);
        mouton.deplace(1,1);
        this.loup = new Loup(this);
        loup.deplace(1,2);
    }

    public void setCaseFinal(Case caseFinal) {
        this.caseFinal = caseFinal;
        this.caseFinal.setType(Element.Herbe);
    }

    public ArrayList<Case> getCasesPossible() {
        ArrayList<Case> casesPossible = new ArrayList<>();
        for (Case[] aCase : cases) {
            for (Case value : aCase) {
                if (value.getType() != Element.Roche) {
                    casesPossible.add(value);
                }
            }
        }
        return casesPossible;
    }

    public ArrayList<Case> getAllCases() {
        ArrayList<Case> casesPossible = new ArrayList<>();
        for (Case[] aCase : cases) {
            Collections.addAll(casesPossible, aCase);
        }
        return casesPossible;
    }

    private boolean pel(){
        LinkedList<Case> lesCases = new LinkedList<>();
        HashSet<Case> file = new HashSet<>();
        file.add(caseFinal);
        lesCases.add(caseFinal);

        while (!lesCases.isEmpty()) {
            Case current = lesCases.poll();
            for (Case neighbor : current.voisin()) {
                if (!file.contains(neighbor)) {
                    lesCases.add(neighbor);
                    file.add(neighbor);
                }
            }
        }
        ArrayList<Case> verif = getCasesPossible();
        verif.removeAll(file);
        return verif.isEmpty();
    }

    public boolean verifier(){
        if (caseFinal==null){
            return false;
        }
        return pel();

    }

    public Case getCase(int x, int y) {
        if (x>=0 && x<cases.length && y>=0 && y<cases[x].length){
            return cases[x][y];
        }
        else return null;
    }

    public String toString(){
        for (Case[] aCase : cases) {
            for (Case value : aCase) {
                System.out.print(value.getType() + " ");
            }
            System.out.println();
        }
        return "\nCase Final : "+caseFinal.toString();
    }

    public Mouton getMouton(){
        return mouton;
    }
    public Loup getLoup(){return loup;}

    public int length(){
        return cases.length;
    }
    public int height(){
        return cases[0].length;
    }
    public Case getCaseFinal() {
        return caseFinal;
    }
}
