package pl.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sudoku.BacktrackingSudokuSolver;
import pl.sudoku.Dao;
import pl.sudoku.SudokuBoard;
import pl.sudoku.SudokuBoardDaoFactory;

import static org.junit.jupiter.api.Assertions.*;

class FileSudokuBoardDaoTest {

    private SudokuBoard sudokuBoard;
    private SudokuBoard sudokuBoard1;
    private SudokuBoardDaoFactory factory;
    private Dao<SudokuBoard> fileSudokuBoardDao;

    @BeforeEach
    public void setUp() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();
        factory = new SudokuBoardDaoFactory();

    }

    @Test
    void writeReadTest() {
        fileSudokuBoardDao = factory.getFileDao("new_file.txt");
        fileSudokuBoardDao.write(sudokuBoard);

        sudokuBoard1 = fileSudokuBoardDao.read();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(sudokuBoard.get(i, j), sudokuBoard1.get(i, j));
            }
        }
        assertEquals(sudokuBoard.hashCode(), sudokuBoard1.hashCode());
        assertEquals(sudokuBoard, sudokuBoard1);

        assertTrue(sudokuBoard1.equals(sudokuBoard));
    }

    @Test
    void writeExceptionTest() {
        fileSudokuBoardDao = factory.getFileDao("??????????.txt");
        assertThrows(RuntimeException.class, () -> {fileSudokuBoardDao.write(sudokuBoard);});
    }
    @Test
    void readExceptionTest() {
        fileSudokuBoardDao = factory.getFileDao("empty.txt");
        assertThrows(RuntimeException.class, () -> {fileSudokuBoardDao.read();});
    }

    @Test
    void closeTest() throws Exception {
        fileSudokuBoardDao = factory.getFileDao("file.txt");
        fileSudokuBoardDao.close();
    }
}