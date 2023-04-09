package pl.comp.viewproject;

import pl.sudoku.SudokuBoard;
import pl.sudoku.exception.SudokuBoardException;

import java.util.ArrayList;
import java.util.Collections;


public class DeletingFields {
    public void removeFields(Level level, SudokuBoard sudokuBoard) throws SudokuBoardException {
        int howManyToRemove = 0;
        switch (level) {
            case EASY: {
                howManyToRemove = 2;
                break;
            }
            case MEDIUM: {
                howManyToRemove = 40;
                break;
            }
            case HARD: {
                howManyToRemove = 60;
                break;

            }

        }


        ArrayList<Integer> list = new ArrayList();
        for (int i = 0; i < 9; i++) {
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


