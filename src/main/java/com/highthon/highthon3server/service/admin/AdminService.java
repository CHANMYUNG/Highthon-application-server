package com.highthon.highthon3server.service.admin;

import com.highthon.highthon3server.domain.admin.Admin;
import com.highthon.highthon3server.domain.admin.AdminRepository;
import com.highthon.highthon3server.domain.admin.Role;
import com.highthon.highthon3server.domain.invitation.Invitation;
import com.highthon.highthon3server.domain.invitation.InvitationRepository;
import com.highthon.highthon3server.dto.auth.AdminSignupDto;
import com.highthon.highthon3server.exception.AdminNotFoundException;
import com.highthon.highthon3server.exception.DuplicatedValueException;
import com.highthon.highthon3server.exception.InvitationCodeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;


@Service
public class AdminService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private InvitationRepository invitationRepository;

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
        final String invitationCode = adminSignupDto.getInvitationCode();

        Invitation invitation = invitationRepository.findByInvitationCode(invitationCode).orElse(null);

        if (invitation == null) throw new InvitationCodeNotFoundException();

        final String email = invitation.getEmail();

        invitationRepository.delete(invitation);

        if (adminRepository.existsByEmail(email)) throw new DuplicatedValueException("email");

        Admin admin = Admin.builder()
                .email(email)
                .phone(adminSignupDto.getPhone())
                .password(passwordEncoder.encode(adminSignupDto.getPassword()))
                .name(adminSignupDto.getName())
                .belong(adminSignupDto.getBelong())
                .build();

        admin.setRoles(Role.BASIC);

        if (adminRepository.count() == 0)
            admin.addRoles(Role.SUPER);

        return adminRepository.save(admin);
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
