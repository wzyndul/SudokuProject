package pl.sudoku;

import java.sql.SQLException;
import pl.sudoku.exception.DaoException;


public interface Dao<T> extends AutoCloseable {
    T read() throws DaoException, SQLException;

    void write(T obj) throws DaoException, SQLException;
}
