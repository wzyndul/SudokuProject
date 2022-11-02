public abstract class SudokuStructure {
    private SudokuField[] structure;
    SudokuStructure(SudokuField[] x) {
        this.structure = x;
    }

    public abstract boolean verify();
}
