package ru.vsu.cs.shevchenko_daniil;

import ru.vsu.cs.shevchenko_daniil.utils.ArrayUtils;

import java.io.FileNotFoundException;

public class ConsoleVersion {

    public static void runInCommandLineMode(String[] args) throws FileNotFoundException {
        ArgsParser argsParser = new ArgsParser(args);
        String inputFilePath = argsParser.getArgumentValue("-i", "--input-file");
        String outputFilePath = argsParser.getArgumentValue("-o", "--output-file");

        int[][] matrix = ArrayUtils.readIntArray2FromFile(inputFilePath);
        printArray2.outArray2(matrix);

        if (arrayProcessing.rectangleCheck(matrix) == "Массив не является прямоугольным") {
            System.out.println("Массив не является прямоугольным");
        } else System.out.println("Массив является прямоугольным");

        matrix = arrayProcessing.arrayZeroing(matrix);
        System.out.println();

        printArray2.outArray2(matrix);
        System.out.println();

        ArrayUtils.writeArrayToFile(outputFilePath, matrix);

    }

}
