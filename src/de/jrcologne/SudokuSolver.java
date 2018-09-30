/**
 * A simple command-line application for solving Sudokus.
 *
 * @author JR Cologne <kontakt@jr-cologne.de>
 * @copyright 2018 JR Cologne
 * @license MIT (see LICENSE file or https://github.com/jr-cologne/SudokuSolver/blob/master/LICENSE for more info)
 * @version v1.0.0
 * @link https://github.com/jr-cologne/SudokuSolver GitHub Repository
 *
 * ________________________________________________________________________________
 *
 * SudokuSolver.java
 *
 * The class which is responsible for solving the Sudoku.
 *
 */

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
