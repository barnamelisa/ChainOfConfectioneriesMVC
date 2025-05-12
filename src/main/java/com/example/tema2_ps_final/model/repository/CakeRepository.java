package com.example.tema2_ps_final.model.repository;

import com.example.tema2_ps_final.model.Prajitura;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CakeRepository extends Repository<Prajitura> implements Serializable {
    private static CakeRepository instance;

    public CakeRepository() {
        super(Prajitura.class);
    }

    public static CakeRepository getInstance() {
        if (instance == null) {
            synchronized (CakeRepository.class) {
                if (instance == null) {
                    instance = new CakeRepository();
                }
            }
        }
        return instance;
    }

}
