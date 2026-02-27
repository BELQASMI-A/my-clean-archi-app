package com.infrastructure.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

    // On laisse la racine libre pour Spring Static (index.html)
    // On garde juste une API de santé du système
    @GetMapping("/api/health")
    public Map<String, Object> getHealth() {
        Map<String, Object> status = new HashMap<>();
        status.put("system", "Gargantua Core");
        status.put("status", "UP");
        return status;
    }
}
