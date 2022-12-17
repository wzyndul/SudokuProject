package pl.comp.viewproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private static Level level;
    private static String input;
    private String language;

    public static String getInput() {
        return input;
    }
    private ResourceBundle bundle = ResourceBundle.getBundle("pl.comp.viewproject/Language");


    @FXML
    private TextField myTextField = new TextField();

    public static Level getLevel() {
        return level;
    }

    @FXML
    public void switchToEasy(ActionEvent event) throws IOException {
        level = Level.EASY;
        StageSetup.buildStage("game.fxml", bundle);
    }
    @FXML
    public void switchToMedium(ActionEvent event) throws IOException {
        level = Level.MEDIUM;
        StageSetup.buildStage("game.fxml", bundle);
    }

    @FXML
    public void switchToHard(ActionEvent event) throws IOException {
        level = Level.HARD;
        StageSetup.buildStage("game.fxml", bundle);
    }
    @FXML
    public void LoadSudoku(ActionEvent event) throws IOException {
        input = myTextField.getText();
        StageSetup.buildStage("game.fxml", bundle);

    }
    @FXML
    public void switchToPolish(ActionEvent event) throws IOException{
        language = "pl";
        Locale.setDefault(new Locale(language));
        StageSetup.buildStage("whichLevel.fxml", bundle);
    }
    @FXML
    public void switchToEnglish(ActionEvent event) throws IOException{
        language = "en";
        Locale.setDefault(new Locale(language));
        StageSetup.buildStage("whichLevel.fxml", bundle);
    }

}
