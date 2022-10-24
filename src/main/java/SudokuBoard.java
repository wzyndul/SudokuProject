public class SudokuBoard {
    private final int size = 9;
    private int[][] board = new int[size][size];

    private BacktrackingSudokuSolver sudokuSolver;

    SudokuBoard() {
        sudokuSolver = new BacktrackingSudokuSolver();
    }

    public int get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, int value) {
        board[x][y] = value;
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    /* public boolean checkBoard() {

        for (int c = 0; c < 9; c++) {
            for (int r = 0; r < 9; r++) {

                for (int i = r + 1; i < 9; i++) {
                    if (board[c][r] == board[c][i]) {
                        return false;
                    }
                }

                for (int i = c + 1; i < 9; i++) {
                    if (board[c][r] == board[i][r]) {
                        return false;
                    }
                }

                int posCol = (c / 3) * 3;
                int posRow = (r / 3) * 3;
                int num = board[c][r];

                for (int i = posCol; i < 3 + posCol; i++) {
                    for (int j = posRow; j < 3 + posRow; j++) {
                        if (i == c && j == r) {
                            continue;
                        }
                        if (board[i][j] == num) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }*/
}