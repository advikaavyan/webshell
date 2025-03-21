package com.anurag.tools.webshell.service;

import com.anurag.tools.webshell.entity.PredefinedCommandEntity;
import com.anurag.tools.webshell.repository.PredefinedCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PredefinedCommandService {

    @Autowired
    private PredefinedCommandRepository repository;

    public List<PredefinedCommandEntity> getAllCommands() {
        return repository.findAll();
    }

    public Optional<PredefinedCommandEntity> getCommandById(Long id) {
        return repository.findById(id);
    }

    public PredefinedCommandEntity createCommand(PredefinedCommandEntity command) {
        return repository.save(command);
    }

    public PredefinedCommandEntity updateCommand(Long id, PredefinedCommandEntity updatedCommand) {
        return repository.findById(id)
                .map(existingCommand -> {
                    existingCommand.setCommand(updatedCommand.getCommand());
                    existingCommand.setCommandName(updatedCommand.getCommandName());
                    existingCommand.setCommandDesc(updatedCommand.getCommandDesc());
                    return repository.save(existingCommand);
                })
                .orElseThrow(() -> new RuntimeException("Command not found with ID: " + id));
    }

    public void deleteCommand(Long id) {
        repository.deleteById(id);
    }
   /* private final PredefinedCommandRepository repository;

    public PredefinedCommandService(PredefinedCommandRepository repository) {
        this.repository = repository;
    }

    public List<PredefinedCommandEntity> getAllCommands() {
        return repository.findAll();
    }

    public Optional<PredefinedCommandEntity> getCommandById(Long commandId) {
        return repository.findById(commandId);
    }

    public PredefinedCommandEntity saveCommand(PredefinedCommandEntity command) {
        return repository.save(command);
    }*/
}
