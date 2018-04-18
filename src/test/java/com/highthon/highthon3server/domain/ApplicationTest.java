package com.highthon.highthon3server.domain;

import com.highthon.highthon3server.domain.application.*;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    ApplicationRepository applicationRepository;

    Long TEST_APPLICATION_ID;

    @Test
    public void 신청서를_작성한다() {

        TEST_APPLICATION_ID = applicationRepository.save(Application.builder()
                .name("윤찬명")
                .sex(Sex.MAN)
                .phone("010-1111-1111")
                .email("test@test.com")
                .password("1234")
                .area(Area.LIFE)
                .position(Position.DEVELOP)
                .build()).getApplicationId();

        assertTrue(TEST_APPLICATION_ID != null);
    }

    @Test
    public void 신청서_상태를_조회한다() {
        ApplicationCondition status = applicationRepository.getApplicationConditionById(TEST_APPLICATION_ID);

        System.out.println(status.getIsAccepted());
        System.out.println(status.getWaitingNumber());
        System.out.println(status.getName());

        assertTrue(status.getIsAccepted() == false);
    }

    @After
    public void cleanup() {
//        applicationRepository.deleteAll();
    }
//    @Test

}
