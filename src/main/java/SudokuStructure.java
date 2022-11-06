public abstract class SudokuStructure {
    protected SudokuField[] structure;

    SudokuStructure(SudokuField[] fields) {
        this.structure = fields;
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (structure[i].getFieldValue() == structure[j].getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}
