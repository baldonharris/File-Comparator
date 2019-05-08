package com.company;

import com.company.io.FileComparator;
import com.company.io.FileParser;

import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        FileParser fr1 = new FileParser("C:\\Users\\Harris C. Baldon\\Downloads\\Mock\\MOCK_DATA30cols.csv");
        FileParser fr2 = new FileParser("C:\\Users\\Harris C. Baldon\\Downloads\\Mock\\MOCK_DATA30cols1.csv");

        try {
            long startTime = System.nanoTime();
            long endTime;
            double duration;

            FileComparator fc = new FileComparator(fr1, fr2);
            fc.compare();

            endTime = System.nanoTime();
            duration = (double)(endTime - startTime) / 1_000_000_000.0;

            System.out.println("MAIN: Time elapsed: " + duration);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
