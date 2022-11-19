import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class SudokuStructure {
    List<SudokuField> structure;

    SudokuStructure(SudokuField[] fields) {
        if (fields.length == 9) {
            this.structure = Arrays.asList(fields);
        }
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

    @Override
    public String toString() {
        return "SudokuStructure{" + "structure=" + structure + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuStructure that = (SudokuStructure) o;
        return Objects.equals(structure, that.structure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(structure);
    }
}