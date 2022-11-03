public abstract class SudokuStructure {
    private SudokuField[] structure;   //czy to tak ma byc (?)

    SudokuStructure(SudokuField[] x) {
        this.structure = x;
    }

    public abstract boolean verify();
}
