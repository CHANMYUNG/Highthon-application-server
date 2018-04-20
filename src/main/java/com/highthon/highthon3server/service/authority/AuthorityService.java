package com.highthon.highthon3server.service.authority;

import com.highthon.highthon3server.domain.admin.Admin;
import com.highthon.highthon3server.domain.admin.AdminRepository;
import com.highthon.highthon3server.domain.admin.Role;
import com.highthon.highthon3server.exception.AdminNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorityService {

    @Autowired
    private AdminRepository adminRepository;

    @Transactional
    public void grantSuperAuthorityToOtherAdminAndRefuseMine(String email, String adminId) {
        Admin target = adminRepository.findById(adminId).orElse(null);

        if (target == null) throw new AdminNotFoundException();

        target.grantRoles(Role.SUPER);

        adminRepository.save(target);

        Admin origin = adminRepository.findByEmail(email).orElse(null);

        assert origin != null;

        origin.refuseRoles(Role.SUPER);

        adminRepository.save(origin);
    }
}
