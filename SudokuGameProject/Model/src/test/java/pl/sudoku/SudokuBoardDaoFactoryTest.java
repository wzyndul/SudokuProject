package pl.sudoku;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardDaoFactoryTest {
    @Test
    public void notNullFactoryTest() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        assertNotNull(factory.getFileDao("okon.txt"));
    }

}