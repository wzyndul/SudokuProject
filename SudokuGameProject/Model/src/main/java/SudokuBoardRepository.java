public class SudokuBoardRepository implements Repository<SudokuBoard> {

    private final SudokuBoard sudokuBoard;

    SudokuBoardRepository(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    @Override
    public SudokuBoard createInstance() {
        return sudokuBoard.clone();
    }
}
