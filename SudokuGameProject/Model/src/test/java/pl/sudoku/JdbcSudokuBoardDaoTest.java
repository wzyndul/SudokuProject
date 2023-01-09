package pl.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sudoku.exception.JdbcException;
import pl.sudoku.exception.SudokuBoardException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcSudokuBoardDaoTest {
    private SudokuBoard sudokuBoard;
    private SudokuBoard sudokuBoard1;
    private SudokuBoardDaoFactory sudokuBoardDaoFactory;

    @BeforeEach
    public void setUp() throws SudokuBoardException {
        sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        sudokuBoard1.solveGame();
        sudokuBoardDaoFactory = new SudokuBoardDaoFactory();
    }

    @Test
    public void ReadWriteDataBaseTest() {
        try (JdbcSudokuBoardDao jdbcSudokuBoardDao = (JdbcSudokuBoardDao) sudokuBoardDaoFactory.getDatabseDao()) {
            jdbcSudokuBoardDao.setName("pierwsze_sudoku");
            jdbcSudokuBoardDao.write(sudokuBoard);
            SudokuBoard sudokuBoardRead = jdbcSudokuBoardDao.read();
            assertNotSame(sudokuBoard, sudokuBoardRead);
            assertEquals(sudokuBoard, sudokuBoardRead);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.walk(Paths.get("myDB"))
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void ReadNullExceptionTest() throws JdbcException {
        try (JdbcSudokuBoardDao jdbcSudokuBoardDao = (JdbcSudokuBoardDao) sudokuBoardDaoFactory.getDatabseDao()) {
            assertThrows(JdbcException.class, () -> jdbcSudokuBoardDao.read());
        } catch (Exception e) {
            throw new JdbcException(e);
        } finally {
            try {
                Files.walk(Paths.get("myDB"))
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (Exception e) {
                throw new JdbcException(e);
            }
        }
    }

    @Test
    public void WriteExceptionTest() throws JdbcException {
        try (JdbcSudokuBoardDao jdbcSudokuBoardDao = (JdbcSudokuBoardDao) sudokuBoardDaoFactory.getDatabseDao()) {
            jdbcSudokuBoardDao.setName("pierwsze_sudoku");
            jdbcSudokuBoardDao.write(sudokuBoard);
            assertThrows(JdbcException.class, ()-> jdbcSudokuBoardDao.write(sudokuBoard));
        } catch (Exception e) {
            throw new JdbcException(e);
        } finally {
            try {
                Files.walk(Paths.get("myDB"))
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (Exception e) {
                throw new JdbcException(e);
            }
        }
    }
    @Test
    public void WriteNullExceptionTest() throws JdbcException {
        try (JdbcSudokuBoardDao jdbcSudokuBoardDao = (JdbcSudokuBoardDao) sudokuBoardDaoFactory.getDatabseDao()) {
            assertThrows(JdbcException.class, () -> jdbcSudokuBoardDao.write(sudokuBoard));
        } catch (Exception e) {
            throw new JdbcException(e);
        } finally {
            try {
                Files.walk(Paths.get("myDB"))
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (Exception e) {
                throw new JdbcException(e);
            }
        }
    }
    @Test
    public void getBoardsNamesTest() throws JdbcException {
        try (JdbcSudokuBoardDao jdbcSudokuBoardDao = (JdbcSudokuBoardDao) sudokuBoardDaoFactory.getDatabseDao()) {
            jdbcSudokuBoardDao.setName("pierwsze_sudoku");
            jdbcSudokuBoardDao.write(sudokuBoard);
            assertEquals("pierwsze_sudoku", jdbcSudokuBoardDao.getBoardsNames().get(0));
            jdbcSudokuBoardDao.setName("drugie_sudoku");
           jdbcSudokuBoardDao.write(sudokuBoard1);
            assertEquals("drugie_sudoku", jdbcSudokuBoardDao.getBoardsNames().get(0));
            ArrayList<String> names = new ArrayList<>();
            names.add("drugie_sudoku");
            names.add("pierwsze_sudoku");
            //nazwy wyswietlane sa alfabetycznie
            assertEquals(names, jdbcSudokuBoardDao.getBoardsNames());
        } catch (Exception e) {
            throw new JdbcException(e);
        } finally {
            try {
                Files.walk(Paths.get("myDB"))
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (Exception e) {
                throw new JdbcException(e);
            }
        }
    }
    @Test
    public void setGetNameTest() throws JdbcException {
        try (JdbcSudokuBoardDao jdbcSudokuBoardDao = (JdbcSudokuBoardDao) sudokuBoardDaoFactory.getDatabseDao()) {
            assertEquals(null, jdbcSudokuBoardDao.getName());
            jdbcSudokuBoardDao.setName("pierwsze_sudoku");
            assertEquals("pierwsze_sudoku", jdbcSudokuBoardDao.getName());
        } catch (Exception e) {
            throw new JdbcException(e);
        } finally {
            try {
                Files.walk(Paths.get("myDB"))
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (Exception e) {
                throw new JdbcException(e);
            }
        }
    }
    @Test
    public void constructorTest() throws JdbcException {
        try (JdbcSudokuBoardDao jdbcSudokuBoardDao = (JdbcSudokuBoardDao) sudokuBoardDaoFactory.getDatabseDao()) {
            jdbcSudokuBoardDao.setName("pierwsze_sudoku");
            jdbcSudokuBoardDao.write(sudokuBoard);
        } catch (Exception e) {
            throw new JdbcException(e);
        }
        try (JdbcSudokuBoardDao jdbcSudokuBoardDao1 = (JdbcSudokuBoardDao) sudokuBoardDaoFactory.getDatabseDao()) {
            jdbcSudokuBoardDao1.setName("drugie_sudoku");
            jdbcSudokuBoardDao1.write(sudokuBoard);
        } catch (Exception e) {
            throw new JdbcException(e);
        } finally {
            try {
                Files.walk(Paths.get("myDB"))
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (Exception e) {
                throw new JdbcException(e);
            }
        }
    }
}