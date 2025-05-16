package org.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

public class Simulation extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Plateau plateau = new Plateau(20,10);
        plateau.setCaseFinal(plateau.getCase(4,3));
        System.out.println(plateau);
        System.out.println(plateau.verifier());




        StackPane root = new StackPane();


        Scene scene = new Scene(root,1600,800);
        stage.setTitle("Mange Moi si tu peux !");


        stage.setScene(scene);

        PlateauGUI p = new PlateauGUI(plateau,root);
        p.show();
        p.displayAnimal();
        //plateau.getMouton().deplace(5,5);
        //plateau.getLoup().deplace(2,2);
        //p.displayAnimal();
        p.displayMouton();




        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
