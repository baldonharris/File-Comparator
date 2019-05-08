package com.company.io;

import java.io.IOException;
import java.util.ArrayList;
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
        this.builder = new HTMLTableBuilder(columns);
    }

    public FileComparator(FileParser fr1, FileParser fr2) throws IOException
    {
        this(fr1, fr2, fr1.getColumnNames());
    }

    public void compare() throws IOException
    {
        if (file1.getColumnCount() != file2.getColumnCount()) {
            throw new UnsupportedOperationException("The number of columns does not match.");
        }

        if (!Arrays.equals(file1.getColumnNames(), file2.getColumnNames())) {
            throw new UnsupportedOperationException("Column names does not match.");
        }

        ArrayList<String> dataRow;
        String columnValue1;
        String columnValue2;
        String key;

        for (int row=0; row<file1.getRowCount(); row++) {
            dataRow = new ArrayList<>();

            for (String column : this.columns) {
                columnValue1 = file1.getDataByColumnAndRow(column, row);
                columnValue2 = file2.getDataByColumnAndRow(column, row);
                key = column + (columnValue1.equals(columnValue2) ? ":normal:" : ":highlight:");

                dataRow.add(key + columnValue1);
            }

            this.builder.insertRow(dataRow);
        }

        this.builder.generate().launch();
    }
}
