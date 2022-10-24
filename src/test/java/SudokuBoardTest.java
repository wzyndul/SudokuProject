import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    public void getSetMethodsTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.set(0, 0, 9);
        assertEquals(sudokuBoard.get(0, 0), 9);
    }


    @Test
    void differentNumbers() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.solveGame();
        int[][] table = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                table[i][j] = sudokuBoard.get(i, j);
            }
        }
        sudokuBoard.solveGame();
        int notEqual = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard.get(i, j) != table[i][j]) {
                    notEqual++;
                }
            }
        }
        assertNotEquals(81, notEqual);
    }

   /* @Test
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