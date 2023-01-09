package pl.sudoku;

import java.sql.SQLException;
import pl.sudoku.exception.JdbcException;


public class SudokuBoardDaoFactory {
    public Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public Dao<SudokuBoard> getDatabseDao() {
        try {
            return new JdbcSudokuBoardDao();
        } catch (JdbcException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}