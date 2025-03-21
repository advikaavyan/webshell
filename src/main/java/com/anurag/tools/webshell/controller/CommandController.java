package com.anurag.tools.webshell.controller;

import com.anurag.tools.webshell.dto.CommandRequest;
import com.anurag.tools.webshell.dto.CommandResponse;
import com.anurag.tools.webshell.service.CommandService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandController {
    private CommandService commandService;

    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping(value = "/execute", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommandResponse> executeCommand(@RequestBody CommandRequest request) {
        System.out.println("Request.........."+request);
        CommandResponse result = commandService.executeCommand(request);

        return ResponseEntity.ok(result);
    }

}
