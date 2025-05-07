package org.example.demo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

public class PlateauGUI {

    private Plateau plateau;
    private StackPane stackPane;
    private GridPane gridPane;

    public PlateauGUI(Plateau plateau,StackPane stackPane) {
        this.plateau = plateau;
        this.stackPane = stackPane;
        gridPane = new GridPane();
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

        gridPane.add(image(new Image(Objects.requireNonNull(getClass().getResource("/mouton.png")).toExternalForm())), x, y);
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
