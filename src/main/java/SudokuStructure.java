import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
        return new ToStringBuilder(this)
                .append("structure", structure)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SudokuStructure that = (SudokuStructure) o;

        return new EqualsBuilder().append(structure, that.structure).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(structure).toHashCode();
    }
}