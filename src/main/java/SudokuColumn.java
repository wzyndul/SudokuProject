import java.util.Collections;

public class SudokuColumn extends SudokuStructure implements Cloneable {
    SudokuColumn(SudokuField[] fields) {
        super(fields);
    }

    @Override
    public SudokuColumn clone() {
        SudokuField[] table = new SudokuField[structure.size()];
        for (int i = 0; i < structure.size(); i++) {
            table[i] = new SudokuField();
            table[i].setFieldValue(Collections.unmodifiableList(structure).get(i).getFieldValue());
        }
        return new SudokuColumn(table);
    }
}