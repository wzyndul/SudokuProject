package pl.sudoku;

public class SudokuColumn extends SudokuStructure implements Cloneable {
    public SudokuColumn(SudokuField[] fields) {
        super(fields);
    }

    @Override
    public SudokuColumn clone() {
        SudokuField[] table = new SudokuField[getAll().size()];
        for (int i = 0; i < getAll().size(); i++) {
            table[i] = new SudokuField();
            table[i].setFieldValue(getAll().get(i).getFieldValue());
        }
        return new SudokuColumn(table);
    }
}