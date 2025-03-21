package com.anurag.tools.webshell.service.linux;

import java.io.BufferedReader;
import java.io.IOException;

public class ParseDfOutput {

    public static void parse(BufferedReader reader, StringBuilder output) throws IOException {
        String line;
        boolean isFirstLine = true;

        while ((line = reader.readLine()) != null) {
            if (isFirstLine) { // Skip the first header line
                isFirstLine = false;
                continue;
            }

            String[] columns = line.trim().split("\\s+");
            if (columns.length < 6) continue; // Ignore malformed lines

            output.append("<tr>");
            for (String column : columns) {
                output.append("<td class='text-start'>" + column + "</td>");
            }
            output.append("</tr>");
        }
    }
}
