package pl.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sudoku.exception.DaoException;
import pl.sudoku.exception.WriteReadException;


import static org.junit.jupiter.api.Assertions.*;

public class FileSudokuBoardDaoTest {

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
    public void writeReadTest() throws DaoException {
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
    public void writeExceptionTest() {
        fileSudokuBoardDao = factory.getFileDao("??????????.txt");
        assertThrows(WriteReadException.class, () -> {
            fileSudokuBoardDao.write(sudokuBoard);
        });
    }

    @Test
    public void readExceptionTest() {
        fileSudokuBoardDao = factory.getFileDao("empty.txt");
        assertThrows(WriteReadException.class, () -> {
            fileSudokuBoardDao.read();
        });
    }

    @Test
    public void closeTest() throws Exception {  //co z tym????????????????????
        fileSudokuBoardDao = factory.getFileDao("file.txt");
        fileSudokuBoardDao.close();
    }
}