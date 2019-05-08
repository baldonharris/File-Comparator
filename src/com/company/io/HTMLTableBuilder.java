package com.company.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HTMLTableBuilder
{
    private static FileWriter writer;
    private static String fileName = "result.html";
    private StringBuilder row;

    public HTMLTableBuilder(String[] headers) throws IOException
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

    public void insertColumn(String column, boolean toHighlight)
    {
        if (this.row == null) {
            this.row = new StringBuilder();
            this.row.append("<tr>");
        }

        this.row.append("<td");
        if (toHighlight) {
            this.row.append(" style=\"background-color: yellow;\"");
        }

        this.row.append(">");
        this.row.append(column);
        this.row.append("</td>");
    }

    public void insertRow() throws IOException
    {
        this.row.append("</tr>");
        writer.write(this.row.toString());

        this.row = null;
    }

    public HTMLTableBuilder generate() throws IOException
    {
        writer.write("</tbody></table></body></html>");
        writer.close();

        return this;
    }

    public void launch() throws IOException
    {
        Runtime.getRuntime().exec("cmd /c start " + fileName);
    }
}
