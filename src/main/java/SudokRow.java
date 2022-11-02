public class SudokRow extends SudokuField {
    private SudokuField[] row = new SudokuField[9];

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