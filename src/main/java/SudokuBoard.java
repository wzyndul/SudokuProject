public class SudokuBoard {
    private final int size = 9;
    private SudokuField[][] board = new SudokuField[9][9];
//    public SudokuBox getBox(int x, int y){
//        return;
//    }
    public SudokuRow getRow(int y){
        SudokuRow row = new SudokuRow();
        for(int i=0;i<9;i++){
            row.setFieldValue(board[y][i].getFieldValue());
        }
        return row;
    }
    public SudokuColumn getColumn(int x){
        SudokuColumn column = new SudokuColumn();
        for(int i=0;i<9;i++){
            column.setFieldValue(board[i][x].getFieldValue());
        }
        return column;
    }

    private final SudokuSolver sudokuSolver;

    SudokuBoard(SudokuSolver solver) {
        sudokuSolver = solver;
    }

    public int get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, int value) {
        board[x][y] = value;
    }

    public void solveGame() {
        sudokuSolver.solve(this);
        // checkBoard();
    }

    /* public boolean checkBoard() {     //to moglo byc prywatne i wywolywane po solviez

        for (int c = 0; c < 9; c++) {
            for (int r = 0; r < 9; r++) {

                for (int i = r + 1; i < 9; i++) {
                    if (board[c][r] == board[c][i]) {
                        return false;
                    }
                }

                for (int i = c + 1; i < 9; i++) {
                    if (board[c][r] == board[i][r]) {
                        return false;
                    }
                }

                int posCol = (c / 3) * 3;
                int posRow = (r / 3) * 3;
                int num = board[c][r];

                for (int i = posCol; i < 3 + posCol; i++) {
                    for (int j = posRow; j < 3 + posRow; j++) {
                        if (i == c && j == r) {
                            continue;
                        }
                        if (board[i][j] == num) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }*/
}