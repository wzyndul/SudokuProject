import java.util.Collections;

public class SudokuBox extends SudokuStructure implements Cloneable {
    SudokuBox(SudokuField[] fields) {
        super(fields);
    }

    @Override
    public SudokuBox clone() {
        SudokuField[] table = new SudokuField[structure.size()];
        for (int i = 0; i < structure.size(); i++) {
            table[i] = new SudokuField();
            table[i].setFieldValue(Collections.unmodifiableList(structure).get(i).getFieldValue());
        }
        return new SudokuBox(table);
    }
}

