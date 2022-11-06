public class SudokuField {
    private int value;

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        if (value > -1 && value < 10) {
            this.value = value;
        }
    }
}
