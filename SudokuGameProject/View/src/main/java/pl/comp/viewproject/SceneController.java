package pl.comp.viewproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToSceneEasy(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("easy.fxml"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
    }
    public void switchToSceneMedium(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("medium.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSceneHard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScenewhichLevel(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("whichLevel.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
