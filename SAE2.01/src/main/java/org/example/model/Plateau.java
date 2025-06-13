package org.example.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

    private Element scan(char s){
        if (s == 'x'){
            return Element.Roche;
        }
        else if (s == 'h'){
            return Element.Herbe;
        }
        else if (s == 'f'){
            return Element.Marguerite;
        }
        else if (s == 'c'){
            return Element.Cactus;
        }
        else if (s == 'm'){
            return Element.Herbe;
        }
        else if (s == 'l'){
            return Element.Herbe;
        }
        else if (s == 's'){
            return Element.Herbe;
        }
        else{
            return Element.Roche;
        }
    }

    public static Plateau taillePlateau(String name) {
        int j = 0;
        int i = 0;
        try (Scanner scanner = new Scanner(new File(name))) {
            while (scanner.hasNextLine()) {
                j++;
                i = scanner.nextLine().length();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé : " + e.getMessage());
        }
        return new Plateau(i,j);
    }

    public void lireFichier(String name){
        try (Scanner scanner = new Scanner(new File(name))) {
            for (int j = 0; j < height() ; j++) {
                String element = scanner.nextLine();
                for (int i = 0; i < length(); i++) {
                    char e = element.charAt(i);
                    cases[i][j].setType(scan(e));
                    if (e == 'm') {
                        mouton.deplace(i, j);
                    } else if (e == 'l') {
                        loup.deplace(i, j);
                    }
                    if (e == 's') {
                        this.setCaseFinal(cases[i][j]);
                    }
                }
            }
            } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé : " + e.getMessage());
        }
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

    public int distanceM(int x, int y) {
        return Math.abs(x - caseFinal.getX()) + Math.abs(y - caseFinal.getY());
    }

    public int distanceL(int x, int y) {
        return Math.abs(x - mouton.getX()) + Math.abs(y - mouton.getY());
    }
}
