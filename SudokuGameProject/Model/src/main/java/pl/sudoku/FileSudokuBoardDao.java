package pl.sudoku;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import pl.sudoku.exception.WriteReadException;


public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String name;

    public FileSudokuBoardDao(String name) {
        this.name = name;

    }

    @Override
    public SudokuBoard read() throws WriteReadException {
        SudokuBoard obj = null;
        try (FileInputStream fileInputStream = new FileInputStream(name);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
            obj = (SudokuBoard) objectInputStream.readObject();
            return obj;
        } catch (ClassNotFoundException | IOException e) {
            throw new WriteReadException(e);
        }
    }


    @Override
    public void write(SudokuBoard obj) throws WriteReadException {

        try (FileOutputStream fileOutputStream = new FileOutputStream(name);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            throw new WriteReadException(e);
        }

    }

    @Override
    public void close() {
    }

}
