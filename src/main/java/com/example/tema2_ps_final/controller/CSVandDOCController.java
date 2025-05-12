package com.example.tema2_ps_final.controller;

import com.example.tema2_ps_final.model.viewmodel.CSVandDOCViewModel;
import com.example.tema2_ps_final.view.CSVandDOCView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.io.IOException;

public class CSVandDOCController {
    private final CSVandDOCViewModel viewModel;
    private final CSVandDOCView view;
    private final Parent viewRoot;

    public CSVandDOCController() {
        this.viewModel = new CSVandDOCViewModel();
       // this.view = new CSVandDOCView();

        Parent loadedViewRoot;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tema2_ps_final/CsvDocView.fxml"));
            loadedViewRoot = loader.load();
            this.view = loader.getController();
        } catch (IOException e) {
            throw new RuntimeException("Nu s-a putut încărca CSVandDOCView.fxml", e);
        }
        this.viewRoot = loadedViewRoot;


        setupEventHandlers();
    }

    public void setupEventHandlers() {
        view.getExportButton().setOnAction(e -> handleExportCSV());
    }

    private void handleExportCSV() {
        Integer cofetarieId = view.getCofetarieId();

        if (cofetarieId != null && cofetarieId > 0) {
            // Setăm ID-ul cofetăriei în ViewModel și apelăm metoda cu parametrul
            viewModel.exportCSV(cofetarieId);
        } else {
            // Mesaj dacă ID-ul introdus nu este valid
            showMessage("Eroare", "Introduceți un ID valid pentru cofetărie.");
        }
    }

    public Parent getViewRoot() {
        return this.viewRoot;
    }

    private void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
