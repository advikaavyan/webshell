package com.anurag.tools.webshell.service.linux;

import java.io.BufferedReader;
import java.io.IOException;

public class ParsePsEafOutput {

    public static void parse(BufferedReader reader, StringBuilder output) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] columns = line.trim().split("\\s+", 8);
            if (columns.length < 8) continue;

            output.append("<tr>");
            for (String column : columns) {
                output.append("<td class='text-start'>" + column + "</td>");
            }
            output.append("</tr>");
        }
    }
}
