package ru.vsu.cs.shevchenko_daniil;


public class arrayProcessing {

    public static String rectangleCheck(int[][] matrix) {
        boolean rectangle = true;

        if (matrix.length < 2) {
            return "The array is not rectangular";
        }

        for (int row = 1; row < matrix.length; row++) {
            if (matrix[row - 1].length == matrix[row].length) {
                continue;
            } else {
                return "The array is not rectangular";
            }
        }
        return "The array is rectangular";
    }

    public static int[] rowCheck(int[][] matrix) {
        int[] auxiliaryArray = new int[matrix.length + 1];

        for (int r = 0; r < matrix.length; r++) {
            boolean similarElements = true;
            for (int c = 1; c < matrix[r].length; c++) {
                if (matrix[r][c] != matrix[r][c - 1]) {
                    similarElements = false;
                    break;
                }
            }
            if (similarElements) {
                auxiliaryArray[r] = 1;
                auxiliaryArray[matrix.length]++;
            }
        }
        return auxiliaryArray;
    }

    public static int[] columnCheck(int[][] matrix) {
        int[] auxiliaryArray = new int[matrix[0].length + 1];

        for (int c = 0; c < matrix[0].length; c++) {
            boolean columnFlag = false;
            for (int r = 1; r < matrix.length; r++) {

                if (matrix[r][c] != matrix[r - 1][c]) {
                    columnFlag = true;
                    break;
                }
            }

            if (columnFlag == false) {
                auxiliaryArray[auxiliaryArray.length - 1]++;
                auxiliaryArray[c] = 1;
            }

        }
        return auxiliaryArray;
    }

    public static int[][] createAuxiliaryArray(int[][] matrix) {
        int[][] newArray = new int[matrix.length][matrix[0].length];
        int[] rowAuxiliaryArray = rowCheck(matrix);
        int[] columnAuxiliaryArray = columnCheck(matrix);

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {

                if (rowAuxiliaryArray[r] == 1 || columnAuxiliaryArray[c] == 1) {
                    newArray[r][c] = 1;
                }
            }
        }
        return newArray;
    }

    public static int[][] arrayZeroing(int[][] matrix) {
        int[] auxiliaryRowArray = rowCheck(matrix);
        int[] auxiliaryColumnArray = columnCheck(matrix);
        int[][] auxiliaryArray = createAuxiliaryArray(matrix);

        int[][] newArray = new int[matrix.length - auxiliaryRowArray[auxiliaryRowArray.length-1]][matrix[0].length - auxiliaryColumnArray[auxiliaryColumnArray.length-1]];

        int newArrayRowCounter = 0;
        int newArrayColumnCounter = 0;

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {

                if (auxiliaryArray[r][c] == 0) {
                    newArray[newArrayRowCounter][newArrayColumnCounter] = matrix[r][c];
                    newArrayColumnCounter++;
                }

                if (newArrayColumnCounter == newArray[0].length) {
                    newArrayColumnCounter = 0;
                    newArrayRowCounter++;
                }

            }
        }
        return newArray;
    }
}
