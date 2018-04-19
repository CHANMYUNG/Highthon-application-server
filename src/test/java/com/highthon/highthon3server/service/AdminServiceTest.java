package com.highthon.highthon3server.service;

import com.highthon.highthon3server.domain.admin.AdminRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {
    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void 무조건_True() {
        assertTrue(true);
    }
}
