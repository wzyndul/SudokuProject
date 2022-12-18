package pl.sudoku;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.sudoku.exception.SudokuStructureException;


public abstract class SudokuStructure {
    private List<SudokuField> structure;

    public SudokuStructure(SudokuField[] fields) {
        try {
            this.structure = Arrays.asList(fields);
        } catch (NullPointerException e) {
            throw new SudokuStructureException("Obiekt jest nullem", e.getCause());
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

    List<SudokuField> getAll() {
        return Collections.unmodifiableList(structure);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("structure", structure)
                .toString();
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

        return new EqualsBuilder().append(structure, that.structure).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(13, 31).append(structure).toHashCode();
    }
}