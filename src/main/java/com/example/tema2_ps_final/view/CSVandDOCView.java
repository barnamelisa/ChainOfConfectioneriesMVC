package com.example.tema2_ps_final.view;

import com.example.tema2_ps_final.model.Prajitura;
import com.example.tema2_ps_final.model.observer.Observable;
import com.example.tema2_ps_final.model.observer.Observer;
import com.example.tema2_ps_final.model.viewmodel.CSVandDOCViewModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class CSVandDOCView implements Observer {

    @FXML private TextField cofetarieIdField;  // Schimbăm ComboBox-ul cu un TextField
    @FXML private Button exportButton;
    @FXML private Label messageLabel;

    @FXML private TableColumn<Prajitura, Integer> cofetarieIdColumn;

        @FXML
        public void initialize() {
            if (cofetarieIdColumn != null) {
                cofetarieIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCofetarie_id()).asObject());
            } else {
                System.out.println("CofetarieIdColumn is null");
            }
        }


    public void setExportButtonAction(Runnable action) {
        exportButton.setOnAction(e -> action.run());
    }

    @Override
    public void update(Observable observable) {
    }

    public Integer getCofetarieId() {
        try {
            return Integer.parseInt(cofetarieIdField.getText());  // Încercăm să obținem ID-ul cofetăriei din TextField
        } catch (NumberFormatException e) {
            return null;  // Returnăm null dacă ID-ul nu este un număr valid
        }
    }
}