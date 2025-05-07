package com.example.tema2_ps_final.view;

import com.example.tema2_ps_final.model.Cofetarie;
import com.example.tema2_ps_final.model.observer.Observable;
import com.example.tema2_ps_final.model.observer.Observer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    public void setAddButtonAction(Runnable action) {
        addButton.setOnAction(e -> action.run());
    }

    public void setUpdateButtonAction(Runnable action) {
        updateButton.setOnAction(e -> action.run());
    }

    public void setDeleteButtonAction(Runnable action) {
        deleteButton.setOnAction(e -> action.run());
    }

    public void setSearchButtonAction(Runnable action) {
        searchButton.setOnAction(e -> action.run());
    }

    public void setClearFieldsButtonAction(Runnable action) {
        clearFieldsButton.setOnAction(e -> action.run());
    }

    public void showMessage(String title, String mesaj) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mesaj);
        alert.showAndWait();
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
