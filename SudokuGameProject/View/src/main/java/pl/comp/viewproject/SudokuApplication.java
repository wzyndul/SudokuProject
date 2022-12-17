package pl.comp.viewproject;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class SudokuApplication extends Application {
    private ResourceBundle bundle = ResourceBundle.getBundle("pl.comp.viewproject/Language");
    @Override
    public void start(Stage stage) throws IOException {
        StageSetup.buildStage(stage, "whichLevel.fxml", bundle);
    }

    public static void main(String[] args) {
        launch();
    }
}