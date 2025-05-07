package org.example.demo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
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

    public PlateauGUI(Plateau plateau,StackPane stackPane) {
        this.plateau = plateau;
        this.stackPane = stackPane;
        gridPane = new GridPane();
        this.ancienElementLoup = image(new Image(Objects.requireNonNull(getClass().getResource("/herbe.png")).toExternalForm()));
        this.ancienElementMouton = image(new Image(Objects.requireNonNull(getClass().getResource("/herbe.png")).toExternalForm()));
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

    public void show() {
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.setGridLinesVisible(true);

        for (Case c : plateau.getCases()){
            Element e = c.getType();
            if(e.equals(Element.Cactus)){
                Image i = new Image(Objects.requireNonNull(getClass().getResource("/cactus.png")).toExternalForm());
                gridPane.add(image(i),c.getX(),c.getY());
            } else if (e.equals(Element.Herbe)) {
                Image i = new Image(Objects.requireNonNull(getClass().getResource("/herbe.png")).toExternalForm());
                gridPane.add(image(i),c.getX(),c.getY());
            } else if (e.equals(Element.Marguerite)) {
                Image i = new Image(Objects.requireNonNull(getClass().getResource("/margueritte.png")).toExternalForm());
                gridPane.add(image(i),c.getX(),c.getY());
            } else if(e.equals(Element.Roche)){
                Image i = new Image(Objects.requireNonNull(getClass().getResource("/rocher.png")).toExternalForm());
                gridPane.add(image(i),c.getX(),c.getY());
            }
        }
        stackPane.getChildren().add(gridPane);
    }


}
