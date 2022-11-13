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


}