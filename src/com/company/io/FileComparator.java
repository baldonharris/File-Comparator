package com.company.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class FileComparator
{
    private HTMLTableBuilder builder;
    private FileParser file1;
    private FileParser file2;
    private String[] columns;

    public FileComparator(FileParser fr1, FileParser fr2, String[] columns) throws IOException
    {
        this.file1 = fr1;
        this.file2 = fr2;
        this.columns = columns;
        System.out.println("FileComparator: Preparing result file.");
        this.builder = new HTMLTableBuilder(columns);
    }

    public FileComparator(FileParser fr1, FileParser fr2) throws IOException
    {
        this(fr1, fr2, fr1.getColumnNames());
    }

    private String[] explodeString(String line)
    {
        return line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    }

    public HTMLTableBuilder compare() throws IOException
    {
        System.out.println("FileComparator: Checking if columns to compare exists.");
        for (String column : this.columns) {
            if (!Arrays.asList(file1.getColumnNames()).contains(column)){
                throw new UnsupportedEncodingException("Unknown column to compare.");
            }

            if (!Arrays.asList(file2.getColumnNames()).contains(column)){
                throw new UnsupportedEncodingException("Unknown column to compare.");
            }
        }

        System.out.println("FileComparator: Checking if number of columns matched.");
        if (file1.getColumnCount() != file2.getColumnCount()) {
            throw new UnsupportedOperationException("The number of columns does not match.");
        }

        System.out.println("FileComparator: Checking if column names matched.");
        if (!Arrays.equals(file1.getColumnNames(), file2.getColumnNames())) {
            throw new UnsupportedOperationException("Column names does not match.");
        }

        System.out.println("FileComparator: Comparing values per row and column.");
        int columnNameIndex1;
        int columnNameIndex2;
        BufferedReader br1 = file1.getBufferedReader();
        BufferedReader br2 = file2.getBufferedReader();
        String line1, line2;
        String[] lineData1, lineData2;
        int rowCounter = 0;

        while (( line1 = br1.readLine() ) != null && ( line2 = br2.readLine() ) != null) {
            rowCounter++;

            lineData1 = explodeString(line1);
            lineData2 = explodeString(line2);

            for (String column : this.columns) {
                columnNameIndex1 = file1.getColumnIndex(column);
                columnNameIndex2 = file2.getColumnIndex(column);

                System.out.println(rowCounter + " " + lineData1[columnNameIndex1] + " " + lineData2[columnNameIndex2]);
                this.builder.insertColumn(lineData1[columnNameIndex1], !lineData1[columnNameIndex1].equals(lineData2[columnNameIndex2]));
            }

            file1.incrementRowCount();
            file2.incrementRowCount();

            this.builder.insertRow();
        }

        System.out.println("\n\nFileComparator: Done.");
        System.out.println("FileComparator: Finalizing result file.");

        return this.builder;
    }
}
