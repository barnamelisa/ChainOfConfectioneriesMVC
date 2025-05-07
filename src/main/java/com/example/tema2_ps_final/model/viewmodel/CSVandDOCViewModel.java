package com.example.tema2_ps_final.model.viewmodel;

import com.example.tema2_ps_final.model.Prajitura;
import com.example.tema2_ps_final.model.observer.Observable;
import com.example.tema2_ps_final.model.repository.CakeRepository;

import java.time.LocalDate;
import java.util.List;

public class CSVandDOCViewModel extends Observable {
    private final CakeRepository repository;

    public CSVandDOCViewModel() {
        this.repository = CakeRepository.getInstance();
    }

    public void exportCSV(int selectedCofetarieId) {
        if (!repository.checkCofetarieExists(selectedCofetarieId)) {
            notifyObservers();
            return;
        }

        List<Prajitura> prajituri = repository.findExpiredOrOutOfStockCakes(selectedCofetarieId, LocalDate.now());
        repository.createCSV(prajituri);
        notifyObservers();
    }
}
