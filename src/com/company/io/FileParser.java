package com.company.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileParser
{
    private File file;
    private HashMap<String, ArrayList<String>> data;
    private String[] columnNames;
    private int rowCount;

    public FileParser(String fileName) throws IOException
    {
        this.file = new File(fileName);
        this.readFile();
    }

    public String[] getColumnNames() { return this.columnNames; }

    public int getRowCount() { return this.rowCount; }

    public int getColumnCount() { return this.columnNames.length; }

    public String getDataByColumnAndRow(String column, int row)
    {
        try {
            return this.data.get(column).get(row);
        } catch (Exception e) {
            return null;
        }
    }

    private void addToColumn(String column, String value)
    {
        ArrayList<String> temp = this.data.containsKey(column) ? this.data.get(column) : new ArrayList<>();

        temp.add(value);
        this.data.put(column, temp);
    }

    private String[] explodeString(String line)
    {
        return line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    }

    private void readFile() throws IOException
    {
        System.out.println("FileParser: Attempting to read " + this.file.getName());
        this.data = new HashMap<>();
        this.columnNames = null;

        FileReader fr = new FileReader(this.file);
        BufferedReader br = new BufferedReader(fr);
        String[] lineData;
        String line;

        while (( line = br.readLine() ) != null) {
            lineData = explodeString(line);

            if (this.columnNames != null && !line.isEmpty()) {
                this.rowCount++;
                for (int i=0; i<lineData.length; i++) {
                    this.addToColumn(this.columnNames[i], lineData[i]);
                }
            } else {
                this.columnNames = lineData;
            }
        }

        System.out.println("FileParser: Reading of file success.\n\n");
    }
}
