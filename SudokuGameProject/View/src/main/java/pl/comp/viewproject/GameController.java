package pl.comp.viewproject;

import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pl.sudoku.BacktrackingSudokuSolver;
import pl.sudoku.SudokuBoard;

public class GameController {
    @FXML
    private GridPane sudokuGrid;


    private final SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
    private deletingFields deletingFields = new deletingFields();

    public void initialize() {
        sudokuBoard.solveGame();
        deletingFields.removeFields(SceneController.getLevel(), sudokuBoard);
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
                }
                textField.setFont(Font.font(25));
                sudokuGrid.add(textField, i, j);
            }
        }
    }
    public void switchToScenewhichLevel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("whichLevel.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
