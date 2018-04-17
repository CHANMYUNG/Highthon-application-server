package com.highthon.highthon3server.service.admin;

import com.highthon.highthon3server.domain.admin.Admin;
import com.highthon.highthon3server.dto.admin.AdminSignupDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface AdminService {

    void createAdmin(AdminSignupDto dto);

    void deleteAdmin(String adminId);

    Admin readAdmin(String adminId);
}
