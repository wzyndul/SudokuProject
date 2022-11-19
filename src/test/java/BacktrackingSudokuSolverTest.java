import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSudokuSolverTest {

    @Test
    void solve() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();
        for (int i = 0; i < 9; i++) {
            int sumRow = 0;
            int sumCol = 0;
            for (int j = 0; j < 9; j++) {
                sumRow += sudokuBoard.get(i, j);
                sumCol += sudokuBoard.get(j, i);
            }
            assertEquals(45, sumRow);
            assertEquals(45, sumCol);
        }
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                int rowStart = row - row % 3;
                int colStart = col - col % 3;
                int sumBox = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        sumBox += sudokuBoard.get(i, j);
                    }
                }
                assertEquals(45, sumBox);
            }
        }

    }
    @Test
    void testToString() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}