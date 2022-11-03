public class SudokuColumn extends SudokuStructure {
    private SudokuField[] column;

    SudokuColumn(SudokuField[] x) {
        super(x);
        this.column = x;
    }

    public boolean verify() {
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                if (column[i].getFieldValue() == column[i + 1].getFieldValue()) {
                    return false;
                }
            }

        }
        return true;
    }
}
