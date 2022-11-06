import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    public void getSetMethodsTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.set(0, 0, 9);
        assertEquals(sudokuBoard.get(0, 0), 9);
    }


    @Test   // TEN TEST MOZNA ZMIENIC ZEBY SZYBCIEJ Z NIEGO WYCHODZIC
    void differentNumbers() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();
        int[][] table = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                table[i][j] = sudokuBoard.get(i, j);
            }
        }
        sudokuBoard.solveGame();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard.get(i, j) == table[i][j]) {
                    return;
                }
            }
        }
    }

   /* @Test          //stary test checkboarda
    void chceckBoardTest() {
        SudokuBoard sudoku = new SudokuBoard();
        assertFalse(sudoku.checkBoard());

        sudoku.set(1, 0, 1);
        sudoku.set(0, 0, 1);
        assertFalse(sudoku.checkBoard());

        sudoku.set(1, 0, 0);
        sudoku.set(1, 1, 1);
        assertFalse(sudoku.checkBoard());

        sudoku.solveGame();
        assertTrue(sudoku.checkBoard());
    }*/


}