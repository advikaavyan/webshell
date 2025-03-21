package com.anurag.tools.webshell.controller;
/*
import com.anurag.tools.webshell.dto.PredefinedCommand;
import com.anurag.tools.webshell.entity.PredefinedCommandEntity;
import com.anurag.tools.webshell.service.PredefinedCommandService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PredefinedCommandController {
    private final PredefinedCommandService service;

    public PredefinedCommandController(PredefinedCommandService service) {
        this.service = service;
    }

    @GetMapping("/predefined-commands")
    public List<PredefinedCommand> getPredefinedCommands() {
        List<PredefinedCommand> resList = new ArrayList<>();
        List<PredefinedCommandEntity> list = service.getAllCommands();
        for (PredefinedCommandEntity predefinedCommand : list) {
            resList.add(new PredefinedCommand(predefinedCommand.getCommandId(), predefinedCommand.getCommand(), predefinedCommand.getCommandName(), predefinedCommand.getCommandDesc()));
        }
        return resList;


    }

    @GetMapping
    public List<PredefinedCommandEntity> getCommands() {
        return service.getAllCommands();
    }

    @PostMapping
    public PredefinedCommandEntity addCommand(@RequestBody PredefinedCommandEntity command) {
        return service.saveCommand(command);
    }
}*/
import com.anurag.tools.webshell.dto.PredefinedCommand;
import com.anurag.tools.webshell.entity.PredefinedCommandEntity;
import com.anurag.tools.webshell.service.PredefinedCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/predefined-commands")
@CrossOrigin(origins = "*")  // Enable CORS if needed
public class PredefinedCommandController {

    @Autowired
    private PredefinedCommandService service;

    @GetMapping
    public ResponseEntity<List<PredefinedCommandEntity>> getAllCommands() {
        return ResponseEntity.ok(service.getAllCommands());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PredefinedCommandEntity> getCommandById(@PathVariable Long id) {
        Optional<PredefinedCommandEntity> command = service.getCommandById(id);
        return command.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PredefinedCommandEntity> createCommand(@RequestBody PredefinedCommandEntity command) {
        return ResponseEntity.ok(service.createCommand(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PredefinedCommandEntity> updateCommand(@PathVariable Long id, @RequestBody PredefinedCommandEntity updatedCommand) {
        return ResponseEntity.ok(service.updateCommand(id, updatedCommand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommand(@PathVariable Long id) {
        service.deleteCommand(id);
        return ResponseEntity.noContent().build();
    }
}
