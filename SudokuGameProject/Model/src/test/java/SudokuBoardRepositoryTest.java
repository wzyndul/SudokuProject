import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardRepositoryTest {
    @Test
    void RepositoryTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();
        SudokuBoardRepository sudokuBoardRepository = new SudokuBoardRepository(sudokuBoard);
        SudokuBoard clonedBoard = sudokuBoardRepository.createInstance();
        assertNotNull(clonedBoard);
        assertNotSame(clonedBoard, sudokuBoard);
        assertTrue(clonedBoard.equals(sudokuBoard));
    }
}