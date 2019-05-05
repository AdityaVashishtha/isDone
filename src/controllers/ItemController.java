/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

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

    @FXML
    private HBox imageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupListeners();
    }

    private void setupListeners() {
        imageView.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            try {
                AnchorPane newRoot;
                FXMLLoader loader = new FXMLLoader(new File("./src/fxmls/Main.fxml").toURI().toURL());
                newRoot = loader.load();

                Scene scene = imageView.getScene();
                AnchorPane oldRoot = (AnchorPane) scene.getRoot();
                int sceneWidth = (int) oldRoot.getScene().getWidth();
                int sceneHeight = (int) oldRoot.getScene().getHeight();

                newRoot.setTranslateX(sceneWidth);

                StackPane pane = new StackPane(oldRoot, newRoot);
                pane.setPrefSize(sceneWidth, sceneHeight);

                scene.setRoot(pane);

                Timeline timeline = new Timeline();
                KeyValue keyValue = new KeyValue(newRoot.
                        translateXProperty(), 0,
                        Interpolator.EASE_BOTH);
                KeyFrame keyFrame = new KeyFrame(Duration.millis(100), keyValue);
                timeline.getKeyFrames().add(keyFrame);
                timeline.play();
            } catch (Exception ex) {
                Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        item.setOnMouseClicked(value -> checkBox.setSelected(!checkBox.selectedProperty().get()));
        checkBox.selectedProperty().addListener(
                (observable, oldValue, newValue) -> taskText.getChildrenUnmodifiable().get(0).setStyle("-fx-strikethrough: " + newValue));
    }

    public void setTodo(String text) {
        this.taskText.setText(text);
    }

}
