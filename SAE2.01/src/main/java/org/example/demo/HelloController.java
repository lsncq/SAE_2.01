package org.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;

public class HelloController {
    @FXML
    private Label newGame;

    @FXML
    protected void onHelloButtonClick() {
        newGame.setText("Welcome to JavaFX Application!");
        newGame.setStyle("-fx-text-fill: green;");
    }
}