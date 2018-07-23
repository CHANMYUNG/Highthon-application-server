package com.highthon.highthon3server.controller;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class WebRestController {

    private Environment env;

    @GetMapping("/health")
    public Map<String, String> getHealth() {
        Map<String, String> map = new HashMap<>();
        map.put("status", "UP");
        return map;
    }

    @GetMapping("/profile")
    public String getProfile() {
        Set<String> profileSet = Arrays.stream(env.getActiveProfiles()).collect(Collectors.toSet());
        if (profileSet.contains("set2")) return "set2";
        else return "set1";
    }
}
