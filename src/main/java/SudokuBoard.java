public class SudokuBoard {
    private final int size = 9;
    private SudokuField[][] board = new SudokuField[size][size];


    private final SudokuSolver sudokuSolver;

    SudokuBoard(SudokuSolver solver) {
        sudokuSolver = solver;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
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
        // checkBoard();  tak jakos chyba trzeba to ogarnac
    }

    public SudokuRow getRow(int y) {
        SudokuField[] table = new SudokuField[9];
        for (int i = 0; i < size; i++) {
            table[i] = board[y][i];
        }
        return new SudokuRow(table);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] table = new SudokuField[9];
        for (int i = 0; i < size; i++) {
            table[i] = board[i][x];
        }
        return new SudokuColumn(table);
    }

    public SudokuBox getBox(int x, int y) {
        SudokuField[] table = new SudokuField[size];
        int rowStart = x - x % 3;
        int colStart = y - y % 3;
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[index] = board[rowStart + i][colStart + j];
                index++;
            }
        }
        return new SudokuBox(table);
    }

    private boolean checkBoard() {
        int rowBox = 0;
        int colBox = 0;
        for (int i = 0; i < size; i++) {
            if (!getColumn(i).verify()) {
                return false;
            }
            if (!getRow(i).verify()) {
                return false;
            }
            if (!getBox(rowBox, colBox).verify()) {
                return false;
            }
            colBox += 3;
            if (colBox == size) {
                colBox = 0;
                rowBox += 3;
            }
        }
        return true;
    }
}
//    public boolean checkBoard() {  //stary checkoard
//
//        for (int c = 0; c < 9; c++) {
//            for (int r = 0; r < 9; r++) {
//
//                for (int i = r + 1; i < 9; i++) {
//                    if (board[c][r] == board[c][i]) {
//                        return false;
//                    }
//                }
//
//                for (int i = c + 1; i < 9; i++) {
//                    if (board[c][r] == board[i][r]) {
//                        return false;
//                    }
//                }
//
//                int posCol = (c / 3) * 3;
//                int posRow = (r / 3) * 3;
//                int num = board[c][r].getFieldValue();
//
//                for (int i = posCol; i < 3 + posCol; i++) {
//                    for (int j = posRow; j < 3 + posRow; j++) {
//                        if (i == c && j == r) {
//                            continue;
//                        }
//                        if (board[i][j].getFieldValue() == num) {
//                            return false;
//                        }
//                    }
//                }
//            }
//        }
//        return true;
//    }
