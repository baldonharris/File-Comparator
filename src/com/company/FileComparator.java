package com.company;

import java.io.*;
import java.util.Arrays;

class FileComparator
{
    private static FileWriter writer;
    private FCFileReader file1;
    private FCFileReader file2;
    private String[] columns;

    FileComparator(FCFileReader fr1, FCFileReader fr2, String[] columns) throws IOException
    {
        this.file1 = fr1;
        this.file2 = fr2;
        this.columns = columns;

        File resultFile = new File("test.csv");
        writer = new FileWriter(resultFile);

        resultFile.createNewFile();
        printToFile(String.join(",", this.columns));
    }

    FileComparator(FCFileReader fr1, FCFileReader fr2) throws IOException
    {
        this(fr1, fr2, fr1.getColumnNames());
    }

    private static void printToFile(String content) throws IOException
    {
        StringBuilder sb = new StringBuilder();

        sb.append(content);
        sb.append("\n");

        writer.write(sb.toString());
    }

    public void compare() throws IOException
    {
        if (file1.getColumnCount() != file2.getColumnCount()) {
            throw new UnsupportedOperationException("The number of columns does not match.");
        }

        if (!Arrays.equals(file1.getColumnNames(), file2.getColumnNames())) {
            throw new UnsupportedOperationException("Column names does not match.");
        }

        for (int row=0; row<file1.getRowCount(); row++) {
            StringBuilder sbb = new StringBuilder();

            for (String column : this.columns) {
                String columnValue1 = file1.getDataByColumnAndRow(column, row);
                String columnValue2 = file2.getDataByColumnAndRow(column, row);

                if (!columnValue1.equals(columnValue2)) {
                    sbb.append(columnValue1);
                }

                sbb.append(",");
            }

            if (!sbb.toString().replace(",", "").isEmpty()) {
                printToFile(sbb.toString());
            }
        }

        writer.close();
    }
}
