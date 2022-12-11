package pl.sudoku;

public class SudokuBox extends SudokuStructure implements Cloneable {
    SudokuBox(SudokuField[] fields) {
        super(fields);
    }

    @Override
    public SudokuBox clone() {
        SudokuField[] table = new SudokuField[getAll().size()];
        for (int i = 0; i < getAll().size(); i++) {
            table[i] = new SudokuField();
            table[i].setFieldValue(getAll().get(i).getFieldValue());
        }
        return new SudokuBox(table);
    }
}

