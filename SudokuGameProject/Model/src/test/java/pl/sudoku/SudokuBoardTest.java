package pl.sudoku;

import org.junit.jupiter.api.Test;
import pl.sudoku.exception.SudokuBoardException;
import pl.sudoku.exception.SudokuFieldException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {

    @Test
    public void getSetMethodsTest() throws SudokuBoardException {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.set(0, 0, 9);
        assertEquals(sudokuBoard.get(0, 0), 9);
        assertThrows(SudokuBoardException.class, () -> {
            sudokuBoard.set(9, 10, 9);
        });
        assertThrows(SudokuBoardException.class, () -> {
            sudokuBoard.set(-1, -10, 9);
        });
        assertThrows(SudokuBoardException.class, () -> {
            sudokuBoard.get(10, 10);
        });
        assertThrows(SudokuBoardException.class, () -> {
            sudokuBoard.get(-1, -10);
        });
    }


    @Test
    public void differentNumbers() throws SudokuBoardException {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();
        int[][] table = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                table[i][j] = sudokuBoard.get(i, j);
            }
        }
        sudokuBoard.solveGame();
        int notEqual = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard.get(i, j) != table[i][j]) {
                    notEqual++;
                }
            }
        }
        assertNotEquals(81, notEqual);
    }

    @Test
    public void checkBoardTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SudokuBoardException {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();
        Method method = SudokuBoard.class.getDeclaredMethod("checkBoard");
        method.setAccessible(true);
        assertTrue((Boolean) method.invoke(sudokuBoard));

        int temp = sudokuBoard.get(0, 1);
        sudokuBoard.set(0, 1, sudokuBoard.get(0, 0));
        assertFalse((Boolean) method.invoke(sudokuBoard));
        sudokuBoard.set(0, 1, temp);

        temp = sudokuBoard.get(1, 0);
        sudokuBoard.set(1, 0, sudokuBoard.get(0, 0));
        assertFalse((Boolean) method.invoke(sudokuBoard));
        sudokuBoard.set(1, 0, temp);

        sudokuBoard.set(1, 1, sudokuBoard.get(0, 0));
        assertFalse((Boolean) method.invoke(sudokuBoard));
    }


    @Test
    public void equalsAndHashCodeTest() throws SudokuBoardException {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver backtrackingSudokuSolver1 = new BacktrackingSudokuSolver();

        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);
        SudokuBoard sudokuBoard2 = new SudokuBoard(backtrackingSudokuSolver);
        SudokuBoard sudokuBoard3 = new SudokuBoard(backtrackingSudokuSolver1);


        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard1.set(i, j, j + 1);
                sudokuBoard2.set(i, j, j + 1);
                sudokuBoard3.set(i, j, j + 1);
            }
        }
        assertTrue(sudokuBoard1.equals(sudokuBoard1));
        assertTrue(sudokuBoard1.equals(sudokuBoard3));
        assertTrue(sudokuBoard3.equals(sudokuBoard1));
        assertEquals(sudokuBoard1.hashCode(), sudokuBoard3.hashCode());

        assertEquals(sudokuBoard1.equals(sudokuBoard2), true);
        assertEquals(sudokuBoard1.hashCode(), sudokuBoard2.hashCode());

        sudokuBoard2.set(1, 0, 3);

        assertEquals(sudokuBoard1.equals(sudokuBoard2), false);
        assertEquals(sudokuBoard2.equals(sudokuBoard1), false);
        assertEquals(sudokuBoard1.equals(backtrackingSudokuSolver), false);
        assertNotEquals(sudokuBoard1.hashCode(), sudokuBoard2.hashCode());
        assertEquals(sudokuBoard1.equals(null), false);
    }

    @Test
    public void toStringTest() throws SudokuBoardException {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();
        assertNotEquals(sudokuBoard.toString(), null);
    }

    @Test
    public void cloneTest() throws SudokuBoardException {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();
        SudokuBoard sudokuBoardCloned = sudokuBoard.clone();
        assertNotNull(sudokuBoardCloned);
        assertTrue(sudokuBoard.equals(sudokuBoardCloned));
        assertNotSame(sudokuBoard, sudokuBoardCloned);
        sudokuBoard.set(0, 0, sudokuBoardCloned.get(0, 1)); //ustawiam w tym boardzie wartosc z 2
        assertFalse(sudokuBoard.equals(sudokuBoardCloned));
        assertNotSame(sudokuBoard, sudokuBoardCloned);

    }
    @Test
    public void getRowBoxColumnExceptionTest() throws SudokuBoardException {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();
        assertThrows(SudokuBoardException.class, () -> {
            sudokuBoard.getColumn(10);
        });
        assertThrows(SudokuBoardException.class, () -> {
            sudokuBoard.getColumn(-1);
        });
        assertThrows(SudokuBoardException.class, () -> {
            sudokuBoard.getRow(10);
        });
        assertThrows(SudokuBoardException.class, () -> {
            sudokuBoard.getRow(-1);
        });
        assertThrows(SudokuBoardException.class, () -> {
            sudokuBoard.getBox(10,10);
        });
    }


}