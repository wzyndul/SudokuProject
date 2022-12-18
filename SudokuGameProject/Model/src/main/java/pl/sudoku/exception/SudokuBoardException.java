package pl.sudoku.exception;

public class SudokuBoardException extends RuntimeException {
    public SudokuBoardException(String message) {
        super(message);
    }

    public SudokuBoardException(String message, Throwable cause) {
        super(message, cause);
    }

}
