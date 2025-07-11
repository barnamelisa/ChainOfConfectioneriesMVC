package com.example.tema2_ps_final.model.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private final List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer obs) {
        observers.add(obs);
    }
    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }
    protected void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(this);
        }
    }
}
