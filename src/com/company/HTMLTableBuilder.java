package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HTMLTableBuilder
{
    private static FileWriter writer;
    private static String fileName = "result.html";

    HTMLTableBuilder(String[] headers) throws IOException
    {
        File resultFile = new File(fileName);
        writer = new FileWriter(resultFile);

        resultFile.createNewFile();

        StringBuilder sb = new StringBuilder();

        sb.append("<html>");
        sb.append("<style>table, th, td { border: 1px solid #000; border-collapse: collapse; }</style>");
        sb.append("<body><table><thead><tr>");
        for (String header : headers) {
            sb.append("<th>");
            sb.append(header);
            sb.append("</th>");
        }
        sb.append("</tr></thead><tbody>");
        writer.write(sb.toString());
    }

    public void insertRow(ArrayList<String> row) throws IOException
    {
        StringBuilder sb = new StringBuilder();

        sb.append("<tr>");
        for (String d : row) {
            String[] columnIndicatorValue = d.split(":");

            sb.append("<td");
            if (columnIndicatorValue[1].equals("highlight")) {
                sb.append(" style=\"background-color: yellow;\"");
            }

            sb.append(">");
            sb.append(columnIndicatorValue[2]);
            sb.append("</td>");
        }

        sb.append("</td></tr>");
        writer.write(sb.toString());
    }

    public HTMLTableBuilder generate() throws IOException
    {
        StringBuilder sb = new StringBuilder();

        sb.append("</tbody></table></body></html>");
        writer.write(sb.toString());
        writer.close();

        return this;
    }

    public void launch() throws IOException
    {
        Runtime.getRuntime().exec("cmd /c start " + fileName);
    }
}
