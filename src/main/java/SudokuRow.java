import java.util.Collections;

public class SudokuRow extends SudokuStructure implements Cloneable {
    SudokuRow(SudokuField[] fields) {
        super(fields);
    }

    @Override
    public SudokuRow clone() {
        SudokuField[] table = new SudokuField[structure.size()];
        for (int i = 0; i < structure.size(); i++) {
            table[i] = new SudokuField();
            table[i].setFieldValue(Collections.unmodifiableList(structure).get(i).getFieldValue());
        }
        return new SudokuRow(table);
    }
}