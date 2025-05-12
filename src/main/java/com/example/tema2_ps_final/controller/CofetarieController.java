package com.example.tema2_ps_final.controller;

import com.example.tema2_ps_final.model.Cofetarie;
import com.example.tema2_ps_final.model.viewmodel.CofetarieViewModel;
import com.example.tema2_ps_final.view.CofetarieView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CofetarieController {
    private final CofetarieViewModel viewModel;
    private final CofetarieView view;
    private final Parent viewRoot;

    public CofetarieController() {
        this.viewModel = new CofetarieViewModel();
        Parent loadedView;
        CofetarieView loadedController;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tema2_ps_final/ConfectionaryView.fxml"));
            loadedView = loader.load();
            loadedController = loader.getController();
        } catch (IOException e) {
            throw new RuntimeException("Nu s-a putut încărca CofetarieView.fxml", e);
        }

        this.viewRoot = loadedView;
        this.view = loadedController;

        this.viewModel.addObserver(view);
        this.view.setTableItems1(viewModel.getCofetarii());

        setupEventHandlers();
    }

    public Parent getViewRoot() {
        return this.viewRoot;
    }

    private void setupEventHandlers() {
        view.getAddButton().setOnAction(e -> handleAdaugaCofetarie());

        view.getUpdateButton().setOnAction(e -> {
            if (view.getSelectedCofetarie() != null) {
                view.getSelectedCofetarie().setAddress(view.getAdresaCofetarie());
                handleUpdateCofetarie(view.getSelectedCofetarie());
            } else {
                view.showMessage("Eroare", "Selectează o cofetărie pentru actualizare.");
            }
        });

        view.getDeleteButton().setOnAction(e -> {
            if (view.getSelectedCofetarie() != null) {
                handleStergereCofetarie(view.getSelectedCofetarie().getId());
            } else {
                view.showMessage("Eroare", "Selectează o cofetărie pentru ștergere.");
            }
        });

    }

    // ACEASTA TREBUIE MUTATĂ ÎN AFARA LUI setupEventHandlers
    private void handleAdaugaCofetarie() {
        try {
            String adresa = view.getAdresaCofetarie();
            Cofetarie nouaCofetarie = new Cofetarie(adresa);
            viewModel.adaugaCofetarie(nouaCofetarie);

            view.showMessage("Succes", "Cofetăria a fost adăugată cu succes!");
        } catch (Exception e) {
            view.showMessage("Eroare", "Date introduse incorect: " + e.getMessage());
        }
    }

    public void handleStergereCofetarie(int id) {
        viewModel.stergeCofetarie(id);
        view.showMessage("Șters", "Cofetăria a fost ștearsă.");
    }

    public void handleUpdateCofetarie(Cofetarie cofetarie) {
        viewModel.actualizeazaCofetarie(cofetarie);
        view.showMessage("Actualizat", "Cofetăria a fost actualizată.");
    }
}