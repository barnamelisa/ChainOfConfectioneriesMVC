package com.example.tema2_ps_final.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

import com.example.tema2_ps_final.view.PrajituraView;
import com.example.tema2_ps_final.model.Prajitura;
import com.example.tema2_ps_final.model.viewmodel.PrajituraViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
public class PrajituraController {

    private final PrajituraViewModel viewModel;
    private final PrajituraView view;
    private final Parent viewRoot;

    private Locale currentLocale;
    private ResourceBundle bundle;

    public PrajituraController() {
        this.viewModel = new PrajituraViewModel();
        Parent loadedView;
        PrajituraView loadedController;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tema2_ps_final/PrajituraView.fxml"));
            loadedView = loader.load();
            System.out.println("✅ Loaded FXML successfully: " + loadedView);

            loadedController = loader.getController();
            System.out.println("✅ Controller class: " + loadedController.getClass());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load PrajituraView.fxml", e);
        }

        this.viewRoot = loadedView;
        this.view = loadedController;

        this.viewModel.addObserver(view);
        this.view.setTableItems(viewModel.getPrajituri());

        setupEventHandlers();
        currentLocale = Locale.getDefault(); // implicit
        updateLanguage();
    }

    private void updateLanguage() {
        bundle = ResourceBundle.getBundle("com.example.tema2_ps_final.messages", currentLocale);

        view.getAddButton().setText(bundle.getString("add"));
        view.getUpdateButton().setText(bundle.getString("update"));
        view.getDeleteButton().setText(bundle.getString("delete"));
        view.getClearFieldsButton().setText(bundle.getString("clear"));
        view.getSearchButton().setText(bundle.getString("search"));
        view.getAvailabilityButton().setText(bundle.getString("availability"));
        view.getValidityButton().setText(bundle.getString("validity"));

        view.getNumePrajituraColumn().setText(bundle.getString("name"));
        view.getDescriereColumn().setText(bundle.getString("description"));
        view.getCofetarieIdColumn().setText(bundle.getString("shopId"));
        view.getPretColumn().setText(bundle.getString("price"));
        view.getDataExpirareColumn().setText(bundle.getString("expiryDate"));
        view.getDataProductieColumn().setText(bundle.getString("productionDate"));
        view.getImagineColumn().setText(bundle.getString("image"));

        view.getMessageLabel().setText(bundle.getString("messageDataLoaded"));
    }

    public Parent getViewRoot() {
        return this.viewRoot;
    }

    private void setupEventHandlers() {
        // Language buttons
        view.getEnglishButton().setOnAction(e -> handleLanguageChange("English"));
        view.getFrenchButton().setOnAction(e -> handleLanguageChange("Français"));
        view.getRomanianButton().setOnAction(e -> handleLanguageChange("Română"));

        // Other event handlers for buttons like Add, Update, Delete, etc.
        view.getAddButton().setOnAction(e -> handleAdaugaPrajitura());
        view.getUpdateButton().setOnAction(e -> {
            if (view.getSelectedCake() != null) {
                handleUpdatePrajitura(view.getSelectedCake());
            } else {
                view.showMessage("Error", "Please select a cake to update.");
            }
        });
        view.getDeleteButton().setOnAction(e -> {
            if (view.getSelectedCake() != null) {
                handleStergerePrajitura(view.getSelectedCake().getId());
            } else {
                view.showMessage("Error", "Please select a cake to delete.");
            }
        });
        view.getSearchButton().setOnAction(e -> handleCautaDupaNume(view.getSearchName()));
        view.getAvailabilityButton().setOnAction(e -> handleDisponibilitate(view.getAvailabilityCofetarieId()));
        view.getValidityButton().setOnAction(e -> handleNevalabile(view.getValidityCofetarieId()));
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
        updateLanguage();
    }

    public void handleAdaugaPrajitura() {
        try {
            String nume = view.getNumePrajitura();
            String descriere = view.getDescriere();
            BigDecimal pret = new BigDecimal(view.getPret());
            int cofetarieId = Integer.parseInt(view.getCofetarieId());
            LocalDate dataExpirare = view.getDataExpirare();
            LocalDate dataProductie = view.getDataProductie();
            String imagine = view.getImagine();

            Prajitura prajituraNoua = new Prajitura(nume, descriere, cofetarieId, pret, dataExpirare, dataProductie, imagine);
            viewModel.adaugaPrajitura(prajituraNoua);

            view.showMessage("Success", "Cake added successfully!");
        } catch (Exception e) {
            view.showMessage("Error", "Incorrect input: " + e.getMessage());
        }
    }

    public void handleStergerePrajitura(int id) {
        viewModel.stergePrajitura(id);
        view.showMessage("Deleted", "Cake deleted successfully.");
    }

    public void handleUpdatePrajitura(Prajitura prajitura) {
        viewModel.actualizeazaPrajitura(prajitura);
        view.showMessage("Updated", "Cake updated successfully.");
    }

    public void handleCautaDupaNume(String nume) {
        String result = viewModel.cautaPrajituraDupaNume(nume);
        view.showMessage("Search", result.isEmpty() ? "No cake found." : result);
    }

    public void handleDisponibilitate(int idCofetarie) {
        var results = viewModel.prajituriDisponibile(idCofetarie);
        view.setTableItems(FXCollections.observableArrayList(results));
    }

    public void handleNevalabile(int idCofetarie) {
        var results = viewModel.prajituriNevalabile(idCofetarie);
        view.setTableItems(FXCollections.observableArrayList(results));
    }
}
