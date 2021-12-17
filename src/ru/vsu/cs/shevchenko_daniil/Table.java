package ru.vsu.cs.shevchenko_daniil;


import ru.vsu.cs.shevchenko_daniil.utils.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Table extends JFrame {
    private JTable inTable;
    private JButton loadButton;
    private JButton solveButton;
    private JTable outTable;
    private JButton saveButton;
    private JPanel MainPanel;
    private JButton rectangleCheckButton;
    private JTextField rectangleCheckField;
    private int[][] tempArray;

    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;
    private final JMenuBar menuBarMain;
    private final JMenu menuLookAndFeel;


    public Table() {
        this.setTitle("Table");
        this.setContentPane(MainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(inTable, 40, true, true, true, true);
        JTableUtils.initJTableForArray(outTable, 40, true, true, true, true);
        inTable.setRowHeight(25);
        outTable.setRowHeight(25);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        JTableUtils.writeArrayToJTable(inTable, new int[][]{
                {0, 1, 2, 3, 4},
                {5, 6, 7, 8, 9}
        });

        this.pack();

        loadButton.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(MainPanel) == JFileChooser.APPROVE_OPTION) {
                    int[][] matrix = ArrayUtils.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                    tempArray = matrix;
                    JTableUtils.writeArrayToJTable(inTable, matrix);
                }
            } catch (Exception a) {
                SwingUtils.showErrorMessageBox(a);
            }
        });

        saveButton.addActionListener(actionEvent -> {
            try {
                if (fileChooserSave.showSaveDialog(MainPanel) == JFileChooser.APPROVE_OPTION) {
                    int[][] matrix = JTableUtils.readIntMatrixFromJTable(outTable);
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    ArrayUtils.writeArrayToFile(file, matrix);
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String temp = rectangleCheckField.getText();
                    if (temp.equals("The array is rectangular")) {
                        int[][] matrix = JTableUtils.readIntMatrixFromJTable(inTable);
                        matrix = arrayProcessing.arrayZeroing(matrix);
                        JTableUtils.writeArrayToJTable(outTable, matrix);
                    } else if (temp.equals("The array is not rectangular")) {
                        rectangleCheckField.setText("The program cannot handle an invalid array. Load and try to process another one.");
                    } else if (temp.equals("The program cannot handle an invalid array. Load and try to process another one.") || temp.equals("The array was not checked")) {
                        rectangleCheckField.setText("First check the array");
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        rectangleCheckButton.addActionListener(e ->

        {
            try {
                    rectangleCheckField.setText(arrayProcessing.rectangleCheck(tempArray));
            } catch (Exception a) {
                SwingUtils.showErrorMessageBox(a);
            }
        });


    }
}