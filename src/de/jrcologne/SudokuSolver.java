package de.jrcologne;

import java.util.Map;

class SudokuSolver {

    private Sudoku sudoku;
    private boolean solved;

    SudokuSolver() {
        this.sudoku = null;
        this.solved = false;
    }

    Sudoku solve(Sudoku sudoku) {
        this.sudoku = sudoku;

        if (!this.sudoku.check()) {
            solveSudoku();
            return this.sudoku;
        }

        System.out.println();
        System.out.println("Sudoku is already solved!");

        return this.sudoku;
    }

    private void solveSudoku() {
        if (this.sudoku.check()) {
            this.solved = true;
            return;
        }

        Map<String, Integer> vacantCell = this.sudoku.getVacantCell();

        int x = vacantCell.get("x");
        int y = vacantCell.get("y");

        boolean[] possibilities = this.sudoku.getPossibleCellValues(x, y);

        for (int i = 0; i < 9; i++) {
            if (possibilities[i]) {
                fillCell(x, y, Integer.toString(i + 1));
                solveSudoku();

                if (this.solved) {
                    return;
                }
            }
        }

        fillCell(x, y, " ");
    }

    private void fillCell(int x, int y, String val) {
        this.sudoku.setCellValue(x, y, val);
    }

}
