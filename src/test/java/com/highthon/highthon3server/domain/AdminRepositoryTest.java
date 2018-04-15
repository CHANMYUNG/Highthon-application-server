package com.highthon.highthon3server.domain;

import com.highthon.highthon3server.domain.admin.Admin;
import com.highthon.highthon3server.domain.admin.AdminRepository;
import com.highthon.highthon3server.domain.admin.AdminRole;
import com.highthon.highthon3server.domain.admin.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminRepositoryTest {

    @Autowired
    private AdminRepository repository;

    private static final String TEST_ADMIN_ACCOUNT_EMAIL = "test1@test.com";

    @Before
    public void setup() {
        repository.deleteByEmail("test1@test.com");
        Admin admin = Admin.builder()
                .belong("테스트학교")
                .email(TEST_ADMIN_ACCOUNT_EMAIL)
                .name("testAdmin")
                .password("1234")
                .phone("010-0000-0000")
//                .roles(Arrays.asList(new AdminRole(adRole.BASIC))
                .build();
        repository.save(admin);
    }

    @Test
    public void 권한_가져오기() {
        Admin admin = repository.findByEmail(TEST_ADMIN_ACCOUNT_EMAIL);

        Collection<AdminRole> roles = admin.getRoles();

        roles.stream().forEach(adminRole -> {
//            if(adminRole.getRoleName().equals(admin.))
        });
    }
}
