package com.anurag.tools.webshell.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommandResponse {
    public CommandResponse(String command, String output) {
        this.command = command;
        this.output = output;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @JsonProperty("command")
    private String command;

    @JsonProperty("output")
    private String output;


}