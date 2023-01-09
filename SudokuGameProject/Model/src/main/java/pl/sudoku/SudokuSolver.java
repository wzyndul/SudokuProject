package pl.sudoku;

import java.io.Serializable;
import pl.sudoku.exception.SudokuBoardException;


public interface SudokuSolver extends Serializable {
    void solve(SudokuBoard board) throws SudokuBoardException;


}
