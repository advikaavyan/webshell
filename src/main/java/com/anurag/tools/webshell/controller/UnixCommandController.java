package com.anurag.tools.webshell.controller;

import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UnixCommandController {

    @GetMapping("/execute")
    public List<String> executeCommandww(@RequestParam String command) {
        System.out.println("Get executeCommand Method::: " + command);
        return executeCommandWindowsGitBash(command);

    }

    //dir C:\Users\ag942\OneDrive\Desktop\resumes /O-D /T:W
    //powershell.exe -Command "Get-ChildItem 'C:\Users\ag942\OneDrive\Desktop\resumes' | Sort-Object LastWriteTime | Select-Object Name, LastWriteTime, Length | Format-Table -AutoSize"
    // download file, Option Input then Link
    //powershell.exe -Command "Get-ChildItem 'C:\Users\ag942\OneDrive\Desktop\resumes' | Sort-Object LastWriteTime | Select-Object FullName, LastWriteTime, Length | Format-Table -AutoSize"
    @PostMapping("/execute")
    public List<String> executeCommand(@RequestBody String command) {
        System.out.println("Post executeCommand Method::: " + command);
        return executeCommandWindows(command);

    }

    public List<String> executeCommand2(@RequestParam String command) {
        System.out.println("Going to execute command on Linux Machine.. " + command);
        List<String> output = new ArrayList<>();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("sh", "-c", command);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.add(line);
            }

            process.waitFor();
        } catch (Exception e) {
            output.add("Error executing command: " + e.getMessage());
        }
        return output;
    }

    private List<String> executeCommandWindows(String command) {
        System.out.println("Going to execute command on Windows Machine.. " + command);
        List<String> output = new ArrayList<>();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.add(line);
            }

            process.waitFor();
        } catch (Exception e) {
            output.add("Error executing command: " + e.getMessage());
        }
        return output;
    }


    private List<String> executeCommandWindowsPS(String command) {
        System.out.println("Going to execute command on Windows Machine..Power Shell " + command);
        List<String> output = new ArrayList<>();
        try {
            // PowerShell command to list files with Name, LastWriteTime, and Length sorted by date
              command = "powershell.exe -Command \"Get-ChildItem 'C:\\Users\\ag942\\OneDrive\\Desktop\\resumes' | Sort-Object LastWriteTime | Format-Table Name, LastWriteTime, Length -AutoSize\"";

            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            processBuilder.redirectErrorStream(true); // Redirect error stream to output stream
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.add(line);
            }

            process.waitFor();
        } catch (Exception e) {
            output.add("Error executing command: " + e.getMessage());
        }
        return output;
    }

    private List<String> executeCommandWindowsGitBash(String command) {
        List<String> output = new ArrayList<>();
        System.out.println("Going to execute command on Windows Machine..GIT BASH " + command);
        try {
            // Command to execute `ls -ltr` using Git Bash
            /*  command = "cmd.exe /c \"C:\\Program Files\\Git\\bin\\bash.exe\" -c \"ls -ltr /c/Users/ag942/OneDrive/Desktop/resumes\"";


            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));*/

            // command = "C:\\Program Files\\Git\\bin\\bash.exe -c \"ls -ltr /c/Users/ag942/OneDrive/Desktop/resumes\"";

            //bash.exe -c "find \"$PWD\" -maxdepth 1 -type f -printf '%p %TY-%Tm-%Td %TH:%TM %s\n' | sort"            worked
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.add(line);
            }

            process.waitFor();
        } catch (Exception e) {
            output.add("Error executing command: " + e.getMessage());
        }
        return output;

    }
}
