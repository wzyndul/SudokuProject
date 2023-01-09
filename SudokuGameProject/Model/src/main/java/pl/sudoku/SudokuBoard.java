package pl.sudoku;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.sudoku.exception.SudokuBoardException;


public class SudokuBoard implements Serializable, Cloneable {
    private final transient ResourceBundle bundle = ResourceBundle.getBundle("Language");
    private final int size = 81;

    private List<SudokuField> board;

    private final SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver solver) {
        this.sudokuSolver = solver;
        SudokuField[] table = new SudokuField[size];
        for (int i = 0; i < size; ++i) {
            table[i] = new SudokuField();
        }
        this.board = Arrays.asList(table);
    }

    private int updateCoordinates(int x, int y) {
        return x * 9 + y;
    }

    public int get(int x, int y) throws SudokuBoardException {
        try {
            return board.get(updateCoordinates(x, y)).getFieldValue();
        } catch (IndexOutOfBoundsException e) {
            throw new SudokuBoardException(bundle.getString("outOfBounds"), e.getCause());
        }
    }

    public void set(int x, int y, int value) throws SudokuBoardException {
        try {
            board.get(updateCoordinates(x, y)).setFieldValue(value);
        } catch (IndexOutOfBoundsException e) {
            throw new SudokuBoardException(bundle.getString("outOfBounds"), e.getCause());
        }
    }

    public void solveGame() throws SudokuBoardException {
        sudokuSolver.solve(this);
        checkBoard();
    }

    public SudokuRow getRow(int y) throws SudokuBoardException {
        try {
            SudokuField[] table = new SudokuField[9];
            for (int i = 0; i < 9; i++) {
                table[i] = new SudokuField();
                table[i].setFieldValue(board.get(updateCoordinates(y, i)).getFieldValue());
            }
            return new SudokuRow(table);
        } catch (IndexOutOfBoundsException e) {
            throw new SudokuBoardException(bundle.getString("outOfBounds"), e.getCause());
        }
    }

    public SudokuColumn getColumn(int x) throws SudokuBoardException {
        try {
            SudokuField[] table = new SudokuField[9];
            for (int i = 0; i < 9; i++) {
                table[i] = new SudokuField();
                table[i].setFieldValue(board.get(updateCoordinates(i, x)).getFieldValue());
            }
            return new SudokuColumn(table);
        } catch (IndexOutOfBoundsException e) {
            throw new SudokuBoardException(bundle.getString("outOfBounds"), e.getCause());
        }
    }

    public SudokuBox getBox(int x, int y) throws SudokuBoardException {
        try {
            SudokuField[] table = new SudokuField[9];
            int rowStart = x - x % 3;
            int colStart = y - y % 3;
            int index = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    table[index] = new SudokuField();
                    table[index].setFieldValue(
                            board.get(updateCoordinates(rowStart + i, colStart + j))
                                    .getFieldValue());
                    index++;
                }
            }
            return new SudokuBox(table);
        } catch (IndexOutOfBoundsException e) {
            throw new SudokuBoardException(bundle.getString("outOfBounds"), e.getCause());
        }

    }

    private boolean checkBoard() throws SudokuBoardException {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuBoard that = (SudokuBoard) o;

        return new EqualsBuilder().append(size, that.size).append(board, that.board)
                .append(sudokuSolver, that.sudokuSolver).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(size)
                .append(board).append(sudokuSolver).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("size", size)
                .append("board", board)
                .append("sudokuSolver", sudokuSolver)
                .toString();
    }

    @Override
    public SudokuBoard clone() {
        String solverName = this.sudokuSolver.getClass().getCanonicalName();
        try {
            Class<?> myClass = Class.forName(solverName);
            Constructor<?> constructor = myClass.getConstructor();
            Object solver = constructor.newInstance();
            SudokuBoard sudokuBoard = new SudokuBoard((SudokuSolver) solver);
            int k = 0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sudokuBoard.set(i, j, Collections.unmodifiableList(board)
                            .get(k).getFieldValue());
                    k++;
                }
            }
            return sudokuBoard;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                 | IllegalAccessException | InvocationTargetException | SudokuBoardException e) {
            try {
                throw new SudokuBoardException(bundle.getString("cloneError"), e);
            } catch (SudokuBoardException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

