package com.example.tema2_ps_final.model.viewmodel;

import com.example.tema2_ps_final.model.Prajitura;
import com.example.tema2_ps_final.model.observer.Observable;
import com.example.tema2_ps_final.model.repository.CakeRepository;
import com.example.tema2_ps_final.model.repository.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

// notifica view urile cand se modifica ceva in date
public class PrajituraViewModel extends Observable {
    private final ObservableList<Prajitura> prajituri;
   // private final Repository<Prajitura> prajituraRepository;
    private final CakeRepository prajituraRepository;

    public PrajituraViewModel() {
        this.prajituraRepository = new CakeRepository();
        this.prajituri = FXCollections.observableArrayList();
        loadData();
    }

    public ObservableList<Prajitura> getPrajituri() {
        return prajituri;
    }

    public void loadData() {
        List<Prajitura> lista = prajituraRepository.getTableContent1();
        prajituri.setAll(lista);
        notifyObservers();
    }

    public void adaugaPrajitura(Prajitura prajitura) {
        Prajitura rezultat = prajituraRepository.insertCake(prajitura);
        if (rezultat != null) {
            prajituri.add(rezultat);
            notifyObservers();
            loadData();
        }
    }

    public void stergePrajitura(int id) {
        int rezultat = prajituraRepository.delete(id);
        if (rezultat > 0) {
            prajituri.removeIf(p -> p.getId() == id);
            notifyObservers();
        }
    }

    public void actualizeazaPrajitura(Prajitura prajitura) {
        boolean succes = prajituraRepository.update(prajitura);
        if (succes) {
            loadData(); // Reîncarcă lista completă
        }
    }

    public String cautaPrajituraDupaNume(String nume) {
        return prajituraRepository.findCakeByName(nume);
    }

    public List<Prajitura> prajituriDisponibile(int cofetarieId) {
        return prajituraRepository.findCakesByAvailability(cofetarieId);
    }

    public List<Prajitura> prajituriExpirateSauStocZero(int cofetarieId) {
        return prajituraRepository.findExpiredOrOutOfStockCakes(cofetarieId, LocalDate.now());
    }

    public List<Prajitura> prajituriNevalabile(int cofetarieId) {
        return prajituraRepository.findCakesByValidity(cofetarieId, LocalDate.now());
    }
}