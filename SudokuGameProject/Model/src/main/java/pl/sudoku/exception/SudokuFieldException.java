package pl.sudoku.exception;

public class SudokuFieldException extends SudokuStructureException {
    public SudokuFieldException(String message) {
        super(message);
    }

    public SudokuFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
