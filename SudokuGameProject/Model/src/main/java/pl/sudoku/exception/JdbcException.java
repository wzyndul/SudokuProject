package pl.sudoku.exception;

public class JdbcException extends DaoException {
    public JdbcException(Throwable cause) {
        super(cause);
    }

    public JdbcException(String message) {
        super(message);
    }

    public JdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
