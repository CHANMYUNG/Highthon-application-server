package com.highthon.highthon3server.domain;

import com.highthon.highthon3server.domain.admin.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminRepositoryTest {

    @Autowired
    private AdminRepository repository;

    private final String TEST_ADMIN_ACCOUNT_EMAIL = "test0@test.com";

    private String TEST_ADMIN_ADMIN_ID;

    @Before
    public void setup() {
        repository.deleteAll();
        Admin admin = Admin.builder()
                .belong("테스트학교")
                .email(TEST_ADMIN_ACCOUNT_EMAIL)
                .name("testAdmin")
                .password(new BCryptPasswordEncoder().encode("1234"))
                .phone("010-0000-0000")
                .build();

        admin.grantRoles(Role.BASIC);
        repository.save(admin);

        TEST_ADMIN_ADMIN_ID = admin.getAdminId();
    }

    @Test
    public void 권한_가져오기() {
        Admin admin = repository.findByEmail(TEST_ADMIN_ACCOUNT_EMAIL).orElse(null);
        Set<Role> roles = admin.getRoles();


        assertThat(roles, hasItem(Role.BASIC));
    }

    @Test
    public void 권한_추가하기() {
        Admin admin = repository.findByEmail(TEST_ADMIN_ACCOUNT_EMAIL).orElse(null);

        admin.grantRoles(Role.SUPER);

        repository.save(admin);

        Set<Role> roles = admin.getRoles();

        assertThat(roles, hasItems(Role.BASIC, Role.SUPER));
    }

    @Test
    public void CRUD_레포지토리_Optional_orElse_테스트() {
        Admin admin = repository.findById(TEST_ADMIN_ADMIN_ID).orElse(null);

        assertThat(admin, not(nullValue()));
    }

    @After
    public void cleanup() {
        repository.deleteAll();
    }
}
