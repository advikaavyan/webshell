package com.anurag.tools.webshell.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import liquibase.integration.spring.SpringLiquibase;

@Component
public class LiquibaseStartup {
    private final SpringLiquibase liquibase;

    public LiquibaseStartup(SpringLiquibase liquibase) {
        this.liquibase = liquibase;
    }

    @PostConstruct
    public void runLiquibase() {
        try {
            liquibase.afterPropertiesSet();
            System.out.println("✅ Liquibase executed on startup!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
