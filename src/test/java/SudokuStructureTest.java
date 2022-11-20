import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class SudokuStructureTest {
    @Test
    public void verifyTest() {
        SudokuField[] structure = new SudokuField[9];
        for(int i=0;i<9;i++){
            structure[i]=new SudokuField();
            structure[i].setFieldValue(i+1);
        }

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
    void hashCodeAndEqualsTest() {
        SudokuField[] structure = new SudokuField[9];
        SudokuField[] structure2 = new SudokuField[9];
        for(int i=0;i<9;i++){
            structure[i]=new SudokuField();
            structure[i].setFieldValue(i+1);
        }
        for(int i=8;i>=0;i--){
            structure2[i]=new SudokuField();
            structure2[i].setFieldValue(i+1);
        }
        SudokuColumn column1 =new SudokuColumn(structure);
        SudokuColumn column2 =new SudokuColumn(structure);
        SudokuColumn column3 =new SudokuColumn(structure2);
        SudokuBox box = new SudokuBox(structure);

        assertNotEquals(column1.toString(),null);
        assertEquals(column1.equals(column1),true);
        assertEquals(column1.hashCode(),column2.hashCode());
        assertEquals(column1.equals(column2),true);
        assertEquals(column1.equals(box),false);
        assertEquals(column1.equals(null),false);
        structure[3].setFieldValue(9);
        assertEquals(column1.equals(column3),false);
        assertNotEquals(column1.hashCode(),column3.hashCode());
    }


    @Test
    void ToStringTest() {
        SudokuField[] structure = new SudokuField[9];
        for(int i=0;i<9;i++){
            structure[i]=new SudokuField();
            structure[i].setFieldValue(i+1);
        }
        SudokuColumn column =new SudokuColumn(structure);
        SudokuRow row = new SudokuRow(structure);
        SudokuBox box = new SudokuBox(structure);
        assertNotEquals(box.toString(),null);
        assertNotEquals(column.toString(),null);
        assertNotEquals(row.toString(),null);
    }
}