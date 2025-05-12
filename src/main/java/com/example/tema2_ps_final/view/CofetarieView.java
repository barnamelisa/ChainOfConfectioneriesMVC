package com.example.tema2_ps_final.view;

import com.example.tema2_ps_final.model.Cofetarie;
import com.example.tema2_ps_final.model.observer.Observable;
import com.example.tema2_ps_final.model.observer.Observer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class CofetarieView implements Observer {

    @FXML private TextField adresaField;
    @FXML private TextField searchAddressField;

    @FXML private TableView<Cofetarie> cofetarieTable;
    @FXML private TableColumn<Cofetarie, Integer> idColumn;
    @FXML private TableColumn<Cofetarie, String> adresaColumn;

    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private Button searchButton;
    @FXML private Button clearFieldsButton;
    @FXML private Label messageLabel;


    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        adresaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
    }

    public void showMessage(String title, String mesaj) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mesaj);
        alert.showAndWait();
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public Button getClearFieldsButton() {
        return clearFieldsButton;
    }
    public String getAdresaCofetarie() {
        return adresaField.getText();
    }

    public String getSearchAddress() {
        return searchAddressField.getText();
    }

    public void setTableItems1(ObservableList<Cofetarie> lista) {
        cofetarieTable.setItems(lista);
    }

    public Cofetarie getSelectedCofetarie() {
        return cofetarieTable.getSelectionModel().getSelectedItem();
    }

    @Override
    public void update(Observable observable) {
        cofetarieTable.refresh();
        cofetarieTable.setItems(((com.example.tema2_ps_final.model.viewmodel.CofetarieViewModel) observable).getCofetarii());
    }
}
