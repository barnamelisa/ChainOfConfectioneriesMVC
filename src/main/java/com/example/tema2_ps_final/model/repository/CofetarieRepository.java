package com.example.tema2_ps_final.model.repository;

import com.example.tema2_ps_final.model.Cofetarie;

import java.io.Serializable;

public class CofetarieRepository extends Repository<Cofetarie> implements Serializable {
    private static CofetarieRepository instance;

    public CofetarieRepository() {
        super(Cofetarie.class);
    }
    public static CofetarieRepository getInstance() {
        if (instance == null) {
            synchronized (CakeRepository.class) {
                if (instance == null) {
                    instance = new CofetarieRepository();
                }
            }
        }
        return instance;
    }
}
