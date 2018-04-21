package com.highthon.highthon3server.controller;

import com.highthon.highthon3server.dto.auth.AdminSignupDto;
import com.highthon.highthon3server.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @DeleteMapping("/admin/{adminId}")
    public void deleteAdmin(@PathVariable("adminId") String adminId) {
        adminService.deleteAdmin(adminId);
    }
}
