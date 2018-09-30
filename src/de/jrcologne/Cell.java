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
 * Cell.java
 *
 * The class which represents a Sudoku cell.
 *
 */

package de.jrcologne;

class Cell {

    private String value;
    private boolean fixed;

    Cell() {
        this.value = " ";
        this.fixed = false;
    }

    String getValue() {
        return this.value;
    }

    void setValue(String value) {
        if (!this.fixed) {
            this.value = value;
        }
    }

    void fix() {
        this.fixed = true;
    }

}
