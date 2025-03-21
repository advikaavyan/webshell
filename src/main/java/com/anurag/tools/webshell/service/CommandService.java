package com.anurag.tools.webshell.service;

import com.anurag.tools.webshell.dto.CommandRequest;
import com.anurag.tools.webshell.dto.CommandResponse;
import com.anurag.tools.webshell.entity.PredefinedCommandEntity;
import com.anurag.tools.webshell.service.linux.LinuxCommandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommandService {

    final String LINUX = "LINUX";
    LinuxCommandService linuxCommandService;
    private final PredefinedCommandService predefinedCommandService;


    public CommandService(PredefinedCommandService predefinedCommandService, LinuxCommandService linuxCommandService) {
        this.predefinedCommandService = predefinedCommandService;
        this.linuxCommandService = linuxCommandService;
    }

    public CommandResponse executeCommand(CommandRequest request) {
        CommandResponse output = null;
        CommandRequest request11 = getCR(request);

        if (LINUX.equals(getTargetOperatingSystem(request11))) {
            output = linuxCommandService.executeCommand(request11);
        }

        return output;
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private CommandRequest getCR(CommandRequest request) {
        CommandRequest request11 = new CommandRequest();


        if (isNumeric(request.getCommand())) {
           /* Optional<PredefinedCommand> command = PredefinedCommandList.availableCommands.stream()
                    .filter(cmd -> cmd.getCommandId().equals(Long.valueOf(request.getCommand())))
                    .findFirst();
            PredefinedCommand predefinedCommand = command.get();
            request11.setCommand(predefinedCommand.getCommand());*/

            Optional<PredefinedCommandEntity> command = predefinedCommandService.getCommandById(Long.valueOf(request.getCommand()));

            request11.setCommand(command.get().getCommand());
        } else {
            request11.setCommand(request.getCommand());
        }


        return request11;
    }

    private String getTargetOperatingSystem(CommandRequest request) {
        String targetOperatingSystem = "LINUX";
        return targetOperatingSystem;
    }

}
