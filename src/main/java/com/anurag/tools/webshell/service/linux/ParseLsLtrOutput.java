package com.anurag.tools.webshell.service.linux;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class ParseLsLtrOutput {

    public static void parse(BufferedReader reader, StringBuilder output) throws IOException {
        output.append("<thead class='table-dark'><tr><th>Permissions</th><th>Links</th><th>Owner</th><th>Group</th><th>Size</th><th>Date</th><th>Time</th><th>Filename</th></tr></thead><tbody>");

        String line;
        boolean isFirstLine = true;

        while ((line = reader.readLine()) != null) {
            if (isFirstLine && line.startsWith("total")) { // Skip "total XXX" line
                isFirstLine = false;
                continue;
            }
            isFirstLine = false;

            String[] columns = line.trim().split("\\s+");
            if (columns.length < 9) continue; // Ignore malformed lines

            output.append("<tr>");
            output.append("<td class='text-start'>" + columns[0] + "</td>");  // Permissions
            output.append("<td class='text-start'>" + columns[1] + "</td>");  // Links
            output.append("<td class='text-start'>" + columns[2] + "</td>");  // Owner
            output.append("<td class='text-start'>" + columns[3] + "</td>");  // Group
            output.append("<td class='text-start'>" + columns[4] + "</td>");  // Size
            output.append("<td class='text-start'>" + columns[5] + "</td>");  // Date

            // Extract only `HH:MM:SS` part from `HH:MM:SS.000000000:+0530`
            String fullTime = columns[6];
            String cleanedTime = fullTime.split("\\.")[0]; // Remove everything after the first "."

            output.append("<td class='text-start'>" + cleanedTime + "</td>");  // Cleaned Time

            // Handle filenames with spaces
            String filename = String.join(" ", Arrays.copyOfRange(columns, 8, columns.length));
            String filePath = "/download?file=" + filename; // API endpoint to handle downloads
            getFileName(filePath);
            // Add a hyperlink to download the file
            String cell = "<td class='text-start'>" + getHyperLinkFileName(filename) + "</td>";
            output.append(cell);


            // Handle filenames with spaces
           /* String filename = String.join(" ", Arrays.copyOfRange(columns, 8, columns.length));
            output.append("<td class='text-start'><strong>" + filename + "</strong></td>");*/

            output.append("</tr>");
        }
    }

    public static String getFileName(String filePath) {

        // Find the last occurrence of '/' or '\' (handling both Unix & Windows paths)
        int lastUnixSeparator = filePath.lastIndexOf("/");
        int lastWindowsSeparator = filePath.lastIndexOf("\\");
        int lastSeparator = Math.max(lastUnixSeparator, lastWindowsSeparator);

        // Extract the file name
        String fileName = filePath.substring(lastSeparator + 1);
//      /  System.out.println("File Name: " + fileName);
        return fileName;

    }

    public static String getHyperLinkFileName(String filePath) {
        String aaa = "";
        aaa = "<a href='#' onclick=downloadFile('" + filePath + "')><strong>" + getFileName(filePath) + "</strong></a>";
        return aaa;

    }


}
