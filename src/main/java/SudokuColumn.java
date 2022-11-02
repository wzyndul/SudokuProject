public class SudokuColumn extends SudokuField {
    private SudokuField[] column = new SudokuField[9];

    public boolean verify() {
            for (int j = 0; j < 9; j++) {
                for (int i = 0; i < 9; i++) {
                    if (column[i].getFieldValue() == column[i+1].getFieldValue())
                        return false;
                }

            }

        return true;

        }
}
