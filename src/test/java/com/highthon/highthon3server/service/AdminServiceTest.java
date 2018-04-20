package com.highthon.highthon3server.service;

import com.highthon.highthon3server.domain.admin.AdminRepository;
import com.highthon.highthon3server.domain.invitation.Invitation;
import com.highthon.highthon3server.domain.invitation.InvitationRepository;
import com.highthon.highthon3server.service.admin.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private InvitationRepository invitationRepository;

    private String TEST01_USER_EMAIL = "test01@test.com";

    private String TEST02_USER_EMAIL = "test02@test.com";

    @Before
    public void setup() {
//        adminRepository.deleteAll();
//        invitationRepository.deleteAll();
//
//        Invitation invitation01 = new Invitation(TEST01_USER_EMAIL);
//        Invitation invitation02 = new Invitation(TEST02_USER_EMAIL);
//        // 초대 2개 생성
//        invitationRepository.saveAll(
//                Arrays.asList(
//                        invitation01,
//                        invitation02
//                );


//        adminService.createAdmin()
    }

    @Test
    public void 관리자_회원가입_테스트() {

    }

    @Test
    public void 권한_위임_TEST() {
        assertTrue(true);
    }
}
