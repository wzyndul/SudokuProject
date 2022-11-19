import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

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

    private ArrayList<Integer> randomList() {
        ArrayList<Integer> list = new ArrayList();

        for (int i = 1; i <= size; ++i) {
            list.add(i);
        }
        Collections.shuffle(list);

        return list;
    }

    private boolean fillSudoku(int i, int j, SudokuBoard board) {
        if (j == size && i == size - 1) {
            return true;
        }
        if (j == size) {
            j = 0;
            i++;
        }
        ArrayList<Integer> numberList = randomList();
        for (int k = 0; k < size; k++) {
            if (checkRow(i, numberList.get(k), board) && checkCol(j, numberList.get(k),
                    board) && checkBox(i, j, numberList.get(k), board)) {
                board.set(i, j, numberList.get(k));
                if (fillSudoku(i, j + 1, board)) {
                    return true;
                }
                board.set(i, j, 0);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "BacktrackingSudokuSolver{" +
                "size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BacktrackingSudokuSolver that = (BacktrackingSudokuSolver) o;
        return size == that.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }
}
