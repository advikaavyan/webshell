package com.anurag.tools.webshell.service.linux;

import java.io.BufferedReader;
import java.io.IOException;

public class ParseNetstatOutput {

    public static void parse(BufferedReader reader, StringBuilder output) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Proto")) continue; // Skip headers
            String[] columns = line.trim().split("\\s+");
            if (columns.length < 7) continue;

            output.append("<tr>");
            for (String column : columns) {
                output.append("<td class='text-start'>" + column + "</td>");
            }
            output.append("</tr>");
        }
    }
}
