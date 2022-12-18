package pl.comp.viewproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.sudoku.exception.GuiException;

import java.io.IOException;
import java.util.ResourceBundle;


public class StageSetup {
    private static Stage stage;

    private static void setStage(Stage stage) {
        StageSetup.stage = stage;
    }

    private static Parent loadFxml(String fxml, ResourceBundle bundle) throws GuiException {
        try {
            return new FXMLLoader(StageSetup.class.getResource(fxml), bundle).load();
        } catch (IOException e) {
            throw new GuiException(e);
        }
    }

    public static void buildStage(String filePath, ResourceBundle bundle) throws GuiException {
        stage.setScene(new Scene(loadFxml(filePath, bundle)));
        stage.sizeToScene();
        stage.show();
    }

    public static void buildStage(Stage stage, String filePath, ResourceBundle bundle) throws GuiException {
        setStage(stage);
        stage.setScene(new Scene(loadFxml(filePath, bundle)));
        stage.sizeToScene();
        stage.show();
    }
    public static Stage getStage() {
        return stage;
    }

}
