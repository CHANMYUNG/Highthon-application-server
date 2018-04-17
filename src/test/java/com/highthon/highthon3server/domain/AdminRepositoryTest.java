package com.highthon.highthon3server.domain;

import com.highthon.highthon3server.domain.admin.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminRepositoryTest {

    @Autowired
    private AdminRepository repository;

    @Autowired
    private AdminRoleRepository adminRoleRepository;


    private static final String TEST_ADMIN_ACCOUNT_EMAIL = "test1@test.com";

    @Before
    public void setup() {
        repository.deleteByEmail(TEST_ADMIN_ACCOUNT_EMAIL);
        Admin admin = Admin.builder()
                .belong("테스트학교")
                .email(TEST_ADMIN_ACCOUNT_EMAIL)
                .name("testAdmin")
                .password("1234")
                .phone("010-0000-0000")
                .build();

        AdminRole basicRole = new AdminRole(TEST_ADMIN_ACCOUNT_EMAIL, Role.BASIC);

        repository.save(admin);
        adminRoleRepository.save(basicRole);
    }

    @Test
    public void 권한_가져오기() {
        Admin admin = repository.findByEmail(TEST_ADMIN_ACCOUNT_EMAIL);
        Collection<AdminRole> adminRoles = admin.getRoles();

        List<Role> roles = adminRoles.stream().map(AdminRole::getRole).collect(Collectors.toCollection(ArrayList::new));

        assertThat(roles, hasItem(Role.BASIC));
    }
}
