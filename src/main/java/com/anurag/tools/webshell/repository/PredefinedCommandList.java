package com.anurag.tools.webshell.repository;

import com.anurag.tools.webshell.dto.PredefinedCommand;

import java.util.Arrays;
import java.util.List;

public class PredefinedCommandList {
     public static List<PredefinedCommand> availableCommands = Arrays.asList(

             new PredefinedCommand(Long.valueOf(1), "ls -ltr", "List Of Files", "VErmi"),
            new PredefinedCommand(Long.valueOf(2), "ls -ltr $(pwd)/*", "List Of Files", "List Of Files under current"),
            new PredefinedCommand(Long.valueOf(3), "df -h", "Disk Space ", "Server's Disk Space"),
            new PredefinedCommand(Long.valueOf(4), "ps -eaf", "Running Processes ", "Running Processes List"),
            new PredefinedCommand(Long.valueOf(5), "uptime", "Server Started Since ", "Server Started Since"),
            new PredefinedCommand(Long.valueOf(6), "free -m", "Free RAM Memory ", "Free RAM Memory Data")


            );
}
