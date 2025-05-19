package org.example.demo;

import javafx.application.Application;
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


public class MainSimul extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StackPane root = new StackPane();
        StackPane root1 = new StackPane();

        root.setStyle("-fx-background-color: blue;");
        Label tf = new Label("Mange moi si tu peux");
        tf.setStyle("-fx-text-fill: white;");
        tf.setFont(Font.loadFont(getClass().getResource("/BebasNeue-Regular.ttf").toExternalForm(), 125));

        Label txt = new Label("Options du labyrinthe");
        txt.setStyle("-fx-text-fill: white;");
        txt.setFont(Font.loadFont(getClass().getResource("/BebasNeue-Regular.ttf").toExternalForm(), 125));
        txt.setTranslateY(-100);

        Label txt1 = new Label("Choisi la taille");
        txt1.setStyle("-fx-text-fill: white;");
        txt1.setFont(Font.loadFont(getClass().getResource("/BebasNeue-Regular.ttf").toExternalForm(), 80));
        txt1.setTranslateY(20);

        Label txt2 = new Label("X");
        txt2.setStyle("-fx-text-fill: white;");
        txt2.setFont(Font.loadFont(getClass().getResource("/BebasNeue-Regular.ttf").toExternalForm(), 80));
        txt2.setTranslateX(-240);
        txt2.setTranslateY(150);


        Label txt3 = new Label("Y");
        txt3.setStyle("-fx-text-fill: white;");
        txt3.setFont(Font.loadFont(getClass().getResource("/BebasNeue-Regular.ttf").toExternalForm(), 80));
        txt3.setTranslateX(10);
        txt3.setTranslateY(150);




        Image image = new Image(Objects.requireNonNull(getClass().getResource("/background.png")).toExternalForm());
        ImageView backgroundView = new ImageView(image);

        Image image1 = new Image(Objects.requireNonNull(getClass().getResource("/regles_du_jeu.png")).toExternalForm());
        ImageView imageView1 = new ImageView(image1);

        Image Simulation = new Image(Objects.requireNonNull(getClass().getResource("/Simulation.png")).toExternalForm());
        ImageView SimulationView = new ImageView(Simulation);

        backgroundView.setFitWidth(1550);
        imageView1.setFitWidth(1550);
        backgroundView.setPreserveRatio(true);
        imageView1.setPreserveRatio(true);

        Button btn1 = new Button("JOUER");
        Button btn2 = new Button("REGLES DU JEU");
        Button btn4 = new Button("SIMULATION");
        Button btn5 = new Button("VALIDER");

        TextField tf1 = new TextField();
        TextField tf2 = new TextField();

        tf1.setTranslateX(-120);
        tf1.setTranslateY(150);
        tf1.setPromptText("Entrez votre x");
        tf1.setMaxWidth(160);
        tf1.setMaxHeight(80);
        tf1.setStyle(
                "-fx-background-color: #f0f0f0;" +        // couleur de fond
                        "-fx-text-fill: #333333;" +               // couleur du texte
                        "-fx-font-size: 14px;" +                  // taille du texte
                        "-fx-background-radius: 8;" +             // coins arrondis
                        "-fx-padding: 5 10 5 10;"                 // padding interne
        );

        tf2.setTranslateX(120);
        tf2.setTranslateY(150);
        tf2.setPromptText("Entrez votre y");
        tf2.setMaxWidth(160);
        tf2.setMaxHeight(80);
        tf2.setStyle(
                "-fx-background-color: #f0f0f0;" +        // couleur de fond
                        "-fx-text-fill: #333333;" +               // couleur du texte
                        "-fx-font-size: 14px;" +                  // taille du texte
                        "-fx-background-radius: 8;" +             // coins arrondis
                        "-fx-padding: 5 10 5 10;"                 // padding interne
        );


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

        btn5.setTranslateY(240);
        btn5.setStyle(
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






        Scene scene = new Scene(root, 1600, 800);

        tf.setTranslateY(-100);

        vbox.setTranslateX(scene.getWidth()/2-100);
        vbox.setTranslateY(450);

        vbox2.setTranslateX(scene.getWidth()/2-100);
        vbox2.setTranslateY(550);

        vbox3.setTranslateX(scene.getWidth()/2-100);
        vbox3.setTranslateY(650);




        root.getChildren().addAll(backgroundView,tf,vbox,vbox2,vbox3);
        btn2.setOnAction(event -> {

            root.getChildren().clear();
            root.getChildren().add(imageView1);
            root.getChildren().add(btn3);
        });

        btn3.setOnAction(event -> {

            root.getChildren().addAll(backgroundView,tf,vbox,vbox2,vbox3);

        });

        btn4.setOnAction(event -> {
            root.getChildren().clear();
            root.getChildren().addAll(SimulationView,btn3);

        });

        btn1.setOnAction(event -> {
            root.getChildren().clear();
            root.getChildren().addAll(backgroundView,txt,txt1,txt2,txt3,tf1,tf2,btn5);
        });

        btn5.setOnAction(event -> {
            System.out.println(tf1.getText()+tf2.getText());
            root.getChildren().clear();
            root.getChildren().add(backgroundView);
            int x = Integer.parseInt(tf1.getText());
            int y = Integer.parseInt(tf2.getText());
            Plateau plateau = new Plateau(x,y);
            plateau.setCaseFinal(plateau.getCase(1,0));
            PlateauGUI p = new PlateauGUI(plateau,root);
            p.show();
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
