package com.example.tema2_ps_final.view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.example.tema2_ps_final.model.Prajitura;
import com.example.tema2_ps_final.model.observer.Observable;
import com.example.tema2_ps_final.model.observer.Observer;

import java.text.DecimalFormat;
import java.time.LocalDate;

// nu stie nimic despre Controller, ci primeste update prin Observer
public class PrajituraView implements Observer {

    @FXML private TextField numePrajituraField;
    @FXML private TextField descriereField;
    @FXML private TextField cofetarieIdField;
    @FXML private TextField pretField;
    @FXML private DatePicker dataExpirareField;
    @FXML private DatePicker dataProductieField;
    @FXML private TextField imagineField;
    @FXML private TextField searchField;
    @FXML private TextField availabilityField;
    @FXML private TextField validityField;

    @FXML private TableView<Prajitura> cakeTable;
    @FXML private TableColumn<Prajitura, String> numePrajituraColumn;
    @FXML private TableColumn<Prajitura, String> descriereColumn;
    @FXML private TableColumn<Prajitura, Integer> cofetarieIdColumn;
    @FXML private TableColumn<Prajitura, Double> pretColumn;
    @FXML private TableColumn<Prajitura, String> dataExpirareColumn;
    @FXML private TableColumn<Prajitura, String> dataProductieColumn;
    @FXML private TableColumn<Prajitura, String> imagineColumn;

    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private Button clearFieldsButton;
    @FXML private Button searchButton;
    @FXML private Button availabilityButton;
    @FXML private Button validityButton;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        numePrajituraColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNume_prajitura()));
        descriereColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescriere()));
        cofetarieIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCofetarie_id()).asObject());
        pretColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getPret().doubleValue()));
        pretColumn.setCellFactory(column -> new TableCell<Prajitura, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    DecimalFormat df = new DecimalFormat("#.##");
                    setText(df.format(item));
                }
            }
        });
        dataExpirareColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getData_expirare().toString()));
        dataProductieColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getData_productie().toString()));
        imagineColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getImagine()));
    }

    public void setAddButtonAction(Runnable action) {
        addButton.setOnAction(e -> action.run());
    }

    public void setUpdateButtonAction(Runnable action) {
        updateButton.setOnAction(e -> action.run());
    }

    public void setDeleteButtonAction(Runnable action) {
        deleteButton.setOnAction(e -> action.run());
    }

    public void setClearFieldsButtonAction(Runnable action) {
        clearFieldsButton.setOnAction(e -> action.run());
    }

    public void setSearchButtonAction(Runnable action) {
        searchButton.setOnAction(e -> action.run());
    }

    public void setAvailabilityButtonAction(Runnable action) {
        availabilityButton.setOnAction(e -> action.run());
    }

    public void setValidityButtonAction(Runnable action) {
        validityButton.setOnAction(e -> action.run());
    }

    public void showMessage(String title, String mesaj) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mesaj);
        alert.showAndWait();
    }

    public String getNumePrajitura() { return numePrajituraField.getText(); }
    public String getDescriere() { return descriereField.getText(); }
    public String getPret() { return pretField.getText(); }
    public String getCofetarieId() { return cofetarieIdField.getText(); }
    public LocalDate getDataExpirare() { return dataExpirareField.getValue(); }
    public LocalDate getDataProductie() { return dataProductieField.getValue(); }
    public String getImagine() { return imagineField.getText(); }
    public String getSearchName() {
        return searchField.getText();
    }

    public int getAvailabilityCofetarieId() {
        return Integer.parseInt(availabilityField.getText());
    }

    public int getValidityCofetarieId() {
        return Integer.parseInt(validityField.getText());
    }

    public void setTableItems(ObservableList<Prajitura> lista) {
        cakeTable.setItems(lista);
    }
    public Prajitura getSelectedCake() {
        return cakeTable.getSelectionModel().getSelectedItem();
    }

    @Override
    public void update(Observable observable) {
        cakeTable.refresh();
        cakeTable.setItems(((com.example.tema2_ps_final.model.viewmodel.PrajituraViewModel) observable).getPrajituri());
    }
}

