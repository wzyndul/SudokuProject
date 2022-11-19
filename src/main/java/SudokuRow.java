
public class SudokuRow extends SudokuStructure {
    SudokuRow(SudokuField[] fields) {
        super(fields);
    }


    @Override
    public String toString() {
        return "SudokuRow{" +
                "structure=" + structure +
                '}';
    }


}