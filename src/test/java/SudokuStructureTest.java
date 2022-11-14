import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class SudokuStructureTest {
    @Test
    public void verifyTest() {
        SudokuField[] structure = new SudokuField[9];
        for(int i=0;i<9;i++){
            structure[i]=new SudokuField();
            structure[i].setFieldValue(i+1);
        }
        List<SudokuField> list =Arrays.asList(structure);
        SudokuRow sudokuRow = new SudokuRow(list);
        assertTrue(sudokuRow.verify());
        structure[8].setFieldValue(1);
        assertFalse(sudokuRow.verify());
        structure[8].setFieldValue(9);
        SudokuBox sudokuBox = new SudokuBox(list);
        assertTrue(sudokuBox.verify());
        structure[8].setFieldValue(1);
        assertFalse(sudokuBox.verify());
        structure[8].setFieldValue(9);
        SudokuColumn sudokuColumn = new SudokuColumn(list);
        assertTrue(sudokuColumn.verify());
        structure[8].setFieldValue(1);
        assertFalse(sudokuColumn.verify());

    }
}