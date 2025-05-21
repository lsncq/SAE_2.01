package org.example.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.view.Menu;


public class MainSimul extends Application {
    @Override
    public void start(Stage stage){
        stage.setTitle("Simulation Demo");
        Menu menu = new Menu(stage);


    }




public static void main(String[] args) {
    launch();
}
}
