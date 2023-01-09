package pl.sudoku.exception;

public class SudokuException extends Exception {

    public SudokuException(String message) {
        super(message);
    }

    public SudokuException(Throwable cause) {
        super(cause);
    }

    public SudokuException(String message, Throwable cause) {
        super(message, cause);
    }
}
