package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcSudokuBoardDaoTest {
    @Test
    public void ReadWriteDataBaseTest() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        SudokuBoardDaoFactory sudokuBoardDaoFactory = new SudokuBoardDaoFactory();
       try( JdbcSudokuBoardDao jdbcSudokuBoardDao = (JdbcSudokuBoardDao) sudokuBoardDaoFactory.getDatabseDao()) {
          jdbcSudokuBoardDao.setName("pierwsze_sudoku");
         //  jdbcSudokuBoardDao.write(sudokuBoard);
//           System.out.println(jdbcSudokuBoardDao.metodaTestowa().size());
           jdbcSudokuBoardDao.metodaTestowa();
           jdbcSudokuBoardDao.metodaTestowa2();
           SudokuBoard sudokuBoard1 = jdbcSudokuBoardDao.read();
           System.out.println(sudokuBoard1.toString());
       } catch (Exception e) {
           throw new RuntimeException(e);
       }

    }

}