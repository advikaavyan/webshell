package com.anurag.tools.webshell.controller;

import com.anurag.tools.webshell.dto.PredefinedCommand;
import com.anurag.tools.webshell.repository.PredefinedCommandList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/apaaaai")
public class PredCommandController {


    @GetMapping("/predefined-commands")
    public List<PredefinedCommand> getPredefinedCommands() {

        return PredefinedCommandList.availableCommands;


    }



}