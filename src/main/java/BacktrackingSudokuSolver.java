import java.util.ArrayList;
import java.util.Collections;

public class BacktrackingSudokuSolver implements SudokuSolver {

    private final int size = 9;


    @Override
    public void solve(SudokuBoard board) {
        clear(board);
        fillSudoku(0, 0, board);
    }

    private void clear(SudokuBoard board) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board.set(i, j, 0);
            }
        }
    }

    private boolean checkRow(int row, int number, SudokuBoard board) {
        for (int i = 0; i < size; i++) {
            if (board.get(row, i) == number) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCol(int col, int number, SudokuBoard board) {
        for (int i = 0; i < size; i++) {
            if (board.get(i, col) == number) {
                return false;
            }
        }
        return true;
    }

    private boolean checkBox(int row, int col, int number, SudokuBoard board) {
        int rowStart = row - row % 3;
        int colStart = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.get(rowStart + i, colStart + j) == number) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[] randomList() {
        ArrayList<Integer> list = new ArrayList();

        for (int i = 1; i <= size; ++i) {
            list.add(i);
        }

        Collections.shuffle(list);
        int[] result = new int[size];
        for (int i = 0; i < size; ++i) {
            result[i] = list.get(i);
        }
        return result;
    }

    private boolean fillSudoku(int i, int j, SudokuBoard board) {
        if (j == size && i == size - 1) {
            return true;
        }
        if (j == size) {
            j = 0;
            i++;
        }
        int[] numberTab = randomList();
        for (int k = 0; k < size; k++) {
            if (checkRow(i, numberTab[k], board) && checkCol(j, numberTab[k],
                    board) && checkBox(i, j, numberTab[k], board)) {
                board.set(i, j, numberTab[k]);
                if (fillSudoku(i, j + 1, board)) {
                    return true;
                }
                board.set(i, j, 0);
            }
        }
        return false;
    }
}
