

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
    }

    public SudokuRow getRow(int y) {
        SudokuField[] table = new SudokuField[9];
        for (int i = 0; i < size; i++) {
            table[i] = new SudokuField();
            table[i].setFieldValue(board[y][i].getFieldValue());
        }
        return new SudokuRow(table);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] table = new SudokuField[9];
        for (int i = 0; i < size; i++) {
            table[i] = new SudokuField();
            table[i].setFieldValue(board[i][x].getFieldValue());
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
                table[index] = new SudokuField();
                table[index].setFieldValue(board[rowStart + i][colStart + j].getFieldValue());
                index++;
            }
        }
        return new SudokuBox(table);
    }

    boolean checkBoard() {
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

