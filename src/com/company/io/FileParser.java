package com.company.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class FileParser
{
    private String fileName;
    private File file;
    private HashMap<String, ArrayList<String>> data;
    private String[] columnNames;
    private int rowCount;

    public FileParser(String fileName) throws IOException
    {
        this.fileName = fileName;
        this.file = new File(fileName);
        this.readFile();
    }

    public String[] getColumnNames() { return this.columnNames; }

    public int getRowCount() { return this.rowCount; }

    public int getColumnCount() { return this.columnNames.length; }

    public String getDataByColumnAndRow(String column, int row) throws IOException, NullPointerException
    {
        String line;
        String[] rowData;
        int columnIndex = 0;
        try (Stream<String> lines = Files.lines(Paths.get(this.fileName))) {
            line = lines.skip(row).findFirst().get();
        }

        rowData = explodeString(line);

        for (int c = 0; c < this.columnNames.length; c++) {
            if (this.columnNames[c].equals(column)) {
                columnIndex = c;
                break;
            }
        }

        return rowData[columnIndex];

//        BufferedReader tempBr = new BufferedReader(new FileReader(this.file));
//        String[] rowData;
//        int columnIndex = 0;
//
//        for (int r = 0; r < (row + 1); r++) {
//            tempBr.readLine();
//        }
//
//        rowData = explodeString(tempBr.readLine());
//
//        for (int c = 0; c < this.columnNames.length; c++) {
//            if (this.columnNames[c].equals(column)) {
//                columnIndex = c;
//                break;
//            }
//        }
//
//        tempBr.close();
//
//        return rowData[columnIndex];
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
            } else {
                this.columnNames = lineData;
            }
        }

        br.close();

        System.out.println("FileParser: Reading of file success.\n\n");
    }
}
