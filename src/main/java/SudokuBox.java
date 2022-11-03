public class SudokuBox extends SudokuStructure {
    private SudokuField[] box;

    SudokuBox(SudokuField[] x) {
        super(x);
        this.box = x;
    }

    public boolean verify() {
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                if (box[i].getFieldValue() == box[i + 1].getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}

