package com.example.tema2_ps_final;

import com.example.tema2_ps_final.controller.CSVandDOCController;
import com.example.tema2_ps_final.controller.CofetarieController;
import com.example.tema2_ps_final.controller.PrajituraController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        PrajituraController prajituraController = new PrajituraController();
        CofetarieController cofetarieController = new CofetarieController();
        CSVandDOCController csvAndDocController = new CSVandDOCController();

        Parent cakeView = prajituraController.getViewRoot();
        Parent cofetarieView = cofetarieController.getViewRoot();
        Parent csvAndDocView = csvAndDocController.getViewRoot();

        Button buttonCakes = new Button("Cakes");
        Button buttonConfectionaries = new Button("Confectionaries");
        Button buttonCsvDoc = new Button("CSV and Doc");

        buttonCakes.setOnAction(e -> root.setCenter(cakeView));
        buttonConfectionaries.setOnAction(e -> root.setCenter(cofetarieView));
        buttonCsvDoc.setOnAction(e -> root.setCenter(csvAndDocView));

        // Bara de meniu
        HBox menuBar = new HBox(20, buttonCakes, buttonConfectionaries, buttonCsvDoc);
        menuBar.setPadding(new Insets(20));

        root.setTop(menuBar);
        root.setCenter(cakeView);

        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sweet Treats Management");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
