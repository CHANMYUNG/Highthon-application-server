package com.highthon.highthon3server.security;

import com.highthon.highthon3server.domain.admin.Admin;
import com.highthon.highthon3server.domain.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String AdminId) throws UsernameNotFoundException {
        Admin admin = adminRepository.findById(AdminId).orElse(null);

        if (admin == null) {
            throw new UsernameNotFoundException(String.format("No user found with AdminId '%s'", AdminId));
        } else {
            return admin;
        }
    }
}
