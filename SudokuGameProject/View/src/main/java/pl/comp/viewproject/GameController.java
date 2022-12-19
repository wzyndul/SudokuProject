package pl.comp.viewproject;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import javafx.stage.FileChooser;
import pl.sudoku.*;
import pl.sudoku.exception.DaoException;
import pl.sudoku.exception.WriteReadException;

public class GameController {
    @FXML
    private GridPane sudokuGrid;

    private ResourceBundle bundle = ResourceBundle.getBundle("Language");
    private SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

    private deletingFields deletingFields = new deletingFields();
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private Dao<SudokuBoard> fileSudokuBoardDao;
    @FXML
    private FileChooser fileChooser;

    @FXML
    public void initialize() {
        if (SceneController.getSudokuBoardFromFile() == null) {
            sudokuBoard.solveGame();
            deletingFields.removeFields(SceneController.getLevel(), sudokuBoard);
        } else {
            sudokuBoard = SceneController.getSudokuBoardFromFile();
        }
        fillGridToPlay();
    }

    private void fillGridToPlay() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setPrefHeight(60);
                textField.setPrefWidth(60);
                if (sudokuBoard.get(i, j) != 0) {
                    textField.setDisable(true);
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                } else {
                    int finalJ = j;
                    int finalI = i;
                    textField.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue.length() == 1 && newValue.matches("[1-9]")) {
                            sudokuBoard.set(finalI, finalJ, Integer.parseInt(newValue));
                        } else {
                            textField.setText("");
                        }
                    });
                }
                textField.setFont(Font.font(25));
                sudokuGrid.add(textField, i, j);
            }
        }
    }


    public void switchToScenewhichLevel(ActionEvent event) throws IOException {
        StageSetup.buildStage("whichLevel.fxml", bundle);
    }

    public void onActionSaveToFile(ActionEvent actionEvent) throws DaoException {
        fileChooser = new FileChooser();
        File file;
        try {
            file = fileChooser.showSaveDialog(StageSetup.getStage());
            fileSudokuBoardDao = factory.getFileDao(file.getAbsolutePath());
            fileSudokuBoardDao.write(sudokuBoard);
        } catch (NullPointerException e) {
            throw new WriteReadException(e);
        }

    }
}
