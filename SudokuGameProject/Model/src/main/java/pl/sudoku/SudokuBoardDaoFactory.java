package pl.sudoku;

import pl.sudoku.exception.DaoException;

public class SudokuBoardDaoFactory {
    public Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public Dao<SudokuBoard> getDatabseDao() {
        try {
            return new JdbcSudokuBoardDao();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

}