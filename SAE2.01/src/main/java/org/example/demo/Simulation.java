package org.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

public class Simulation extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Simulation.class.getResource("simul.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Mange Moi si tu peux !");
        stage.setScene(scene);
        Image image1 = new Image("file:Loup.png");
        stage.getIcons().add(image1);
        stage.show();
    }

    public static void main(String[] args) {
        Plateau plateau = new Plateau(5,5);
        plateau.setCaseFinal(plateau.getCase(4,3));
        System.out.println(plateau);
        System.out.println(plateau.verifier());

        launch();
    }
}
