public class SudokuBox extends SudokuField {
    private SudokuField[] box = new SudokuField[9];

        public boolean verify() {
            for (int j = 0; j < 9; j++) {
                for (int i = 0; i < 9; i++) {
                    if (box[i].getFieldValue() == box[i+1].getFieldValue())
                        return false;
                }

            }

            return true;

        }
    }

