import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldsTest {

    @Test
    public void setGetFieldValueTest() {
        SudokuField sudokufield = new SudokuField();
        sudokufield.setFieldValue(8);
        assertEquals(sudokufield.getFieldValue(), 8);
        sudokufield.setFieldValue(23);
        assertNotEquals(sudokufield.getFieldValue(), 23);
        sudokufield.setFieldValue(-23);
        assertNotEquals(sudokufield.getFieldValue(), -23);
    }


    @Test
    void HashCodeAndEqualsTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuField a = new SudokuField();
        SudokuField b = new SudokuField();
        SudokuField c = new SudokuField();
        a.setFieldValue(5);
        b.setFieldValue(5);
        c.setFieldValue(5);
        assertTrue(a.equals(a));
        assertEquals(a.equals(b), true);
        assertEquals(b.equals(a), true);
        assertEquals(a.hashCode(), b.hashCode());

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));

        b.setFieldValue(6);
        assertEquals(a.equals(b), false);
        assertEquals(a.equals(backtrackingSudokuSolver), false);
        assertEquals(a.equals(null), false);
        assertNotEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void toStringTest() {
        SudokuField b = new SudokuField();
        assertNotEquals(b.toString(), null);
    }

    @Test
    void CloneTestField() throws CloneNotSupportedException {
        SudokuField field = new SudokuField();
        field.setFieldValue(2);
        SudokuField copiedField = field.clone();
        assertNotNull(copiedField);
        assertTrue(field.equals(copiedField));
        assertTrue(copiedField.equals(field));
        assertEquals(field.hashCode(), copiedField.hashCode());
        assertNotSame(field, copiedField);
        assertEquals(field.getFieldValue(), copiedField.getFieldValue());
        field.setFieldValue(3);
        assertNotEquals(field.getFieldValue(), copiedField.getFieldValue());
    }

    @Test
    void compareToTest() {
        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        field1.setFieldValue(1);
        field2.setFieldValue(9);
        assertEquals(field1.compareTo(field2), -1);
        assertEquals(field2.compareTo(field1), 1);
        field2.setFieldValue(1);
        assertEquals(field1.compareTo(field2), 0);
        assertThrows(NullPointerException.class, () -> {
            field1.compareTo(null);
        });
    }
}