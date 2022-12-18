package pl.sudoku;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import pl.sudoku.exception.SudokuStructureException;


import static org.junit.jupiter.api.Assertions.*;


public class SudokuStructureTest {
    private final Logger log = LoggerFactory.getLogger(SudokuStructureTest.class);
    @Test
    public void verifyTest() {

        log.info("logi dzialaja");
        SudokuField[] structure = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            structure[i] = new SudokuField();
            structure[i].setFieldValue(i + 1);
        }
        assertThrows(SudokuStructureException.class, () -> {
            SudokuRow sudokuRowTest = new SudokuRow(null);
        });


        SudokuRow sudokuRow = new SudokuRow(structure);
        assertTrue(sudokuRow.verify());
        structure[8].setFieldValue(1);
        assertFalse(sudokuRow.verify());
        structure[8].setFieldValue(9);

        SudokuBox sudokuBox = new SudokuBox(structure);
        assertTrue(sudokuBox.verify());
        structure[8].setFieldValue(1);
        assertFalse(sudokuBox.verify());
        structure[8].setFieldValue(9);

        SudokuColumn sudokuColumn = new SudokuColumn(structure);
        assertTrue(sudokuColumn.verify());
        structure[8].setFieldValue(1);
        assertFalse(sudokuColumn.verify());
    }


    @Test
    public void hashCodeAndEqualsTest() {
        SudokuField[] structure = new SudokuField[9];
        SudokuField[] structure2 = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            structure[i] = new SudokuField();
            structure[i].setFieldValue(i + 1);
            structure2[i] = new SudokuField();
            structure2[i].setFieldValue(i + 1);
        }

        SudokuRow row = new SudokuRow(structure);
        SudokuColumn column0 = new SudokuColumn(structure);
        SudokuColumn column1 = new SudokuColumn(structure);
        SudokuColumn column2 = new SudokuColumn(structure);
        SudokuColumn column3 = new SudokuColumn(structure2);
        SudokuBox box = new SudokuBox(structure);

        assertTrue(column0.equals(column1));
        assertTrue(column1.equals(column0));

        assertTrue(column1.equals(column1));

        assertFalse(row.equals(column1));
        assertFalse(column1.equals(row));
        assertEquals(row.hashCode(), column1.hashCode());

        assertEquals(column1.equals(box), false);
        assertEquals(column1.equals(null), false);
        assertEquals(column1.equals(column1), true);
        assertEquals(column1.hashCode(), column2.hashCode());

        assertEquals(column1.equals(column2), true);
        assertEquals(column2.equals(column0), true);
        assertEquals(column1.equals(column0), true);


        structure[3].setFieldValue(9);
        assertEquals(column1.equals(column3), false);
        assertNotEquals(column1.hashCode(), column3.hashCode());
    }


    @Test
    public void ToStringTest() {
        SudokuField[] structure = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            structure[i] = new SudokuField();
            structure[i].setFieldValue(i + 1);
        }
        SudokuColumn column = new SudokuColumn(structure);
        SudokuRow row = new SudokuRow(structure);
        SudokuBox box = new SudokuBox(structure);
        assertNotEquals(box.toString(), null);
        assertNotEquals(column.toString(), null);
        assertNotEquals(row.toString(), null);
    }
    @Test
    public void CloneTestStructure() {
        SudokuField[] structure = new SudokuField[9];
        SudokuField[] structure1 = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            structure[i] = new SudokuField();
            structure[i].setFieldValue(i + 1);
            structure1[i] = new SudokuField();
            structure1[i].setFieldValue(i + 1);
        }
        SudokuRow row1 = new SudokuRow(structure);
        SudokuRow row2 = row1.clone();
        assertNotNull(row2);
        assertTrue(row1.equals(row2));
        assertTrue(row2.equals(row1));
        assertNotSame(row1, row2);

        SudokuColumn col1 = new SudokuColumn(structure);
        SudokuColumn col2 = col1.clone();
        assertNotNull(col2);
        assertTrue(col1.equals(col2));
        assertTrue(col2.equals(col1));
        assertNotSame(col1, col2);

        SudokuBox box1 = new SudokuBox(structure);
        SudokuBox box2 = box1.clone();
        assertNotNull(box2);
        assertTrue(box1.equals(box2));
        assertTrue(box2.equals(box1));
        assertNotSame(box1, box2);


        SudokuBox box3 = new SudokuBox(structure1);
        SudokuColumn col3 = new SudokuColumn(structure1);
        SudokuRow row3 = new SudokuRow(structure1);
        SudokuColumn col4 = col3.clone();
        SudokuRow row4 = row3.clone();
        SudokuBox box4 = box3.clone();

        assertTrue(box3.equals(box4));
        assertNotSame(box3, box4);
        assertTrue(row3.equals(row4));
        assertNotSame(row3, row4);
        assertTrue(col3.equals(col4));
        assertNotSame(col3, col4);

        structure1[8].setFieldValue(1);

        assertFalse(box3.equals(box4));
        assertFalse(col3.equals(col4));
        assertFalse(row3.equals(row4));
    }
}