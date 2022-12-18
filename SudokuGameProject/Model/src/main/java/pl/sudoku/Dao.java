package pl.sudoku;

import pl.sudoku.exception.DaoException;

public interface Dao<T> extends AutoCloseable {
    T read() throws DaoException;

    void write(T obj) throws DaoException;
}
