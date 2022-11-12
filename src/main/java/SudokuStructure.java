import java.util.Arrays;
import java.util.List;

public abstract class SudokuStructure {
    List<SudokuField> structure;

    SudokuStructure(SudokuField[] fields) {
        this.structure = Arrays.asList(fields);
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (structure.get(i).getFieldValue() == structure.get(j).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}
