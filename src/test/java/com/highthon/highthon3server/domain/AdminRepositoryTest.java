package com.highthon.highthon3server.domain;

import com.highthon.highthon3server.domain.admin.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminRepositoryTest {

    @Autowired
    private AdminRepository repository;

    @Autowired
    private AdminRoleRepository adminRoleRepository;


    private static final String TEST_ADMIN_ACCOUNT_EMAIL = "test1@test.com";

    private static String TEST_ADMIN_ADMIN_ID;

    @Before
    public void setup() {
        repository.deleteByEmail(TEST_ADMIN_ACCOUNT_EMAIL);
        Admin admin = Admin.builder()
                .belong("테스트학교")
                .email(TEST_ADMIN_ACCOUNT_EMAIL)
                .name("testAdmin")
                .password("1234")
                .phone("010-0000-0000")
                .roles(Arrays.asList(new AdminRole(TEST_ADMIN_ACCOUNT_EMAIL, Role.BASIC)))
                .build();

        repository.save(admin);

        TEST_ADMIN_ADMIN_ID = admin.getAdminId();
    }

    @Test
    public void 권한_가져오기() {
        Admin admin = repository.findByEmail(TEST_ADMIN_ACCOUNT_EMAIL);
        Collection<AdminRole> adminRoles = admin.getRoles();

        List<Role> roles = adminRoles.stream().map(AdminRole::getRole).collect(Collectors.toCollection(ArrayList::new));

        assertThat(roles, hasItem(Role.BASIC));
    }

    @Test
    public void 권한_추가하기() {
        Admin admin = repository.findByEmail(TEST_ADMIN_ACCOUNT_EMAIL);

        admin.addRoles(Role.SUPER);

        repository.save(admin);

        List<Role> roles = admin.getRoles().stream().map(AdminRole::getRole).collect(Collectors.toCollection(ArrayList::new));

        assertThat(roles, hasItems(Role.BASIC, Role.SUPER));
    }

    @Test
    public void CRUD_레포지토리_Optional_orElse_테스트() {
        Admin admin = repository.findById(TEST_ADMIN_ADMIN_ID).orElse(null);
        System.out.println();
        assertThat(admin, not(nullValue()));
    }
}
