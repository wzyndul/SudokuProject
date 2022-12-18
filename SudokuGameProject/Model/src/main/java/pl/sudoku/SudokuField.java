package pl.sudoku;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import pl.sudoku.exception.SudokuFieldException;


public class SudokuField implements Serializable, Comparable<SudokuField>, Cloneable {
    private int value;


    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        if (value > -1 && value < 10) {
            this.value = value;
        } else {
            throw new SudokuFieldException("Wartosc powinna byc z zakresu 0-9");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuField that = (SudokuField) o;

        return new EqualsBuilder().append(value, that.value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(value).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
                .toString();
    }

    @Override
    public int compareTo(SudokuField o) {
        try {
            if (this.getFieldValue() == o.getFieldValue()) {
                return 0;
            } else if (this.getFieldValue() > o.getFieldValue()) {
                return 1;
            } else {
                return -1;
            }
        } catch (NullPointerException e) {
            throw new SudokuFieldException("obiekt jest nullem", e.getCause());
        }

    }

    @Override
    protected SudokuField clone() throws CloneNotSupportedException {
        return (SudokuField) super.clone();
    }
}
