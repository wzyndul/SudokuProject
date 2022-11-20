import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuRow extends SudokuStructure {
    SudokuRow(SudokuField[] fields) {
        super(fields);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("structure", structure)
                .toString();
    }
}