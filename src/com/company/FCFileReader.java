package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FCFileReader {
    private HashMap<String, ArrayList<String>> data;
    private String[] columnNames;
    private int rowCount;

    private String fileName;
    private File file;

    FCFileReader(String fileName) {
        this.fileName = fileName;
        this.file = new File(this.fileName);

        this.rowCount = this.readFile();
    }

    public String[] getColumnNames() {
        return this.columnNames;
    }

    public HashMap<String, ArrayList<String>> getData() {
        return this.data;
    }

    public int getRowCount()
    {
        return this.rowCount;
    }

    public String getDataByColumnAndRow(String column, int row)
    {
        try {
            return this.data.get(column).get(row);
        } catch (Exception e) {
            return null;
        }
    }

    public void addToColumn(String column, String value)
    {
        ArrayList<String> temp = this.data.containsKey(column) ? this.data.get(column) : new ArrayList<>();

        temp.add(value);
        this.data.put(column, temp);
    }

    private int readFile()
    {
        int rowCounter = 0;

        try {
            this.data = new HashMap<>();
            this.columnNames = null;

            FileReader fr = new FileReader(this.file);
            BufferedReader br = new BufferedReader(fr);
            String[] lineData = null;
            String line;
            while (( line = br.readLine() ) != null) {
                lineData = line.split(",");
                this.columnNames = this.columnNames == null ? lineData : this.columnNames;

                for (int i=0; i<lineData.length; i++) {
                    this.addToColumn(this.columnNames[i], lineData[i]);
                }

                rowCounter++;
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        return rowCounter;
    }
}
