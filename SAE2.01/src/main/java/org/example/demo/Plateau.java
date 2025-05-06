package org.example.demo;

import java.util.ArrayList;

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
        this.loup = new Loup(this);
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

    private boolean pel(){
        ArrayList<Case> lesCases = new ArrayList<>();
        ArrayList<Case> file = new ArrayList<>();
        file.add(caseFinal);
        while(!file.isEmpty()){
            Case currentCase = file.remove(0);
            lesCases.add(currentCase);
            ArrayList<Case> voisin = currentCase.voisin();
            voisin.removeAll(lesCases);
            file.addAll(voisin);
        }
        ArrayList<Case> verif = getCasesPossible();
        verif.removeAll(lesCases);
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

}
