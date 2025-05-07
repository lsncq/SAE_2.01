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
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;


public class SimulationCorentin extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StackPane root = new StackPane();
        StackPane root1 = new StackPane();

        root.setStyle("-fx-background-color: blue;");
        Label tf = new Label("Mange moi si tu peux");
        tf.setStyle("-fx-text-fill: white;");
        tf.setFont(Font.loadFont(getClass().getResource("/BebasNeue-Regular.ttf").toExternalForm(), 125));

        Image image = new Image(Objects.requireNonNull(getClass().getResource("/background.png")).toExternalForm());
        ImageView imageView = new ImageView(image);

        Image image1 = new Image(Objects.requireNonNull(getClass().getResource("/regles_du_jeu.png")).toExternalForm());
        ImageView imageView1 = new ImageView(image1);

        imageView.setFitWidth(1550);
        imageView1.setFitWidth(1550);
        imageView.setPreserveRatio(true);
        imageView1.setPreserveRatio(true);

        Button btn1 = new Button("JOUER");
        Button btn2 = new Button("REGLES DU JEU");
        Button btn4 = new Button("SIMULATION");
        Button btn3 = new Button();
        btn3.opacityProperty().setValue(0.0);
        btn3.setTranslateX(-700);
        btn3.setTranslateY(-350);
        btn3.setPrefSize(100, 100);


        btn1.setPrefSize(200, 60);
        btn1.setStyle(
                "-fx-background-size: cover;" +
                        "-fx-text-fill: #5c3b00;" +
                        "-fx-font-size: 18px;" +
                        "-fx-background-color: green;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 30;" +
                        "-fx-border-radius: 30;" +
                        "-fx-padding: 10 20 10 20;"
        );
        btn1.setOnMouseEntered(e -> btn1.setStyle("-fx-background-color: #0b8329;" +
                "-fx-background-size: cover;" +
                "-fx-text-fill: #5c3b00;" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 30;" +
                "-fx-border-radius: 30;" +
                "-fx-padding: 10 20 10 20;"
        ));

        btn1.setOnMouseExited(e -> btn1.setStyle("-fx-background-size: cover;" +
                "-fx-text-fill: #5c3b00;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: green;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 30;" +
                "-fx-border-radius: 30;" +
                "-fx-padding: 10 20 10 20;"
        ));

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

        btn4.setPrefSize(200, 60);
        btn4.setStyle(
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
        VBox vbox3 = new VBox(btn4);




        FXMLLoader fxmlLoader = new FXMLLoader(Simulation.class.getResource("simul.fxml"));

        Scene scene = new Scene(root, 1550, 800);

        tf.setTranslateY(-100);

        vbox.setTranslateX(scene.getWidth()/2-100);
        vbox.setTranslateY(450);

        vbox2.setTranslateX(scene.getWidth()/2-100);
        vbox2.setTranslateY(550);

        vbox3.setTranslateX(scene.getWidth()/2-100);
        vbox3.setTranslateY(650);




        root.getChildren().addAll(imageView,tf,vbox,vbox2,vbox3);
        btn2.setOnAction(event -> {

            root.getChildren().clear();
            root.getChildren().add(imageView1);
            root.getChildren().add(btn3);
        });

        btn3.setOnAction(event -> {

            root.getChildren().addAll(imageView,tf,vbox,vbox2,vbox3);

        });


        stage.setTitle("Mange Moi si tu peux !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        System.out.println(menu);

        launch();
    }
}
