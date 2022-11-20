import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuColumn extends SudokuStructure {


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("structure", structure)
                .toString();
    }

    SudokuColumn(SudokuField[] fields) {
        super(fields);
    }



}