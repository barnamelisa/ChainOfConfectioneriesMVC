package com.example.tema2_ps_final.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    public PrajituraController() {
        this.viewModel = new PrajituraViewModel();
        Parent loadedView;
        PrajituraView loadedController;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tema2_ps_final/PrajituraView.fxml"));
            loadedView = loader.load();
            loadedController = loader.getController();
        } catch (IOException e) {
            throw new RuntimeException("Nu s-a putut încărca PrajituraView.fxml", e);
        }

        this.viewRoot = loadedView;
        this.view = loadedController;

        this.viewModel.addObserver(view);
        this.view.setTableItems(viewModel.getPrajituri());

        setupEventHandlers();
    }

    public Parent getViewRoot() {
        return this.viewRoot;
    }

    private void setupEventHandlers() {
        view.setAddButtonAction(this::handleAdaugaPrajitura);
        view.setUpdateButtonAction(() -> {
            if (view.getSelectedCake() != null) {
                handleUpdatePrajitura(view.getSelectedCake());
            } else {
                view.showMessage("Eroare", "Selectează o prăjitură pentru actualizare.");
            }
        });

        view.setDeleteButtonAction(() -> {
            if (view.getSelectedCake() != null) {
                handleStergerePrajitura(view.getSelectedCake().getId());
            } else {
                view.showMessage("Eroare", "Selectează o prăjitură pentru ștergere.");
            }
        });

        view.setSearchButtonAction(() -> handleCautaDupaNume(view.getSearchName()));
        view.setAvailabilityButtonAction(() -> handleDisponibilitate(view.getAvailabilityCofetarieId()));
        view.setValidityButtonAction(() -> handleNevalabile(view.getValidityCofetarieId()));

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

            view.showMessage("Succes", "Prăjitura a fost adăugată cu succes!");
        } catch (Exception e) {
            view.showMessage("Eroare", "Date introduse incorect: " + e.getMessage());
        }
    }

    public void handleStergerePrajitura(int id) {
        viewModel.stergePrajitura(id);
        view.showMessage("Șters", "Prăjitura a fost ștearsă.");
    }

    public void handleUpdatePrajitura(Prajitura prajitura) {
        viewModel.actualizeazaPrajitura(prajitura);
        view.showMessage("Actualizat", "Prăjitura a fost actualizată.");
    }

    public void handleCautaDupaNume(String nume) {
        String rezultat = viewModel.cautaPrajituraDupaNume(nume);
        view.showMessage("Căutare", rezultat.isEmpty() ? "Nu s-a găsit nicio prăjitură." : rezultat);
    }

    public void handleDisponibilitate(int idCofetarie) {
        var rezultate = viewModel.prajituriDisponibile(idCofetarie);
        view.setTableItems(FXCollections.observableArrayList(rezultate));
    }

    public void handleNevalabile(int idCofetarie) {
        var rezultate = viewModel.prajituriNevalabile(idCofetarie);
        view.setTableItems(FXCollections.observableArrayList(rezultate));
    }
}
