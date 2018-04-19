package com.highthon.highthon3server.service.admin;

import com.highthon.highthon3server.domain.admin.Admin;
import com.highthon.highthon3server.domain.admin.AdminRepository;
import com.highthon.highthon3server.domain.admin.Role;
import com.highthon.highthon3server.dto.auth.AdminSignupDto;
import com.highthon.highthon3server.exception.AdminNotFoundException;
import com.highthon.highthon3server.exception.DuplicatedValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email).orElse(null);

        if (admin == null) {
            throw new UsernameNotFoundException(String.format("No user found with email '%s'", email));
        } else {
            return admin;
        }
    }

    @Transactional
    public Admin createAdmin(AdminSignupDto adminSignupDto) {
        //TODO: 중복 체크
        Admin admin = adminSignupDto.toEntity();
        if (adminRepository.countByEmail(adminSignupDto.getEmail()) > 0) {
            throw new DuplicatedValueException("email");
        }

        if (adminRepository.countByPhone(adminSignupDto.getPhone()) > 0) {
            throw new DuplicatedValueException("phone");
        }

        if (adminRepository.count() == 0) {
            admin.setRoles(Role.BASIC, Role.SUPER);
        } else admin.setRoles(Role.BASIC);

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        adminRepository.save(admin);

        return admin;
    }

    @Transactional
    public void deleteAdmin(String adminId) {
        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin == null) throw new AdminNotFoundException(adminId);
        adminRepository.delete(admin);
    }

    @Transactional
    public Admin readAdmin(String adminId) {
        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin == null) throw new AdminNotFoundException(adminId);
        return admin;
    }

}
