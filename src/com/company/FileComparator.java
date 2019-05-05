package com.company;

public class FileComparator
{
    public static void main(String[] args)
    {
        FCFileReader fr1 = new FCFileReader("C:\\Users\\Colita C. Baldon\\Downloads\\MOCK_DATA.csv");

        System.out.println(fr1.getDataByColumnAndRow("ip_address", 70));
        System.out.println(fr1.getRowCount());
    }
}
