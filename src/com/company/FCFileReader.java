package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FCFileReader
{
    private String fileName;
    private File file;

    FCFileReader(String fileName)
    {
        this.fileName = fileName;
        this.file = new File(this.fileName);
    }

    private void readFile()
    {
        try {
            FileReader fr = new FileReader(this.file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
