/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author aditya
 */
public class ItemController implements Initializable {

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
        checkBox.selectedProperty().addListener(
                (observable, oldValue, newValue) -> taskText.getChildrenUnmodifiable().get(0).setStyle("-fx-strikethrough: " + newValue));
    }

}
