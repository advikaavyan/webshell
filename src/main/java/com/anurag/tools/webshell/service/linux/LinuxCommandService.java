package com.anurag.tools.webshell.service.linux;

import com.anurag.tools.webshell.dto.CommandRequest;
import com.anurag.tools.webshell.dto.CommandResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Service
public class LinuxCommandService {

    private static final List<String> ALLOWED_COMMANDS = Arrays.asList("ls", "df", "ps");

    public CommandResponse executeCommand(CommandRequest request) {
        String command = request.getCommand().trim();

       /* if (!ALLOWED_COMMANDS.contains(command)) {
            String responseHtml = "<table border='1' class='table table-bordered'>" +
                    "<tr><th>Status</th><th>Message</th></tr>" +
                    "<tr><td>403 Forbidden</td><td>This command is not allowed for execution.</td></tr>" +
                    "</table>";
            return responseHtml;
        }*/

        StringBuilder output = new StringBuilder();

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Bootstrap Table Styling (Load Once in UI to Optimize)
            //output.append("<div class='p-1 m-1'> <table id='commandTable' class='table table-striped table-bordered'>");
            output.append("<div class=''> <table id='commandTable' class='table table-striped table-bordered'>");

            // Command-specific parsing
            if (command.startsWith("ls -ltr")) {

                ParseLsLtrOutput.parse(reader, output);
            } else if (command.startsWith("ps -eaf")) {
                output.append("<thead class='table-dark'><tr><th>UID</th><th>PID</th><th>PPID</th><th>C</th><th>STIME</th><th>TTY</th><th>TIME</th><th>CMD</th></tr></thead><tbody>");
                ParsePsEafOutput.parse(reader, output);
            } else if (command.startsWith("netstat -tulnp")) {
                output.append("<thead class='table-dark'><tr><th>Proto</th><th>Recv-Q</th><th>Send-Q</th><th>Local Address</th><th>Foreign Address</th><th>State</th><th>PID/Program</th></tr></thead><tbody>");
                ParseNetstatOutput.parse(reader, output);
            } else if (command.startsWith("df -h")) {
                output.append("<thead class='table-dark'><tr><th>Filesystem</th><th>Size</th><th>Used</th><th>Avail</th><th>Use%</th><th>Mounted on</th></tr></thead><tbody>");
                ParseDfOutput.parse(reader, output);
            } else if (command.startsWith("uptime")) {
                output.append("<thead class='table-dark'><tr><th>System Uptime</th></tr></thead><tbody>");
                parseUptimeOutput(reader, output);
            } else {
                output.append("<thead class='table-dark'><tr><th>Output</th></tr></thead><tbody>");
                parseGeneralOutput(reader, output);
            }

            output.append("</tbody></table></div>");

        } catch (Exception e) {
            return new CommandResponse(command, "<p class='text-danger'>Error executing command: " + e.getMessage() + "</p>");

        }
        return new CommandResponse(command, output.toString());

    }


    private void parseUptimeOutput(BufferedReader reader, StringBuilder output) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            output.append("<tr><td class='text-start'>" + line + "</td></tr>");
        }
    }

    private void parseGeneralOutput(BufferedReader reader, StringBuilder output) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            output.append("<tr><td>").append(line).append("</td></tr>");
        }
    }
}
