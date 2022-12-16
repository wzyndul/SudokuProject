package pl.comp.viewproject;

import pl.sudoku.SudokuBoard;

import java.util.ArrayList;
import java.util.Collections;

public enum Level {
    EASY(30),
    MEDIUM(45),
    HARD(60);
    private final int value;

    Level(int x) {
        value = x;
    }

    public int getValue() {
        return value;
    }


    public void removeFields(Level level, SudokuBoard sudokuBoard) {   //tutaj bedzie juz clone
        int howManyToRemove = level.getValue();                        //(??????????)
        ArrayList<Integer> list = new ArrayList();
        for (int i = 1; i <= 9; i++) {
            list.add(i);
        }
        while (howManyToRemove > 0) {
            Collections.shuffle(list);
            int x = list.get(0);
            Collections.shuffle(list);
            int y = list.get(0);
            if (sudokuBoard.get(x, y) != 0) {
                sudokuBoard.set(x, y, 0);
                howManyToRemove--;
            }
        }
    }
}
