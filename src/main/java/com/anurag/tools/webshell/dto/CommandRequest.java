package com.anurag.tools.webshell.dto;

public class CommandRequest {
    private String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "CommandRequest{" +
                "command='" + command + '\'' +
                '}';
    }
}