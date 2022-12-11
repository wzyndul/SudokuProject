package pl.comp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String name;

    FileSudokuBoardDao(String name) {
        this.name = name;

    }

    @Override
    public SudokuBoard read() {
        SudokuBoard obj = null;
        try (FileInputStream fileInputStream = new FileInputStream(name);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            obj = (SudokuBoard) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }


    @Override
    public void write(SudokuBoard obj) {

        try (FileOutputStream fileOutputStream = new FileOutputStream(name);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void close()  {
    }

}
