public class SudokuBoardDaoFactory {
    public Dao<SudokuBoard> getFileDao(String fileName) {
        Dao<SudokuBoard> dao = new FileSudokuBoardDao(fileName);
        return dao;
    }

}