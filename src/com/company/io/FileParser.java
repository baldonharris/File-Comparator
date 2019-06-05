package com.company.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileParser
{
    private String fileName;
    private File file;
    private String[] columnNames;
    private int rowCount;
    private BufferedReader br;

    public FileParser(String fileName) throws IOException
    {
        this.fileName = fileName;
        this.file = new File(fileName);
        this.br = new BufferedReader(new FileReader(this.file));
        this.columnNames = explodeString(br.readLine());
    }

    public int getColumnIndex(String columnName)
    {
        int columnIndex = -1;

        for (int i = 0; i < this.columnNames.length; i++) {
            if (this.columnNames[i].equals(columnName)) {
                columnIndex = i;
                break;
            }
        }

        return columnIndex;
    }

    public BufferedReader getBufferedReader()
    {
        return this.br;
    }

    public String[] getColumnNames() { return this.columnNames; }

    public int getRowCount() { return this.rowCount; }

    public void incrementRowCount() { this.rowCount++; }

    public int getColumnCount() { return this.columnNames.length; }

    private String[] explodeString(String line)
    {
        return line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    }
}
