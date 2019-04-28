package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UiController implements Initializable {

    double x = 0;
    double y = 0;

    @FXML
    private AnchorPane root;

    @FXML
    private VBox taskContainer;

    @FXML
    private Button showInputButton;

    @FXML
    private Label exitLabel;

    @FXML
    private TextField inputTodo;

    @FXML
    private Button cancelButton;

    @FXML
    private Button addTaskButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            inputTodo.setVisible(false);
            cancelButton.setVisible(false);
            addTaskButton.setVisible(false);
            FXMLLoader loader = new FXMLLoader(new File("./src/fxmls/Item.fxml").toURI().toURL());
            taskContainer.getChildren().add(loader.load());
            setupListeners();
        } catch (IOException ex) {
            Logger.getLogger(UiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void exitWindow(MouseEvent event) {
        Stage stage = (Stage) exitLabel.getScene().getWindow();
        stage.close();
        Platform.exit();
        System.exit(0);
    }

    private void setupListeners() {
        root.setOnMousePressed(this::updateWindowPosition);
        root.setOnMouseDragged(this::updateWindowPosition);
        showInputButton.setOnMouseClicked(this::toggleAddTodoInput);
        cancelButton.setOnMouseClicked(this::toggleAddTodoInput);
    }

    private void updateWindowPosition(MouseEvent value) {
        if (value.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
            x = value.getSceneX();
            y = value.getSceneY();
        } else if (value.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setX(value.getScreenX() - x);
            stage.setY(value.getScreenY() - y);
        }
    }

    private void toggleAddTodoInput(Event event) {
        if (inputTodo.isVisible()) {
            inputTodo.setVisible(false);
            cancelButton.setVisible(false);
            addTaskButton.setVisible(false);
        } else {
            inputTodo.setVisible(true);
            cancelButton.setVisible(true);
            addTaskButton.setVisible(true);
        }
    }

}
