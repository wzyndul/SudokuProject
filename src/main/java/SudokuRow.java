public class SudokuRow extends SudokuStructure {
    private SudokuField[] row;

    SudokuRow(SudokuField[] x) {
        super(x);
        this.row = x;
    }

    public boolean verify() {
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                if (row[i].getFieldValue() == row[i+1].getFieldValue())
                    return false;
            }
        }
        return true;
    }
}