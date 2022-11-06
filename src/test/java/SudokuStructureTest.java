import org.junit.jupiter.api.Test;

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
        SudokuRow sudokuRow = new SudokuRow(structure);
        assertTrue(sudokuRow.verify());
        structure[8].setFieldValue(1);
        assertFalse(sudokuRow.verify());

    }
}