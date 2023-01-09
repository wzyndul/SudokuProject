package pl.sudoku;

import pl.sudoku.exception.SudokuFieldWrongNumberException;
import pl.sudoku.exception.SudokuStructureException;

public class SudokuBox extends SudokuStructure implements Cloneable {
    public SudokuBox(SudokuField[] fields) throws SudokuStructureException {
        super(fields);
    }

    @Override
    public SudokuBox clone() {
        SudokuField[] table = new SudokuField[getAll().size()];
        for (int i = 0; i < getAll().size(); i++) {
            table[i] = new SudokuField();
            try {
                table[i].setFieldValue(getAll().get(i).getFieldValue());
            } catch (SudokuFieldWrongNumberException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            return new SudokuBox(table);
        } catch (SudokuStructureException e) {
            throw new RuntimeException(e);
        }
    }
}

