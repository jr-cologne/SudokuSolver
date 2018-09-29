package de.jrcologne;

public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        String[][] sudokuArr = Sudoku.readFromFile(args[0]);

        if (sudokuArr == null) {
            System.out.println("Failed to read file!");
            System.exit(0);
        }

        Sudoku sudoku = (new Sudoku()).init(sudokuArr);

        sudoku.print();
        System.out.println();
        System.out.println("Solving Sudoku ...");

        sudoku = (new SudokuSolver()).solve(sudoku);

        System.out.println();
        sudoku.print();

        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        System.out.println();
        System.out.println("Execution time: " + duration + "ms");
    }

}
