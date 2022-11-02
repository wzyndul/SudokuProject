public class SudokuBoard {
    private final int size = 9;
    private SudokuField[][] board = new SudokuField[9][9];


    private final SudokuSolver sudokuSolver;

    SudokuBoard(SudokuSolver solver) {
        sudokuSolver = solver;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {    // nie wiem czy tam trzeba / mozna ale inicjalizuje cala tablice
                board[i][j] = new SudokuField();
            }
        }

    }

    public int get(int x, int y) {
        return board[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        board[x][y].setFieldValue(value);
    }

    public void solveGame() {
        sudokuSolver.solve(this);
        // checkBoard();
    }

    public SudokuRow getRow(int y) {
        SudokuField[] table = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            table[i] = board[y][i];
        }
        return new SudokuRow(table);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] table = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            table[i] = board[i][x];
        }
        return new SudokuColumn(table);
    }

    public SudokuBox getBox(int x, int y) {
        SudokuField[] table = new SudokuField[9];
        int rowStart = x - x % 3;
        int colStart = y - y % 3;
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[k] = board[rowStart + i][colStart + j];
                k++;
            }
        }
        return new SudokuBox(table);
    }
    /* public boolean checkBoard() {     //to moglo byc prywatne i wywolywane po solviez

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

    public void Print() {

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                System.out.print(board[i][j].getFieldValue());
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}