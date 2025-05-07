package org.example.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

public class SimulationCorentin extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StackPane root = new StackPane();

        root.setStyle("-fx-background-color: blue;");
        Label tf = new Label("Mange moi si tu peux");
        tf.setStyle("-fx-text-fill: white;");
        tf.setFont(new Font("Impact", 40));

        Button btn1 = new Button("JOUER");
        Button btn2 = new Button("REGLES DU JEU");

        btn1.setPrefSize(200, 60);
        btn1.setStyle(
                "-fx-background-size: cover;" +
                        "-fx-text-fill: #5c3b00;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 30;" +
                        "-fx-border-radius: 30;" +
                        "-fx-padding: 10 20 10 20;"
        );

        btn2.setPrefSize(200, 60);
        btn2.setStyle(
                "-fx-background-size: cover;" +
                        "-fx-text-fill: #5c3b00;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 30;" +
                        "-fx-border-radius: 30;" +
                        "-fx-padding: 10 20 10 20;"
        );

        VBox vbox = new VBox(btn1);
        VBox vbox2 = new VBox(btn2);




        FXMLLoader fxmlLoader = new FXMLLoader(Simulation.class.getResource("simul.fxml"));

        Scene scene = new Scene(root, 1600, 800);

        vbox.setTranslateX(scene.getWidth()/2-100);
        vbox.setTranslateY(scene.getHeight()-scene.getHeight()/2.5);

        vbox2.setTranslateX(scene.getWidth()/2-100);
        vbox2.setTranslateY(scene.getHeight()-scene.getHeight()/4);

        root.getChildren().addAll(tf,vbox,vbox2);


        stage.setTitle("Mange si tu peux !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        System.out.println(menu);

        launch();
    }
}
