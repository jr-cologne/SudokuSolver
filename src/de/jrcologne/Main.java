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
 * Main.java
 *
 * The main class which is the entry point into the application.
 *
 */

package de.jrcologne;

public class Main {

    public static void main(String[] args) {
        String[][] sudokuArr = Sudoku.readFromFile(args[0]);

        if (sudokuArr == null) {
            System.out.println("Failed to read file!");
            return;
        }

        Sudoku sudoku = (new Sudoku()).init(sudokuArr);

        if (!sudoku.validate()) {
            System.out.println("Sudoku is not valid!");
            return;
        }

        sudoku = (new SudokuSolver()).solve(sudoku);

        sudoku.print();
    }

}
