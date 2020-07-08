package implementations;

import java.util.ArrayDeque;
import java.util.Deque;

public class TheMatrix {
    private char[][] matrix;
    private char fillChar;
    private char toBeReplaced;
    private int startRow;
    private int startCol;

    public TheMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {
        this.matrix = matrix;
        this.fillChar = fillChar;
        this.startRow = startRow;
        this.startCol = startCol;
        this.toBeReplaced = this.matrix[this.startRow][this.startCol];
    }

    //solve DFS
    public void solve() {
        Deque<int[]> coordinates = new ArrayDeque<>();
        coordinates.push(new int[]{this.startRow, this.startCol});
        while (!coordinates.isEmpty()) {
            int[] position = coordinates.pop();
            int row = position[0];
            int col = position[1];

            matrix[row][col] = this.fillChar;

            if (isInBounds(row + 1, col) && matrix[row + 1][col] == this.toBeReplaced) {
                coordinates.push(new int[]{row + 1, col});
            }

            if (isInBounds(row, col + 1) && matrix[row][col + 1] == this.toBeReplaced) {
                coordinates.push(new int[]{row, col + 1});
            }

            if (isInBounds(row - 1, col) && matrix[row - 1][col] == this.toBeReplaced) {
                coordinates.push(new int[]{row - 1, col});
            }


            if (isInBounds(row, col - 1) && matrix[row][col - 1] == this.toBeReplaced) {
                coordinates.push(new int[]{row, col - 1});
            }
        }
    }

    public void solveBFS() {
        Deque<int[]> coordinates = new ArrayDeque<>();
        coordinates.offer(new int[]{this.startRow, this.startCol});
        while (!coordinates.isEmpty()) {
            int[] position = coordinates.poll();
            int row = position[0];
            int col = position[1];

            matrix[row][col] = this.fillChar;
            if (isInBounds(row + 1, col) && matrix[row + 1][col] == this.toBeReplaced) {
                coordinates.offer(new int[]{row + 1, col});
            }
            if (isInBounds(row - 1, col) && matrix[row - 1][col] == this.toBeReplaced) {
                coordinates.offer(new int[]{row - 1, col});
            }
            if (isInBounds(row, col + 1) && matrix[row][col + 1] == this.toBeReplaced) {
                coordinates.offer(new int[]{row, col + 1});
            }
            if (isInBounds(row, col - 1) && matrix[row][col - 1] == this.toBeReplaced) {
                coordinates.offer(new int[]{row, col - 1});
            }
        }
    }

    private boolean isInBounds(int row, int col) {
        return !isOutOfBound(row, col);
    }

    public void solveRecursion() {
        fillMatrix(this.startRow, this.startCol);
    }

    private void fillMatrix(int row, int col) {
        if (isOutOfBound(row, col) || this.matrix[row][col] != this.toBeReplaced) {
            return;
        }
        matrix[row][col] = fillChar;

        fillMatrix(row + 1, col);
        fillMatrix(row, col + 1);
        fillMatrix(row - 1, col);
        fillMatrix(row, col - 1);
    }

    private boolean isOutOfBound(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }

    public String toOutputString() {
        StringBuilder matrixElements = new StringBuilder();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrixElements.append(matrix[row][col]);
            }
            matrixElements.append(System.lineSeparator());
        }
        return matrixElements.toString().trim();
    }
}
