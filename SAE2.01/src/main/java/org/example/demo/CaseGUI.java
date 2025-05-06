package org.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CaseGUI {

    private Case case1;
    private StackPane stackPane;

    public CaseGUI(Case case1, StackPane stackPane) {
        this.case1 = case1;
        this.stackPane = stackPane;
    }

    public void show(){
        Element e = case1.getType();
        if(e.equals(Element.Cactus)){
            Image i = new Image("file:/Loup.png");
            ImageView imageView = new ImageView(i);
            stackPane.getChildren().add(imageView);
        }
    }

}
