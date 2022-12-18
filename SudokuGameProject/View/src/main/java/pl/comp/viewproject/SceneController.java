package pl.comp.viewproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import pl.sudoku.Dao;
import pl.sudoku.SudokuBoard;
import pl.sudoku.SudokuBoardDaoFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class SceneController {


    private static Level level;
    @FXML
    private FileChooser fileChooser;
    private String language;
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private Dao<SudokuBoard> fileSudokuBoardDao;
    private static SudokuBoard sudokuBoardFromFile;

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
    public void switchToPolish(ActionEvent event) throws IOException {
        language = "pl";
        Locale.setDefault(new Locale(language));
        bundle = ResourceBundle.getBundle("pl.comp.viewproject/Language");
        StageSetup.buildStage("whichLevel.fxml", bundle);
    }

    @FXML
    public void switchToEnglish(ActionEvent event) throws IOException {
        language = "en";
        Locale.setDefault(new Locale(language));
        bundle = ResourceBundle.getBundle("pl.comp.viewproject/Language");
        StageSetup.buildStage("whichLevel.fxml", bundle);
    }

    @FXML
    public void showAuthors(ActionEvent event) throws IOException {
        Developers developers = new Developers();
        Stage stage = new Stage();
        VBox vbox = new VBox();
        TextField firstDeveloper = new TextField(developers.getObject("Kuba").toString());
        TextField secondDeveloper = new TextField(developers.getObject("Wojtek").toString());


        vbox.getChildren().add(firstDeveloper);
        vbox.getChildren().add(secondDeveloper);
        Scene stageScene = new Scene(vbox, 400, 300);
        stage.setTitle(bundle.getString("popOutAuthors"));
        stage.setScene(stageScene);
        stage.show();
    }

    @FXML
    public void onActionReadFromFile(ActionEvent actionEvent) {
        String filename;
        fileChooser = new FileChooser();
        try {
            filename = fileChooser.showOpenDialog(StageSetup.getStage()).getAbsolutePath();
            fileSudokuBoardDao = factory.getFileDao(filename);
            sudokuBoardFromFile = fileSudokuBoardDao.read();
            StageSetup.buildStage("game.fxml", bundle);
        } catch (NullPointerException | IOException e) {
            throw new RuntimeException(e); //na raazie tak
        }
    }
    public static SudokuBoard getSudokuBoardFromFile() {
        return sudokuBoardFromFile;
    }


}
