package pl.sudoku;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardDaoFactoryTest {
    @Test
    void notNullFactoryTest() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        assertNotNull(factory.getFileDao("okon.txt"));
    }

}