package com.anurag.tools.webshell.dto;

public class PredefinedCommand {
    private String command;
    private Long commandId;
    private String commandName;
    private String commandDesc;

    public PredefinedCommand(Long commandId, String command, String commandName, String commandDesc) {
        this.commandId = commandId;
        this.command = command;
        this.commandName = commandName;
        this.commandDesc = commandDesc;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Long getCommandId() {
        return commandId;
    }

    public void setCommandId(Long commandId) {
        this.commandId = commandId;
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