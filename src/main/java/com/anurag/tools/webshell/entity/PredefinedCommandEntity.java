package com.anurag.tools.webshell.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "predefined_command")
public class PredefinedCommandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commandId;

    private String command;
    private String commandName;
    private String commandDesc;

    public PredefinedCommandEntity() {
    }

    public PredefinedCommandEntity(String command, String commandName, String commandDesc) {
        this.command = command;
        this.commandName = commandName;
        this.commandDesc = commandDesc;
    }

    public Long getCommandId() {
        return commandId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandDesc() {
        return commandDesc;
    }

    public void setCommandDesc(String commandDesc) {
        this.commandDesc = commandDesc;
    }
}
