import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldsTest {

    @Test
    public void setGetFieldValueTest() {
        SudokuField sudokufield = new SudokuField();
        sudokufield.setFieldValue(8);
        assertEquals(sudokufield.getFieldValue(),8);
        sudokufield.setFieldValue(23);
        assertNotEquals(sudokufield.getFieldValue(),23);
        sudokufield.setFieldValue(-23);
        assertNotEquals(sudokufield.getFieldValue(),-23);
    }




    @Test
    void HashCodeAndEqualsTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuField a = new SudokuField();
        SudokuField b = new SudokuField();
        a.setFieldValue(5);
        b.setFieldValue(5);
        assertEquals(a.equals(a),true);
        assertEquals(a.equals(b),true);
        assertEquals(a.hashCode(),b.hashCode());
        b.setFieldValue(6);
        assertEquals(a.equals(b),false);
        assertEquals(a.equals(backtrackingSudokuSolver),false);
        assertEquals(a.equals(null),false);
        assertNotEquals(a.hashCode(),b.hashCode());
    }

    @Test
    void toStringTest() {
        SudokuField b = new SudokuField();
        b.setFieldValue(5);
        assertEquals(b.toString(),"SudokuField{value=5}");
    }
}