package com.highthon.highthon3server.service.admin;

import com.highthon.highthon3server.domain.admin.Admin;
import com.highthon.highthon3server.domain.admin.AdminRepository;
import com.highthon.highthon3server.domain.admin.Role;
import com.highthon.highthon3server.dto.admin.AdminSignupDto;
import com.highthon.highthon3server.exception.AdminNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return adminRepository.findByEmail(email);
    }


    @Override
    public PasswordEncoder passwordEncoder() {
        return passwordEncoder;
    }

    @Override
    @Transactional
    public void createAdmin(AdminSignupDto dto) {
        Admin admin = dto.toEntity();
        if (adminRepository.count() == 0) {
            admin.setRoles(Role.BASIC, Role.SUPER);
        } else admin.setRoles(Role.BASIC);

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(String adminId) {
        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin == null) throw new AdminNotFoundException(adminId);
        adminRepository.delete(admin);
    }

    @Override
    public Admin readAdmin(String adminId) {
        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin == null) throw new AdminNotFoundException(adminId);
        return admin;
    }
}
