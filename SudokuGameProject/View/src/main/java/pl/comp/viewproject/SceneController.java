package pl.comp.viewproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private static Level level;

    @FXML
    Button buttonEasy = new Button("Easy");

    @FXML
    Button buttonMedium = new Button("Medium");

    @FXML
    Button buttonHard = new Button("Hard");
    public static Level getLevel() { return  level;}





    public void switchToEasy(ActionEvent event) throws IOException {
        level = Level.EASY;
        root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage = (Stage) buttonEasy.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToMedium(ActionEvent event) throws IOException {
        level = Level.MEDIUM;
        root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage = (Stage) buttonMedium.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToHard(ActionEvent event) throws IOException {
        level = Level.HARD;
        root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage = (Stage) buttonHard.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
