package org.example.demo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;


import java.util.ArrayList;
import java.util.Objects;

public class PlateauGUI {

    private Plateau plateau;
    private StackPane stackPane;
    private GridPane gridPane;
    private Node ancienElementMouton;
    private Node ancienElementLoup;
    private int[] ancienPosMouton ;
    private int[] ancienPosLoup ;
    private int lequel;

    public PlateauGUI(Plateau plateau,StackPane stackPane) {
        this.plateau = plateau;
        this.stackPane = stackPane;
        gridPane = new GridPane();
        this.ancienElementLoup = image(new Image(Objects.requireNonNull(getClass().getResource("/herbev2.png")).toExternalForm()));
        this.ancienElementMouton = image(new Image(Objects.requireNonNull(getClass().getResource("/herbev2.png")).toExternalForm()));
        this.ancienPosLoup = new int[2];
        ancienPosLoup[0] = plateau.getLoup().getX();
        ancienPosLoup[1] = plateau.getLoup().getY();
        this.ancienPosMouton = new int[2];
        this.ancienPosMouton[0] = plateau.getMouton().getX();
        this.ancienPosMouton[1] = plateau.getMouton().getY();
    }

    private ImageView image(Image image) {
        ImageView view = new ImageView(image);
        view.setFitWidth((stackPane.getWidth()/1.3)/plateau.length());   // largeur en pixels
        view.setFitHeight((stackPane.getHeight()/1.3)/(plateau.height())); // longueur en pixels

        return view;
    }

    public void displayAnimal() {
        int x = plateau.getMouton().getX();
        int y = plateau.getMouton().getY();
        //PARTIE MOUTON
        Node supprime = null;
        for (Node node : gridPane.getChildren()) {
            if(GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == ancienPosMouton[0] && GridPane.getRowIndex(node) == ancienPosMouton[1]){
                supprime = node;
                break;
            }
        }
        gridPane.getChildren().remove(supprime);
        gridPane.add(ancienElementMouton,ancienPosMouton[0],ancienPosMouton[1]);
        plateau.getMouton().mange();
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y) {

                ancienElementMouton = node;
                ancienPosMouton[0] = x;
                ancienPosMouton[1] = y;
                supprime = node;
            }
        }

        gridPane.getChildren().remove(supprime);
        ImageView mouton = image(new Image(Objects.requireNonNull(getClass().getResource("/mouton.png")).toExternalForm()));
        gridPane.add(mouton, x, y);

        //PARTIE LOUP
        x = plateau.getLoup().getX();
        y = plateau.getLoup().getY();
        supprime = null;
        for (Node node : gridPane.getChildren()) {
            if(GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == ancienPosLoup[0] && GridPane.getRowIndex(node) == ancienPosLoup[1]){
                supprime = node;
                break;
            }
        }
        gridPane.getChildren().remove(supprime);
        gridPane.add(ancienElementLoup,ancienPosLoup[0],ancienPosLoup[1]);
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y) {

                ancienElementLoup = node;
                ancienPosLoup[0] = x;
                ancienPosLoup[1] = y;
                supprime = node;
                break;
            }
        }

        gridPane.getChildren().remove(supprime);
        ImageView loup = image(new Image(Objects.requireNonNull(getClass().getResource("/Loup.png")).toExternalForm()));
        gridPane.add(loup, x, y);

    }

    public void displayMouton(){
        deplacement(plateau.deplacePossible(plateau.getMouton().getX(), plateau.getMouton().getY(),plateau.getMouton().getNbCase()));
    }

    public void displayLoup(){
        deplacement(plateau.deplacePossible(plateau.getLoup().getX(),plateau.getLoup().getY(),plateau.getLoup().getNbCase()));
    }


    private void deplacement(ArrayList<Case> cases) {
        for (Case c : cases) {
            Circle grandCercle = new Circle(30, 30, 30);
            // Créer le trou central
            Circle trou = new Circle(30, 30, 26);

            // Soustraire le trou du grand cercle pour créer un anneau
            Shape anneau = Shape.subtract(grandCercle, trou);
            anneau.setFill(Color.rgb(255, 0, 255, 0.5));
            gridPane.add(anneau, c.getX(), c.getY());
        }
    }

    public void caseFin(){
        ImageView ibis ;
    }

    public void choisi(Case c){
        ImageView ibis ;
        if (c.getType() == Element.Herbe){
            Image i = new Image(Objects.requireNonNull(getClass().getResource("/herbev2.png")).toExternalForm());
            ibis = image(i);
        }else if(c.getType() == Element.Cactus){
            Image i = new Image(Objects.requireNonNull(getClass().getResource("/cactusv2.png")).toExternalForm());
            ibis = image(i);
        }else if(c.getType() == Element.Roche){
            Image i = new Image(Objects.requireNonNull(getClass().getResource("/rocherv2.png")).toExternalForm());
            ibis = image(i);
        }else if(c.getType() == Element.Marguerite){
            Image i = new Image(Objects.requireNonNull(getClass().getResource("/margueritev2.png")).toExternalForm());
            ibis = image(i);
        }else { Image i = new Image(Objects.requireNonNull(getClass().getResource("/margueritev2.png")).toExternalForm());
            ibis = image(i);}
        //
        ibis.setOnMouseClicked(mouseEvent -> {
            if (lequel < 5 && !(c.getY() == plateau.height()-1 || c.getY() == 0 || c.getX() == plateau.length()-1 || c.getX() == 0)){
                if (lequel == 1){
                    ibis.setImage(new Image(Objects.requireNonNull(getClass().getResource("/rocherv2.png")).toExternalForm()));
                    plateau.getCase(c.getX(),c.getY()).setType(Element.Roche);
                }else if(lequel == 2 ){
                    ibis.setImage(new Image(Objects.requireNonNull(getClass().getResource("/herbev2.png")).toExternalForm()));
                    plateau.getCase(c.getX(),c.getY()).setType(Element.Herbe);
                }else if(lequel == 3 ){
                    ibis.setImage(new Image(Objects.requireNonNull(getClass().getResource("/cactusv2.png")).toExternalForm()));
                    plateau.getCase(c.getX(),c.getY()).setType(Element.Cactus);
                }else if(lequel == 4) {
                    ibis.setImage(new Image(Objects.requireNonNull(getClass().getResource("/margueritev2.png")).toExternalForm()));
                    plateau.getCase(c.getX(), c.getY()).setType(Element.Marguerite);
                }
            }else if(lequel == 5 && (c.getY() == plateau.height()-1 || c.getY() == 0 || c.getX() == plateau.length()-1 || c.getX() == 0))  {
                ibis.setImage(new Image(Objects.requireNonNull(getClass().getResource("/herbev2.png")).toExternalForm()));
                plateau.getCaseFinal().setType(Element.Roche);
                if (!plateau.getCaseFinal().equals(c)){
                    choisi(plateau.getCaseFinal());
                }

                plateau.getCase(c.getX(), c.getY()).setType(Element.Herbe);
                plateau.setCaseFinal(c);
            }
            else if (c.equals(plateau.getCaseFinal())){
                ibis.setImage(new Image(Objects.requireNonNull(getClass().getResource("/rocherv2.png")).toExternalForm()));
                plateau.getCase(c.getX(),c.getY()).setType(Element.Roche);
            } else if (lequel == 6 && c.getType() != Element.Roche) {
                plateau.getMouton().deplace(c.getX(),c.getY());
                displayAnimal();
            } else if (lequel == 7 && c.getType() != Element.Roche) {
                plateau.getLoup().deplace(c.getX(),c.getY());
                displayAnimal();
            }
        });
        gridPane.add(ibis, c.getX(), c.getY());

        //
    }

    public void show() {
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.setGridLinesVisible(true);


        Button roche = new Button("Roche");
        Button herbe = new Button("Herbe");
        Button cactus = new Button("Cactus");
        Button marguerite = new Button("Marguerite");
        Button sortie = new Button("Sortie");
        Button mouton = new Button("Mouton");
        Button loup = new Button("Loup");

        roche.setOnAction(event -> {
            lequel = 1;
        });
        herbe.setOnAction(event -> {
            lequel = 2;
        });
        cactus.setOnMouseClicked(event -> {
            lequel = 3;
        });
        marguerite.setOnMouseClicked(event -> {
            lequel = 4;
        });
        sortie.setOnMouseClicked(event -> {

            lequel = 5;
        });
        mouton.setOnMouseClicked(event -> {
            lequel = 6;
        });

        loup.setOnMouseClicked(event -> {
            lequel = 7;
        });


        roche.setTranslateX(700);
        roche.setTranslateY(350);
        herbe.setTranslateX(700);
        herbe.setTranslateY(320);
        cactus.setTranslateX(700);
        cactus.setTranslateY(290);
        marguerite.setTranslateX(700);
        marguerite.setTranslateY(260);
        sortie.setTranslateX(700);
        sortie.setTranslateY(230);
        mouton.setTranslateX(700);
        mouton.setTranslateY(200);
        loup.setTranslateX(700);
        loup.setTranslateY(170);





        for (Case c : plateau.getCases()) {
            choisi(c);
            }

        stackPane.getChildren().add(gridPane);
        stackPane.getChildren().addAll(roche,herbe,cactus,marguerite,sortie,mouton,loup);

    }


}
