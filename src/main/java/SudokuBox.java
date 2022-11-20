import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuBox extends SudokuStructure {
    SudokuBox(SudokuField[] fields) {
        super(fields);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("structure", structure)
                .toString();
    }
}

