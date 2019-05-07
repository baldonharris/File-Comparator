package com.company;

import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        FCFileReader fr1 = new FCFileReader("C:\\Users\\Harris C. Baldon\\Downloads\\Mock\\MOCK_DATA30cols.csv");
        FCFileReader fr2 = new FCFileReader("C:\\Users\\Harris C. Baldon\\Downloads\\Mock\\MOCK_DATA30cols1.csv");

        try {
            long startTime = System.nanoTime();
            FileComparator fc = new FileComparator(fr1, fr2);
            fc.compare();
            long endTime = System.nanoTime();
            double duration = (double)(endTime - startTime) / 1_000_000_000.0;

            System.out.println("MAIN: Time elapsed: " + duration);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
