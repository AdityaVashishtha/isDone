/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author aditya
 */
public class ItemController implements Initializable {

    @FXML
    private HBox item;

    @FXML
    private CheckBox checkBox;

    @FXML
    private Label taskText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupListeners();
    }

    private void setupListeners() {
        item.setOnMouseClicked(value -> {
            checkBox.setSelected(!checkBox.selectedProperty().get());
        });
        checkBox.selectedProperty().addListener(
                (observable, oldValue, newValue) -> taskText.getChildrenUnmodifiable().get(0).setStyle("-fx-strikethrough: " + newValue));
    }

    public void setTodo(String text) {
        this.taskText.setText(text);
    }

}
