package pl.comp.viewproject;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.sudoku.exception.GuiException;
import java.util.ResourceBundle;


public class SudokuApplication extends Application {
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");

    @Override
    public void start(Stage stage) throws GuiException {
        StageSetup.buildStage(stage, "whichLevel.fxml", bundle);
    }

    public static void main(String[] args) {
        launch();
    }
}