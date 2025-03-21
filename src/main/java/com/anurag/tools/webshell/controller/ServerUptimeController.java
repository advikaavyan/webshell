package com.anurag.tools.webshell.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.management.ManagementFactory;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ServerUptimeController {

    private final long serverStartTime = Instant.now().toEpochMilli() - ManagementFactory.getRuntimeMXBean().getUptime();

    @GetMapping("/server-uptime")
    public Map<String, Long> getServerStartTime() {
        Map<String, Long> response = new HashMap<>();
        response.put("serverStartTime", serverStartTime); // Returns start time in milliseconds
        return response;
    }
}
