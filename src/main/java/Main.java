public class Main {
    public static void main(String[] args) {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(backtrackingSudokuSolver);
        board.solveGame();
        System.out.println(board.toString());
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        factory.getFileDao("okon").write(board);
        SudokuBoard board1 = factory.getFileDao("okon").read();
        System.out.println(board1.toString());
        System.out.println(board.hashCode() == board1.hashCode());
    }
}
