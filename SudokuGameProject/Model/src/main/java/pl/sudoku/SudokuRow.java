package pl.sudoku;

public class SudokuRow extends SudokuStructure implements Cloneable {
    public SudokuRow(SudokuField[] fields) {
        super(fields);
    }

    @Override
    public SudokuRow clone() {
        SudokuField[] table = new SudokuField[getAll().size()];
        for (int i = 0; i < getAll().size(); i++) {
            table[i] = new SudokuField();
            table[i].setFieldValue(getAll().get(i).getFieldValue());
        }
        return new SudokuRow(table);
    }
}