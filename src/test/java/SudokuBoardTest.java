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


    @Test
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

    @Test
    public void checkBoardTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();
        assertTrue(sudokuBoard.checkBoard());

        int temp = sudokuBoard.get(0, 1);
        sudokuBoard.set(0, 1, sudokuBoard.get(0, 0));
        assertFalse(sudokuBoard.checkBoard());
        sudokuBoard.set(0, 1, temp);

        temp = sudokuBoard.get(1, 0);
        sudokuBoard.set(1, 0, sudokuBoard.get(0, 0));
        assertFalse(sudokuBoard.checkBoard());
        sudokuBoard.set(1, 0, temp);

        sudokuBoard.set(1, 1, sudokuBoard.get(0, 0));
        assertFalse(sudokuBoard.checkBoard());
    }
}