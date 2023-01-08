package pl.sudoku.exception;

import java.io.IOException;

public class DaoException extends IOException {  //jeden korzen zrob
    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
