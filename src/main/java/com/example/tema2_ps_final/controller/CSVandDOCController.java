package com.example.tema2_ps_final.controller;

import com.example.tema2_ps_final.model.viewmodel.CSVandDOCViewModel;
import com.example.tema2_ps_final.view.CSVandDOCView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class CSVandDOCController {
    private final CSVandDOCViewModel viewModel;
    private final CSVandDOCView view;
    private final Parent viewRoot;

    private Locale currentLocale;
    private ResourceBundle bundle;

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

        this.currentLocale = Locale.getDefault(); // Limbajul implicit
        updateLanguage();

        setupEventHandlers();
    }

    private void updateLanguage() {
        bundle = ResourceBundle.getBundle("com.example.tema2_ps_final.messages", currentLocale);

        // Actualizează textul butoanelor și altor elemente
        view.getExportButton().setText(bundle.getString("export"));
        view.getMessageLabel().setText(bundle.getString("messageExportSuccess"));
    }

    public void setupEventHandlers() {
        view.getEnglishButton().setOnAction(e -> handleLanguageChange("English"));
        view.getFrenchButton().setOnAction(e -> handleLanguageChange("Français"));
        view.getRomanianButton().setOnAction(e -> handleLanguageChange("Română"));
        view.getExportButton().setOnAction(e -> handleExportCSV());
    }

    private void handleLanguageChange(String language) {
        switch (language) {
            case "English":
                currentLocale = Locale.ENGLISH;
                break;
            case "Français":
                currentLocale = Locale.FRENCH;
                break;
            case "Română":
                currentLocale = new Locale("ro");
                break;
            default:
                currentLocale = Locale.getDefault();
        }
        updateLanguage(); // Actualizează textul pe baza noii limbi
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
