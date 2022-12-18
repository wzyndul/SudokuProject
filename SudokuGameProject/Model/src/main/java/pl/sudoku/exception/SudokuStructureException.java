package pl.sudoku.exception;

public class SudokuStructureException extends SudokuBoardException {
    public SudokuStructureException(String message) {
        super(message);
    }

    public SudokuStructureException(String message, Throwable cause) {
        super(message, cause);
    }
}
