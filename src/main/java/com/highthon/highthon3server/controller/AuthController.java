package com.highthon.highthon3server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {


    @GetMapping("/admin")
    public String admin() {
        return "hello";
    }

}
