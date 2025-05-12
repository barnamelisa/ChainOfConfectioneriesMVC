package com.example.tema2_ps_final.view;

import com.example.tema2_ps_final.model.Prajitura;
import com.example.tema2_ps_final.model.observer.Observable;
import com.example.tema2_ps_final.model.observer.Observer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

import java.text.DecimalFormat;
import java.time.LocalDate;

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

    // Replace ComboBox with language buttons
    @FXML private Button englishButton;
    @FXML private Button frenchButton;
    @FXML private Button romanianButton;

    @FXML
    public void initialize() {

        // Button Actions
        englishButton.setOnAction(event -> handleLanguageChange("English"));
        frenchButton.setOnAction(event -> handleLanguageChange("Français"));
        romanianButton.setOnAction(event -> handleLanguageChange("Română"));

        // Table column bindings
        numePrajituraColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNume_prajitura()));
        descriereColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescriere()));
        cofetarieIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCofetarie_id()).asObject());
        pretColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPret().doubleValue()));
        dataExpirareColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getData_expirare().toString()));
        dataProductieColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getData_productie().toString()));
        imagineColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getImagine()));

        pretColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : new DecimalFormat("#.##").format(item));
            }
        });
    }

    // Handle language change based on the button clicked
    private void handleLanguageChange(String selectedLang) {
        // Logic to update the language based on the selected button
        System.out.println("Language selected: " + selectedLang);
        // Here, you can implement the logic to update your UI according to the selected language
    }

    public void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public String getNumePrajitura() {
        return numePrajituraField.getText();
    }

    public String getDescriere() {
        return descriereField.getText();
    }

    public String getPret() {
        return pretField.getText();
    }

    public String getCofetarieId() {
        return cofetarieIdField.getText();
    }

    public LocalDate getDataExpirare() {
        return dataExpirareField.getValue();
    }

    public LocalDate getDataProductie() {
        return dataProductieField.getValue();
    }

    public String getImagine() {
        return imagineField.getText();
    }

    public String getSearchName() {
        return searchField.getText();
    }

    public int getAvailabilityCofetarieId() {
        return Integer.parseInt(availabilityField.getText());
    }

    public int getValidityCofetarieId() {
        return Integer.parseInt(validityField.getText());
    }

    public void setTableItems(ObservableList<Prajitura> list) {
        cakeTable.setItems(list);
    }

    public Prajitura getSelectedCake() {
        return cakeTable.getSelectionModel().getSelectedItem();
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

    public Button getClearFieldsButton() {
        return clearFieldsButton;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public Button getAvailabilityButton() {
        return availabilityButton;
    }

    public Button getValidityButton() {
        return validityButton;
    }

    @Override
    public void update(Observable observable) {
        cakeTable.setItems(((com.example.tema2_ps_final.model.viewmodel.PrajituraViewModel) observable).getPrajituri());
        cakeTable.refresh();
    }

    public TableColumn<Prajitura, String> getNumePrajituraColumn() {
        return numePrajituraColumn;
    }

    public TableColumn<Prajitura, String> getDescriereColumn() {
        return descriereColumn;
    }

    public TableColumn<Prajitura, Integer> getCofetarieIdColumn() {
        return cofetarieIdColumn;
    }

    public TableColumn<Prajitura, Double> getPretColumn() {
        return pretColumn;
    }

    public TableColumn<Prajitura, String> getDataExpirareColumn() {
        return dataExpirareColumn;
    }

    public TableColumn<Prajitura, String> getDataProductieColumn() {
        return dataProductieColumn;
    }

    public TableColumn<Prajitura, String> getImagineColumn() {
        return imagineColumn;
    }

    public Label getMessageLabel() {
        return messageLabel;
    }

    public ButtonBase getEnglishButton() {
        return englishButton;
    }

    public ButtonBase getFrenchButton() {
        return frenchButton;
    }

    public ButtonBase getRomanianButton() {
        return romanianButton;
    }
}
