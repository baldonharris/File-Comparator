package com.company.io;

import java.io.IOException;
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
        System.out.println("Preparing result file.");
        this.builder = new HTMLTableBuilder(columns);
    }

    public FileComparator(FileParser fr1, FileParser fr2) throws IOException
    {
        this(fr1, fr2, fr1.getColumnNames());
    }

    public void compare() throws IOException
    {
        System.out.println("Checking if number of columns matched.");
        if (file1.getColumnCount() != file2.getColumnCount()) {
            throw new UnsupportedOperationException("The number of columns does not match.");
        }

        System.out.println("Checking if column names matched.");
        if (!Arrays.equals(file1.getColumnNames(), file2.getColumnNames())) {
            throw new UnsupportedOperationException("Column names does not match.");
        }

        String columnValue1;
        String columnValue2;

        System.out.println("Comparing values per row and column.");
        for (int row=0; row<file1.getRowCount(); row++) {
            for (String column : this.columns) {
                columnValue1 = file1.getDataByColumnAndRow(column, row);
                columnValue2 = file2.getDataByColumnAndRow(column, row);

                this.builder.insertColumn(columnValue1, !columnValue1.equals(columnValue2));
            }

            this.builder.insertRow();
        }

        System.out.println("\n\nDone.\nFinalizing result file.");
        this.builder.generate().launch();
    }
}
