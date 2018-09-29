package de.jrcologne;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Sudoku {

    private Cell[][] board;

    Sudoku() {
        this.board = null;
    }

    static String[][] readFromFile(String file) {
        String[][] arr = new String[9][9];

        try {
            BufferedReader in = new BufferedReader(new FileReader(file));

            String line;
            int i = 0;

            while ((line = in.readLine()) != null) {
                arr[i] = line.split("");

                i++;
            }

            for (i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (arr[i][j] == null) {
                        return null;
                    }
                }
            }

            return arr;
        } catch (IOException e) {
            return null;
        }
    }

    Sudoku init(String[][] sudoku) {
        this.board = new Cell[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Cell cell = new Cell();

                cell.setValue(sudoku[i][j]);

                if (!sudoku[i][j].equals(" ")) {
                    cell.fix();
                }

                this.board[i][j] = cell;
            }
        }

        return this;
    }

    void print() {
        for (int i = 0; i < 9; i ++) {
            System.out.println("+---+---+---+---+---+---+---+---+---+");

            for (int j = 0; j < 9; j++) {
                System.out.print("| " + this.board[i][j].getValue() + " ");
            }

            System.out.println("|");
        }
        System.out.println("+---+---+---+---+---+---+---+---+---+");
    }

    void setCellValue(int x, int y, String value) {
        this.board[y][x].setValue(value);
    }

    boolean check() {
        for (int i = 0; i < 9; i++) {
            if ( !checkColumn(i) || !checkRow(i) ) {
                return false;
            }

            if (i % 3 == 0) {
                if ( !checkBox(0, i) || !checkBox(3, i) || !checkBox(6, i) ) {
                    return false;
                }
            }
        }

        return true;
    }

    Map<String, Integer> getVacantCell() {
        Map<String, Integer> vacantCell = new HashMap<String, Integer>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.board[i][j].getValue().equals(" ")) {
                    vacantCell.put("x", j);
                    vacantCell.put("y", i);

                    return vacantCell;
                }
            }
        }

        return vacantCell;
    }

    boolean[] getPossibleCellValues(int x, int y) {
        boolean[] possibilities = new boolean[9];

        for (int i = 0; i < 9; i++) {
            String num = Integer.toString(i + 1);

            possibilities[i] = !inStack(getColumnNumbers(x), num) && !inStack(getRowNumbers(y), num) && !inStack(getBoxNumbers(x, y), num);
        }

        return possibilities;
    }

    boolean[][][] getPossibilities() {
        boolean[][][] possibilities = new boolean[9][9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                possibilities[i][j] = getPossibleCellValues(j, i);
            }
        }

        return possibilities;
    }

    private Stack<String> getColumnNumbers(int x) {
        Stack<String> columnNumbers = new Stack<String>();

        for (int i = 0; i < 9; i++) {
            String val = this.board[i][x].getValue();

            if (!val.equals(" ")) {
                columnNumbers.push(val);
            }
        }

        return columnNumbers;
    }

    private Stack<String> getRowNumbers(int y) {
        Stack<String> rowNumbers = new Stack<String>();

        for (int i = 0; i < 9; i++) {
            String val = this.board[y][i].getValue();

            if (!val.equals(" ")) {
                rowNumbers.push(val);
            }
        }

        return rowNumbers;
    }

    private Stack<String> getBoxNumbers(int x, int y) {
        x -= x % 3;
        y -= y % 3;

        Stack<String> boxNumbers = new Stack<String>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String val = this.board[y + i][x + j].getValue();

                if (!val.equals(" ")) {
                    boxNumbers.push(val);
                }
            }
        }

        return boxNumbers;
    }

    private boolean checkColumn(int x) {
        Stack<String> columnNumbers = new Stack<String>();

        for (int i = 0; i < 9; i++) {
            String val = this.board[i][x].getValue();

            if ( val.equals(" ") || inStack(columnNumbers, val) ) {
                return false;
            }

            columnNumbers.push(val);
        }

        return true;
    }

    private boolean checkRow(int y) {
        Stack<String> rowNumbers = new Stack<String>();

        for (int i = 0; i < 9; i++) {
            String val = this.board[y][i].getValue();

            if ( val.equals(" ") || inStack(rowNumbers, val) ) {
                return false;
            }

            rowNumbers.push(val);
        }

        return true;
    }

    private boolean checkBox(int x, int y) {
        Stack<String> boxNumbers = new Stack<String>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String val = this.board[y + i][x + j].getValue();

                if ( val.equals(" ") || inStack(boxNumbers, val) ) {
                    return false;
                }

                boxNumbers.push(val);
            }
        }

        return true;
    }

    private boolean inStack(Stack stack, Object o) {
        return stack.search(o) != -1;
    }

}
