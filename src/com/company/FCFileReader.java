package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

class FCFileReader
{
    private File file;
    private HashMap<String, ArrayList<String>> data;
    private String[] columnNames;
    private int rowCount;

    FCFileReader(String fileName)
    {
        this.file = new File(fileName);

        this.readFile();
    }

    public String[] getColumnNames() {
        return this.columnNames;
    }

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

    private void readFile()
    {
        try {
            this.data = new HashMap<>();
            this.columnNames = null;

            FileReader fr = new FileReader(this.file);
            BufferedReader br = new BufferedReader(fr);
            String[] lineData = null;
            String line;

            while (( line = br.readLine() ) != null) {
                lineData = line.split(",");

                if (this.columnNames != null) {
                    this.rowCount++;
                    for (int i=0; i<lineData.length; i++) {
                        this.addToColumn(this.columnNames[i], lineData[i]);
                    }
                }

                this.columnNames = this.columnNames == null ? lineData : this.columnNames;

                if (line.isEmpty()) {
                    this.rowCount--;
                }
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
