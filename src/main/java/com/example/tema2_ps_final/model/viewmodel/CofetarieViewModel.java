package com.example.tema2_ps_final.model.viewmodel;

import com.example.tema2_ps_final.model.Cofetarie;
import com.example.tema2_ps_final.model.observer.Observable;
import com.example.tema2_ps_final.model.repository.CofetarieRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class CofetarieViewModel extends Observable{
        private final ObservableList<Cofetarie> cofetarii;
        private final CofetarieRepository cofetarieRepository;

        public CofetarieViewModel() {
            this.cofetarieRepository = new CofetarieRepository();
            this.cofetarii = FXCollections.observableArrayList();
            loadData();
        }

        public ObservableList<Cofetarie> getCofetarii() {
            return cofetarii;
        }

        public void loadData() {
            List<Cofetarie> lista = cofetarieRepository.getTableContent2();
            cofetarii.setAll(lista);
            notifyObservers();
        }

        public void adaugaCofetarie(Cofetarie cofetarie) {
            Cofetarie rezultat = cofetarieRepository.insert(cofetarie);
            if (rezultat != null) {
                cofetarii.add(rezultat);
                notifyObservers();
                loadData();
            }
        }

        public void stergeCofetarie(int id) {
            int rezultat = cofetarieRepository.delete(id);
            if (rezultat > 0) {
                cofetarii.removeIf(c -> c.getId() == id);
                notifyObservers();
            }
        }

        public void actualizeazaCofetarie(Cofetarie cofetarie) {
            boolean succes = cofetarieRepository.update(cofetarie);
            if (succes) {
                notifyObservers();
                loadData();
            }
        }
}
