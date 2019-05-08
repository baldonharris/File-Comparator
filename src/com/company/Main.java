package com.company;

import com.company.io.FileComparator;
import com.company.io.FileParser;

import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        FileParser fr1 = new FileParser("C:\\Users\\Harris C. Baldon\\Downloads\\Mock\\1500000.csv");
        FileParser fr2 = new FileParser("C:\\Users\\Harris C. Baldon\\Downloads\\Mock\\1500000-1.csv");

        try {
            long startTime = System.nanoTime();
            long endTime;
            double duration;

            FileComparator fc = new FileComparator(fr1, fr2);
            fc.compare();

            endTime = System.nanoTime();
            duration = (double)(endTime - startTime) / 1_000_000_000.0;

            System.out.println("Processed: " + fr1.getRowCount() + " rows.");
            System.out.println("Time elapsed: " + duration);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
