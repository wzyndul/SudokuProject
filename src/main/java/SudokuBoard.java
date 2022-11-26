import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuBoard {
    private final int size = 81;

    private List<SudokuField> board;

    private final SudokuSolver sudokuSolver;

    SudokuBoard(SudokuSolver solver) {
        sudokuSolver = solver;
        SudokuField[] table = new SudokuField[size];
        for (int i = 0; i < size; ++i) {
            table[i] = new SudokuField();
        }
        this.board = Arrays.asList(table);
    }

    private int updateCoordinates(int x, int y) {
        return x * 9 + y;
    }

    public int get(int x, int y) {
        return board.get(updateCoordinates(x, y)).getFieldValue();
    }

    public void set(int x, int y, int value) {
        board.get(updateCoordinates(x, y)).setFieldValue(value);
    }

    public void solveGame() {

        sudokuSolver.solve(this);
        checkBoard();         //w przyszlsoci mozemy dodac tutaj jakies sprawdzenie
        //czy wszystko jest ok (ale zawsze bedzie) bo solve poprawnie wypelnia
    }

    public SudokuRow getRow(int y) {
        SudokuField[] table = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            table[i] = new SudokuField();
            table[i].setFieldValue(board.get(updateCoordinates(y, i)).getFieldValue());
        }
        return new SudokuRow(table);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] table = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            table[i] = new SudokuField();
            table[i].setFieldValue(board.get(updateCoordinates(i, x)).getFieldValue());
        }
        return new SudokuColumn(table);
    }

    public SudokuBox getBox(int x, int y) {
        SudokuField[] table = new SudokuField[9];
        int rowStart = x - x % 3;
        int colStart = y - y % 3;
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[index] = new SudokuField();
                table[index].setFieldValue(
                        board.get(updateCoordinates(rowStart + i, colStart + j)).getFieldValue());
                index++;
            }
        }
        return new SudokuBox(table);
    }

    private boolean checkBoard() {
        int rowBox = 0;
        int colBox = 0;
        for (int i = 0; i < 9; i++) {
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
            if (colBox == 9) {
                colBox = 0;
                rowBox += 3;
            }
        }
        return true;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this).append("size", size).append("board", board)
                .append("sudokuSolver", sudokuSolver).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuBoard that = (SudokuBoard) o;

        return new EqualsBuilder().append(board, that.board)
                .append(sudokuSolver, that.sudokuSolver).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(19, 35).append(board)
                .append(sudokuSolver).toHashCode();
    }
}

