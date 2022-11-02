public class Main {
    public static void main(String[] args) {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(backtrackingSudokuSolver);
        board.solveGame();
        board.Print();
    }
}
