
public class SudokuColumn extends SudokuStructure {

    @Override
    public String toString() {
        return "SudokuColumn{" + "structure=" + structure + '}';
    }

    SudokuColumn(SudokuField[] fields) {
        super(fields);
    }



}