package pl.comp.viewproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.sudoku.Dao;
import pl.sudoku.JdbcSudokuBoardDao;
import pl.sudoku.SudokuBoard;
import pl.sudoku.SudokuBoardDaoFactory;
import pl.sudoku.exception.GuiException;
import pl.sudoku.exception.JdbcException;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class SceneController {


    private static Level level;
    private static boolean isFromOutside = false;
    @FXML
    private FileChooser fileChooser;
    private String language;
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private Dao<SudokuBoard> fileSudokuBoardDao;
    private String name;
    private DisplayingMessage displayingMessage = new DisplayingMessage();
    private static SudokuBoard sudokuBoardFromFile;
    @FXML
    private TextField sudokuDB;

    private ResourceBundle bundle = ResourceBundle.getBundle("Language");

    public SceneController() {
    }


    public static Level getLevel() {
        return level;
    }

    @FXML
    public void switchToEasy(ActionEvent event) throws GuiException {
        setFromOutside(false);
        level = Level.EASY;
        StageSetup.buildStage("game.fxml", bundle);
    }

    @FXML
    public void switchToMedium(ActionEvent event) throws GuiException {
        setFromOutside(false);
        level = Level.MEDIUM;
        StageSetup.buildStage("game.fxml", bundle);
    }

    @FXML
    public void switchToHard(ActionEvent event) throws GuiException {
        setFromOutside(false);
        level = Level.HARD;
        StageSetup.buildStage("game.fxml", bundle);
    }


    @FXML
    public void switchToPolish(ActionEvent event) throws GuiException {
        language = "pl";
        Locale.setDefault(new Locale(language));
        bundle = ResourceBundle.getBundle("Language");
        StageSetup.buildStage("whichLevel.fxml", bundle);
    }

    @FXML
    public void switchToEnglish(ActionEvent event) throws GuiException {
        language = "en";
        Locale.setDefault(new Locale(language));
        bundle = ResourceBundle.getBundle("Language");
        StageSetup.buildStage("whichLevel.fxml", bundle);
    }

    @FXML
    public void showAuthors(ActionEvent event) {
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
    public void onActionDisplayBoards(ActionEvent actionEvent) throws GuiException {
        Stage stage = new Stage();
        VBox vbox = new VBox();
        try (JdbcSudokuBoardDao jdbcSudokuBoardDao = (JdbcSudokuBoardDao) factory.getDatabseDao()) {
            for (String board : jdbcSudokuBoardDao.getBoardsNames()) {
                TextField textField = new TextField(board);
                vbox.getChildren().add(textField);
            }
            Scene stageScene = new Scene(vbox, 500, 800);
            stage.setTitle(bundle.getString("namesOfBoards"));
            stage.setScene(stageScene);
            stage.show();
        } catch (Exception e) {
            throw new GuiException(e);
        }
    }

    @FXML
    public void onActionReadFromFile(ActionEvent actionEvent) throws GuiException {
        setFromOutside(true);
        String filename;
        fileChooser = new FileChooser();
        try {
            filename = fileChooser.showOpenDialog(StageSetup.getStage()).getAbsolutePath();
            fileSudokuBoardDao = factory.getFileDao(filename);
            sudokuBoardFromFile = fileSudokuBoardDao.read();
            StageSetup.buildStage("game.fxml", bundle);
        } catch (NullPointerException | IOException e) {
            throw new GuiException(e);
        }
    }

    public static SudokuBoard getSudokuBoardFromFile() {
        return sudokuBoardFromFile;
    }

    @FXML
    public void onActionReadFromDB(ActionEvent actionEvent) throws GuiException {
        setFromOutside(true);
        try (JdbcSudokuBoardDao jdbcSudokuBoardDao = (JdbcSudokuBoardDao) factory.getDatabseDao()) {
            name = sudokuDB.getText();
            if (jdbcSudokuBoardDao.getBoardsNames().contains(name)) {
                jdbcSudokuBoardDao.setName(name);
                sudokuBoardFromFile = jdbcSudokuBoardDao.read();
                StageSetup.buildStage("game.fxml", bundle);
            } else {
                displayingMessage.showMassage(bundle.getString("boardDoesntExist"));
                throw new JdbcException(bundle.getString("boardDoesntExist"));
            }
        } catch (Exception e) {
            throw new GuiException(e);
        }

    }

    public static boolean isFromOutside() {
        return isFromOutside;
    }

    public static void setFromOutside(boolean fromOutside) {
        isFromOutside = fromOutside;
    }
}
