package pl.sudoku.exception;

public class SudokuBoardException extends SudokuException {
    public SudokuBoardException(String message) {
        super(message);
    }

    public SudokuBoardException(String message, Throwable cause) {
        super(message, cause);
    }

}
