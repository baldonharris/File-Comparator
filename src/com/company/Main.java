package com.company;

import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        FCFileReader fr1 = new FCFileReader("C:\\Users\\Harris C. Baldon\\Downloads\\Mock\\MOCK_DATA.csv");
        FCFileReader fr2 = new FCFileReader("C:\\Users\\Harris C. Baldon\\Downloads\\Mock\\MOCK_DATA2.csv");

        try {
            FileComparator fc = new FileComparator(fr1, fr2);
            fc.compare();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
