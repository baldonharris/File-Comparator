package com.company;

import com.company.io.FileComparator;
import com.company.io.FileParser;

import javax.swing.JOptionPane;
import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        try {
            long startTime;
            long endTime;
            double duration;

            String fileName1 = JOptionPane.showInputDialog("Absolute path of File 1");
            String fileName2 = JOptionPane.showInputDialog("Absolute path of File 2");
            String columnName = JOptionPane.showInputDialog("Columns to compare in comma separated format");

            // C:\Users\Harris C. Baldon\Downloads\Mock\MOCK_DATA.csv
            // C:\Users\Harris C. Baldon\Downloads\Mock\MOCK_DATA2.csv

            startTime = System.nanoTime();
            FileParser fr1 = new FileParser(fileName1);
            FileParser fr2 = new FileParser(fileName2);

            FileComparator fc = new FileComparator(fr1, fr2, columnName.isEmpty() ? fr1.getColumnNames() : columnName.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"));
            fc.compare().generate().launch();

            endTime = System.nanoTime();
            duration = (double)(endTime - startTime) / 1_000_000_000.0;

            System.out.println("\n\nProcessed: " + fr1.getRowCount() + " rows.");
            System.out.println("Time elapsed: " + duration + "s");
        } catch (IOException|UnsupportedOperationException e) {
            System.out.println("\n\nAborting.");
            System.out.println("Error: " + e.getMessage());
        }
    }
}
