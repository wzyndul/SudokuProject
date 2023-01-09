package pl.sudoku;

import org.junit.jupiter.api.Test;
import pl.sudoku.exception.JdbcException;


import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardDaoFactoryTest {
    @Test
    public void notNullFactoryTest() throws SQLException, JdbcException {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        assertNotNull(factory.getFileDao("okon.txt"));
        assertNotNull(factory.getDatabseDao());
    }
}