package com.highthon.highthon3server.controller;

import com.highthon.highthon3server.dto.admin.AdminSignupDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {


    @PostMapping("/signup")
    public void create(@RequestBody AdminSignupDto dto) {
        //TODO: create service layer class, write some signup logic, and call that at here
    }
}
