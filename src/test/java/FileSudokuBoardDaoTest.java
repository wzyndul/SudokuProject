import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        factory = new SudokuBoardDaoFactory();

    }

    @Test
    void writeReadTest() {
        fileSudokuBoardDao = factory.getFileDao("new_file");
        fileSudokuBoardDao.write(sudokuBoard);

        sudokuBoard1 = fileSudokuBoardDao.read();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(sudokuBoard.get(i, j), sudokuBoard1.get(i, j));
            }
        }
    }

    @Test
    void writeExceptionTest() {
        fileSudokuBoardDao = factory.getFileDao("??????????");
        assertThrows(RuntimeException.class, () -> {fileSudokuBoardDao.write(sudokuBoard);});
    }
    @Test
    void readExceptionTest() {
        fileSudokuBoardDao = factory.getFileDao("empty");
       assertThrows(RuntimeException.class, () -> {fileSudokuBoardDao.read();});
    }

    @Test
    void closeTest() throws Exception {
        fileSudokuBoardDao = factory.getFileDao("file");
        fileSudokuBoardDao.close();
    }
}