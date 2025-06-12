package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.example.model.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlateauGUI {

    private Plateau plateau;
    private StackPane stackPane;
    private GridPane gridPane;
    private Node ancienElementMouton;
    private Node ancienElementLoup;
    private int[] ancienPosMouton ;
    private int[] ancienPosLoup ;
    private int lequel;
    private Button valide;
    private Scene scene;
    private int nbPas;
    private int nbTours;
    private ArrayList<Case> cases;

    public PlateauGUI(Plateau plateau,StackPane stackPane,Scene scene) {
        this.nbTours = 1;
        this.plateau = plateau;
        this.scene = scene;
        this.stackPane = stackPane;
        gridPane = new GridPane();
        this.ancienElementLoup = image(new Image(Objects.requireNonNull(getClass().getResource("/rocherv2.png")).toExternalForm()));
        this.ancienElementMouton = image(new Image(Objects.requireNonNull(getClass().getResource("/rocherv2.png")).toExternalForm()));
        this.ancienPosLoup = new int[2];
        this.ancienPosMouton = new int[2];
    }

    private ImageView image(Image image) {
        //créer l'image pour la case de la grille
        ImageView view = new ImageView(image);
        view.setFitWidth((stackPane.getWidth()/1.3)/plateau.length());   // largeur en pixels
        view.setFitHeight((stackPane.getHeight()/1.3)/(plateau.height())); // longueur en pixels

        return view;
    }

    public boolean displayAnimal() {
        //gere tous les déplacements des animaux
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
        ImageView mouton = image(new Image(Objects.requireNonNull(getClass().getResource("/moutonv2.png")).toExternalForm()));
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
        ImageView loup = image(new Image(Objects.requireNonNull(getClass().getResource("/loupv2.png")).toExternalForm()));
        gridPane.add(loup, x, y);
        return true;

    }
    //affichage des éléments en fonction de la grille de la classe plateua
    public void choisi(Case c){
        ImageView ibis ;
        if (c.getType() == Element.Herbe){
            Image i = new Image(Objects.requireNonNull(getClass().getResource("/herbev2.png")).toExternalForm());
            ibis = image(i);
        }else if(c.getType() == Element.Cactus){
            Image i = new Image(Objects.requireNonNull(getClass().getResource("/cactusv2.png")).toExternalForm());
            ibis = image(i);
        }else if(c.getType() == Element.Roche){
            Image i = new Image(Objects.requireNonNull(getClass().getResource("/rocherv2.png")).toExternalForm());
            ibis = image(i);
        }else if(c.getType() == Element.Marguerite){
            Image i = new Image(Objects.requireNonNull(getClass().getResource("/margueritev2.png")).toExternalForm());
            ibis = image(i);
        }else { Image i = new Image(Objects.requireNonNull(getClass().getResource("/margueritev2.png")).toExternalForm());
            ibis = image(i);}
        //affichage de la grille
        ibis.setOnMouseClicked(mouseEvent -> {
            if (lequel < 5 && !(c.getY() == plateau.height()-1 || c.getY() == 0 || c.getX() == plateau.length()-1 || c.getX() == 0)){
                if (lequel == 1){
                    ibis.setImage(new Image(Objects.requireNonNull(getClass().getResource("/rocherv2.png")).toExternalForm()));
                    plateau.getCase(c.getX(),c.getY()).setType(Element.Roche);
                }else if(lequel == 2 ){
                    ibis.setImage(new Image(Objects.requireNonNull(getClass().getResource("/herbev2.png")).toExternalForm()));
                    plateau.getCase(c.getX(),c.getY()).setType(Element.Herbe);
                }else if(lequel == 3 ){
                    ibis.setImage(new Image(Objects.requireNonNull(getClass().getResource("/cactusv2.png")).toExternalForm()));
                    plateau.getCase(c.getX(),c.getY()).setType(Element.Cactus);
                }else if(lequel == 4) {
                    ibis.setImage(new Image(Objects.requireNonNull(getClass().getResource("/margueritev2.png")).toExternalForm()));
                    plateau.getCase(c.getX(), c.getY()).setType(Element.Marguerite);
                }
                valide.setDisable(!plateau.verifier());
            }else if(lequel == 5 && (c.getY() == plateau.height()-1 || c.getY() == 0 || c.getX() == plateau.length()-1 || c.getX() == 0))  {
                ibis.setImage(new Image(Objects.requireNonNull(getClass().getResource("/herbev2.png")).toExternalForm()));
                plateau.getCaseFinal().setType(Element.Roche);
                if (!plateau.getCaseFinal().equals(c)){
                    choisi(plateau.getCaseFinal());
                }

                plateau.getCase(c.getX(), c.getY()).setType(Element.Herbe);
                plateau.setCaseFinal(c);
                valide.setDisable(!plateau.verifier());
            }
            else if (c.equals(plateau.getCaseFinal()) && lequel == 5){
                ibis.setImage(new Image(Objects.requireNonNull(getClass().getResource("/rocherv2.png")).toExternalForm()));
                plateau.getCase(c.getX(),c.getY()).setType(Element.Roche);
            } else if (lequel == 6 && c.getType() != Element.Roche) {
                plateau.getMouton().deplace(c.getX(),c.getY());
                displayAnimal();
            } else if (lequel == 7 && c.getType() != Element.Roche) {
                plateau.getLoup().deplace(c.getX(),c.getY());
                displayAnimal();
            }
        });
        gridPane.add(ibis, c.getX(), c.getY());
        //
    }

    //affiche ce que le mouton a mangé
    public static String enPhrase(List<Element> liste) {
        if (liste.isEmpty()) return "rien.";

        String resultat = "";
        for (int i = 0; i < liste.size(); i++) {
            resultat += liste.get(i);
            if (i < liste.size() - 2) {
                resultat += ", ";
            } else if (i == liste.size() - 2) {
                resultat += " et ";
            }
        }
        return resultat + ".";
    }
    public void show(Boolean auto) {
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.setGridLinesVisible(true);
        //définition des images
        Image rochemere = new Image(Objects.requireNonNull(getClass().getResource("/rocherv2.png")).toExternalForm());

        ImageView roche = new ImageView(rochemere);
        roche.setFitWidth(50);
        roche.setFitHeight(50);
        Image herbemere = new Image(Objects.requireNonNull(getClass().getResource("/herbev2.png")).toExternalForm());

        ImageView herbe = new ImageView(herbemere);
        herbe.setFitWidth(50);
        herbe.setFitHeight(50);

        Image cactusmere = new Image(Objects.requireNonNull(getClass().getResource("/cactusv2.png")).toExternalForm());

        ImageView cactus = new ImageView(cactusmere);

        cactus.setFitWidth(50);
        cactus.setFitHeight(50);

        Image margueritemere = new Image(Objects.requireNonNull(getClass().getResource("/margueritev2.png")).toExternalForm());

        ImageView marguerite = new ImageView(margueritemere);
        marguerite.setFitWidth(50);
        marguerite.setFitHeight(50);

        Image sortiemere = new Image(Objects.requireNonNull(getClass().getResource("/marteauv2.png")).toExternalForm());

        ImageView sortie = new ImageView(sortiemere);
        sortie.setFitWidth(50);
        sortie.setFitHeight(50);

        Image moutonmere = new Image(Objects.requireNonNull(getClass().getResource("/moutonv2.png")).toExternalForm());

        ImageView mouton = new ImageView(moutonmere);
        mouton.setFitWidth(50);
        mouton.setFitHeight(50);

        Image loupmere = new Image(Objects.requireNonNull(getClass().getResource("/loupv2.png")).toExternalForm());

        ImageView loup = new ImageView(loupmere);
        loup.setFitWidth(50);
        loup.setFitHeight(50);
        Button Valide = new Button("Valide");
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Dijkstra", "PEP", "Fourmi", "A*");
        comboBox.setPromptText("Choisissez un algorithme");
        comboBox.setEditable(true);
        comboBox.setTranslateX(350);
        comboBox.setTranslateY(-300);


        valide = Valide;
        //bouton validé pour lancer le jeu
        Valide.setPrefSize(200, 60);
        Valide.setStyle(
                "-fx-background-size: cover;" +
                        "-fx-text-fill: #5c3b00;" +
                        "-fx-font-size: 18px;" +
                        "-fx-background-color: green;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 30;" +
                        "-fx-border-radius: 30;" +
                        "-fx-padding: 10 20 10 20;"
        );
        Valide.setOnMouseEntered(e -> Valide.setStyle("-fx-background-color: #0b8329;" +
                "-fx-background-size: cover;" +
                "-fx-text-fill: #5c3b00;" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 30;" +
                "-fx-border-radius: 30;" +
                "-fx-padding: 10 20 10 20;"
        ));


        Valide.setOnMouseExited(e -> Valide.setStyle("-fx-background-size: cover;" +
                "-fx-text-fill: #5c3b00;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: green;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 30;" +
                "-fx-border-radius: 30;" +
                "-fx-padding: 10 20 10 20;"
        ));
        Valide.setOnMouseClicked(e -> {
            lequel = 0;
            if(auto){
                jeuAuto(comboBox.getValue());
            }else{
                jeu();
            }
        });
        //afficher ce qui est sélectionné
        Label texte5 = new Label("Vous avez selectionné :");
        texte5.setTranslateY(-50);
        texte5.setTranslateX(700);
        texte5.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-text-fill: #333333;" +
                        "-fx-font-size: 10px;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 5 10 5 10;"
        );

        Label texte6 = new Label("Cliquez sur l'image \n pour sélectionner");
        texte6.setTranslateY(0);
        texte6.setTranslateX(700);
        texte6.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-text-fill: #333333;" +
                        "-fx-font-size: 10px;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 5 10 5 10;"
        );


        //choisir en cliquant sur l'image
        roche.setOnMouseClicked(event -> {
            lequel = 1;
            texte6.setText("rocher");
        });
        herbe.setOnMouseClicked(event -> {
            lequel = 2;
            texte6.setText("herbe");
        });
        cactus.setOnMouseClicked(event -> {
            lequel = 3;
            texte6.setText("cactus");
        });
        marguerite.setOnMouseClicked(event -> {
            lequel = 4;
            texte6.setText("marguerite");
        });
        sortie.setOnMouseClicked(event -> {

            lequel = 5;
            texte6.setText("sortie");
        });
        mouton.setOnMouseClicked(event -> {
            lequel = 6;
            texte6.setText("mouton");
        });

        loup.setOnMouseClicked(event -> {
            lequel = 7;
            texte6.setText("loup");
        });

        //deplacer les image cliquable
        roche.setTranslateX(700);
        roche.setTranslateY(350);
        herbe.setTranslateX(700);
        herbe.setTranslateY(300);
        cactus.setTranslateX(700);
        cactus.setTranslateY(250);
        marguerite.setTranslateX(700);
        marguerite.setTranslateY(200);
        sortie.setTranslateX(700);
        sortie.setTranslateY(150);
        mouton.setTranslateX(700);
        mouton.setTranslateY(100);
        loup.setTranslateX(700);
        loup.setTranslateY(50);
        Valide.setTranslateX(0);
        Valide.setTranslateY(-300);






        for (Case c : plateau.getAllCases()) {
            choisi(c);
        }

        stackPane.getChildren().add(gridPane);
        stackPane.getChildren().addAll(roche,herbe,cactus,marguerite,sortie,mouton,loup,Valide,texte5,texte6);
        if (auto){
            stackPane.getChildren().add(comboBox);
        }
        displayAnimal();


    }

    //afficher le nombre de pas
    private void texte(Label l , String animal , int nbPas){
        l.setText(animal + " : " + nbPas + " pas restant");

    }

    //bouger l'animal
    private void bouge(Animal a, KeyCode k, Label text){
        //commande du jeu sur le clavier
        if (k == KeyCode.UP){
            if (plateau.getCase(a.getX(),a.getY()-1).getType() != Element.Roche){
                a.deplace(a.getX(),a.getY()-1);
                displayAnimal();
                nbPas--;
            }
        }else if (k == KeyCode.DOWN){
            if (plateau.getCase(a.getX(),a.getY()+1).getType() != Element.Roche){
                a.deplace(a.getX(),a.getY()+1);
                displayAnimal();
                nbPas--;
            }
        }else if (k == KeyCode.LEFT){
            if (plateau.getCase(a.getX()-1,a.getY()).getType() != Element.Roche){
                a.deplace(a.getX()-1,a.getY());
                displayAnimal();
                nbPas--;
            }
        }else if (k == KeyCode.RIGHT){
            if (plateau.getCase(a.getX()+1,a.getY()).getType() != Element.Roche){
                a.deplace(a.getX()+1,a.getY());
                displayAnimal();
                nbPas--;
            }
        }
        texte(text,a.toString(),nbPas);
        if (a instanceof Mouton && nbPas == 0){
            nbPas = 3;
            lequel = 11;
            texte(text,"Loup",nbPas);
            a.mange();
        }else if (a instanceof Loup && nbPas == 0){
            nbPas = plateau.getMouton().getNbCase();
            lequel = 10;
            texte(text,"Mouton",nbPas);
            nbTours++;
        }
        //afficher le nombre de tours
        Label tour = new Label("Vous avez fini le jeu en "+nbTours+" tours");
        if (nbTours == 1){tour.setText("Vous avez fini le jeu en "+nbTours+" tour");};
        tour.setTranslateY(200);
        tour.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-text-fill: #333333;" +
                        "-fx-font-size: 30px;" +
                        "-fx-background-radius: 8;" +
                        "-fx-background-color: #d2d0d0;" +
                        "-fx-padding: 5 10 5 10;"
        );


        //affiche ce que le mouton a mangé
        Label moutonamange = new Label("Le mouton a mangé : "+enPhrase(plateau.getMouton().getNourriture()));
        moutonamange.setTranslateY(150);
        moutonamange.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-text-fill: #333333;" +
                        "-fx-font-size: 30px;" +
                        "-fx-background-radius: 8;" +
                        "-fx-background-color: #d2d0d0;" +
                        "-fx-padding: 5 10 5 10;"
        );


        if (plateau.getMouton().getX() == plateau.getLoup().getX() && plateau.getMouton().getY() == plateau.getLoup().getY()){
            stackPane.getChildren().clear();
            Image image3 = new Image(Objects.requireNonNull(getClass().getResource("/LoupGagne.png")).toExternalForm());
            ImageView backgroundView = new ImageView(image3);
            backgroundView.setFitHeight(800);
            backgroundView.setFitWidth(1600);

            Button btn3 = new Button();

            btn3.opacityProperty().setValue(0);
            btn3.setTranslateX(0);
            btn3.setTranslateY(300);
            btn3.setPrefSize(400, 100);
            btn3.setOnAction(e -> {
                System.exit(0);
            });

            stackPane.getChildren().addAll(backgroundView,tour,btn3,moutonamange);

        } else if (plateau.getMouton().getX() == plateau.getCaseFinal().getX() && plateau.getMouton().getY() == plateau.getCaseFinal().getY()) {
            stackPane.getChildren().clear();
            Image image3 = new Image(Objects.requireNonNull(getClass().getResource("/MoutonGagne.png")).toExternalForm());
            ImageView backgroundView = new ImageView(image3);
            backgroundView.setFitHeight(800);
            backgroundView.setFitWidth(1600);

            Button btn3 = new Button();

            btn3.opacityProperty().setValue(0);
            btn3.setTranslateX(0);
            btn3.setTranslateY(300);
            btn3.setPrefSize(400, 100);
            btn3.setOnAction(e -> {
                System.exit(0);
            });

            stackPane.getChildren().addAll(backgroundView,tour,btn3,moutonamange);
        }
    }
    //jeu après le choix des éléments
    public void jeu() {
        Image image = new Image(Objects.requireNonNull(getClass().getResource("/background.png")).toExternalForm());
        ImageView backgroundView = new ImageView(image);
        backgroundView.setFitWidth(1600);
        backgroundView.setPreserveRatio(true);
        Label texte = new Label("Mouton : 2 pas restant");
        Image image2 = new Image(Objects.requireNonNull(getClass().getResource("/fleche202.png")).toExternalForm());
        ImageView fleche = new ImageView(image2);

        Label texte2 = new Label("appuyez sur les touches \n du clavier suivantes pour \n faire avancer l'animal");
        texte2.setTranslateX(700);
        texte2.setTranslateY(100);
        texte2.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-text-fill: #333333;" +
                        "-fx-font-size: 10px;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 5 10 5 10;"
        );



        fleche.setTranslateX(700);
        fleche.setTranslateY(250);
        fleche.setFitWidth(50);
        fleche.setFitHeight(200);
        texte.setTranslateY(-300);
        texte.setStyle(
                "-fx-background-color: #f0f0f0;" +
                        "-fx-text-fill: #333333;" +
                        "-fx-font-size: 30px;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 5 10 5 10;"
        );
        Mouton m = plateau.getMouton();
        Loup l = plateau.getLoup();
        lequel = 10;
        nbPas = 2;


        stackPane.getChildren().clear();
        stackPane.getChildren().addAll(backgroundView, gridPane, texte,fleche,texte2);


        scene.setOnKeyPressed(e -> {
            if (e.getCode().isArrowKey() && lequel ==10 ){
                bouge(m,e.getCode(),texte);
            }else if (e.getCode().isArrowKey() && lequel == 11){
                bouge(l,e.getCode(),texte);
            }
        });
    }

    private void typeCheminMouton(String method){
        if (method.equals("Dijkstra")){
            cases = new ArrayList<>(plateau.getMouton().dijkstra());
        }else if (method.equals("Fourmi")){
            cases = new ArrayList<>(plateau.getMouton().fourmi());
        }else if (method.equals("PEP")){
            cases = new ArrayList<>(plateau.getMouton().pep());
        }else{
            cases = new ArrayList<>(plateau.getMouton().pep());
        }
    }

    private void typeCheminLoup(String method){
        if (method.equals("Dijkstra")){
            cases = new ArrayList<>(plateau.getLoup().dijkstra());
        }else if (method.equals("Fourmi")){
            cases = new ArrayList<>(plateau.getLoup().fourmi());
        }else if (method.equals("PEP")) {
            cases = new ArrayList<>(plateau.getLoup().pep());
        }
    }

    public void jeuAuto(String method){
        Image image = new Image(Objects.requireNonNull(getClass().getResource("/background.png")).toExternalForm());
        ImageView backgroundView = new ImageView(image);
        backgroundView.setFitWidth(1600);
        backgroundView.setPreserveRatio(true);
        Label texte = new Label("Mouton : 2 pas restant");
        texte.setTranslateY(-300);
        texte.setStyle(
                "-fx-background-color: #f0f0f0;" +
                        "-fx-text-fill: #333333;" +
                        "-fx-font-size: 30px;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 5 10 5 10;"
        );
        stackPane.getChildren().clear();
        stackPane.getChildren().addAll(backgroundView, gridPane,texte);
        Mouton m = plateau.getMouton();
        Loup l = plateau.getLoup();
        lequel = 10;
        nbPas = 2;
        typeCheminMouton(method);
        cases.removeFirst();
        scene.setOnKeyPressed(e -> {
            if ( lequel ==10 ){
                bougeAuto(m,texte,method);
            }else if (lequel == 11){
                bougeAuto(l,texte,method);
            }
        });
    }

    public void bougeAuto( Animal a, Label text,String method) {
        Case c = cases.getFirst();
        a.deplace(c.getX(), c.getY());
        displayAnimal();
        cases.remove(c);
        nbPas--;
        texte(text,a.toString(),nbPas);
        if (a instanceof Mouton && nbPas == 0){
            nbPas = 3;
            lequel = 11;
            texte(text,"Loup",nbPas);
            a.mange();
            typeCheminLoup(method);
            cases.removeFirst();
        }else if (a instanceof Loup && nbPas == 0){
            nbPas = plateau.getMouton().getNbCase();
            lequel = 10;
            texte(text,"Mouton",nbPas);
            nbTours++;
            typeCheminMouton(method);
            cases.removeFirst();
        }
        //afficher le nombre de tours
        Label tour = new Label("Vous avez fini le jeu en "+nbTours+" tours");
        if (nbTours == 1){tour.setText("Vous avez fini le jeu en "+nbTours+" tour");};
        tour.setTranslateY(200);
        tour.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-text-fill: #333333;" +
                        "-fx-font-size: 30px;" +
                        "-fx-background-radius: 8;" +
                        "-fx-background-color: #d2d0d0;" +
                        "-fx-padding: 5 10 5 10;"
        );


        //affiche ce que le mouton a mangé
        Label moutonamange = new Label("Le mouton a mangé : "+enPhrase(plateau.getMouton().getNourriture()));
        moutonamange.setTranslateY(150);
        moutonamange.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-text-fill: #333333;" +
                        "-fx-font-size: 30px;" +
                        "-fx-background-radius: 8;" +
                        "-fx-background-color: #d2d0d0;" +
                        "-fx-padding: 5 10 5 10;"
        );


        if (plateau.getMouton().getX() == plateau.getLoup().getX() && plateau.getMouton().getY() == plateau.getLoup().getY()){
            stackPane.getChildren().clear();
            Image image3 = new Image(Objects.requireNonNull(getClass().getResource("/LoupGagne.png")).toExternalForm());
            ImageView backgroundView = new ImageView(image3);
            backgroundView.setFitHeight(800);
            backgroundView.setFitWidth(1600);

            Button btn3 = new Button();

            btn3.opacityProperty().setValue(0);
            btn3.setTranslateX(0);
            btn3.setTranslateY(300);
            btn3.setPrefSize(400, 100);
            btn3.setOnAction(e -> {
                System.exit(0);
            });

            stackPane.getChildren().addAll(backgroundView,tour,btn3,moutonamange);

        } else if (plateau.getMouton().getX() == plateau.getCaseFinal().getX() && plateau.getMouton().getY() == plateau.getCaseFinal().getY()) {
            stackPane.getChildren().clear();
            Image image3 = new Image(Objects.requireNonNull(getClass().getResource("/MoutonGagne.png")).toExternalForm());
            ImageView backgroundView = new ImageView(image3);
            backgroundView.setFitHeight(800);
            backgroundView.setFitWidth(1600);

            Button btn3 = new Button();

            btn3.opacityProperty().setValue(0);
            btn3.setTranslateX(0);
            btn3.setTranslateY(300);
            btn3.setPrefSize(400, 100);
            btn3.setOnAction(e -> {
                System.exit(0);
            });
            stackPane.getChildren().addAll(backgroundView,tour,btn3,moutonamange);
        }
    }

}
