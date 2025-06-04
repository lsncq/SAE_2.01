package org.example.model;

import java.util.ArrayList;
import java.util.Objects;

public class Case {

    private int x;
    private int y;
    private Element type;
    private Plateau plateau;

    public Case(Plateau plateau , int x, int y) {
        this.plateau = plateau;
        this.x = x;
        this.y = y;
        this.type = Element.Roche;
    }
    public Case(Plateau plateau ,int x, int y, Element type) {
        this.plateau = plateau;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public ArrayList<Case> voisin(){
        ArrayList<Case> cases = new ArrayList<Case>();
        Case d = this.plateau.getCase(x+1,y);
        Case g = this.plateau.getCase(x-1,y);
        Case h = this.plateau.getCase(x,y+1);
        Case b = this.plateau.getCase(x,y-1);
        if (!(d==null || d.getType()==Element.Roche)){
            cases.add(d);
        }
        if (!(g==null || g.getType()==Element.Roche)){
            cases.add(g);
        }
        if (!(h==null || h.getType()==Element.Roche)){
            cases.add(h);
        }
        if (!(b==null || b.getType()==Element.Roche)){
            cases.add(b);
        }
        return cases;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Element getType() {
        return type;
    }
    public void setType(Element type) {
        this.type = type;
    }

    public String toString() {
        return "Case [x=" + x + ", y=" + y + ", type=" + type + "]\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Case c) {
            return x == c.x && y == c.y && type.equals(c.type);
        } else {
            return false;
        }
    }

}
