package controllers;

import dao.Task;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UiController implements Initializable {

    double x = 0;
    double y = 0;

    double setSmallX = 0;
    double setSmallY = 0;

    private Task curreTask;

    private ObservableList<Task> tasks = FXCollections.observableArrayList();

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
        inputTodo.setVisible(false);
        cancelButton.setVisible(false);
        addTaskButton.setVisible(false);
        setupListeners();
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
        addTaskButton.setOnMouseClicked(this::addTask);
        inputTodo.setOnAction(this::addTask);
        taskContainer.getChildren().addListener(new ListChangeListener<Node>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Node> c) {
                c.getList().stream().forEach(child -> {
                    child.setOnMouseClicked(value -> {
                        if (value.getClickCount() >= 2) {
                            c.getList().remove(child);
                        }
                    });
                });
            }
        });
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

    private void addTask(Event event) {
        try {
            if (inputTodo.getText().length() > 0) {
                FXMLLoader loader = new FXMLLoader(new File("./src/fxmls/Item.fxml").toURI().toURL());
                HBox box = loader.load();
                ItemController controller = loader.getController();
                controller.setTodo(inputTodo.getText());
                taskContainer.getChildren().add(box);
                inputTodo.clear();
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(UiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UiController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
