package com.highthon.highthon3server.domain;

import com.highthon.highthon3server.domain.application.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationRepositoryTest {

    @Autowired
    ApplicationRepository applicationRepository;

    @Value("${limit.life-develop}")
    private Integer LIMIT_LIFE_DEVELOP;

    Queue<Long> applicationIds = new LinkedList<>();

    @Before
    public void setup() {
        applicationRepository.deleteAll();
        for (int i = 1; i <= LIMIT_LIFE_DEVELOP + 1; i++) {
            Long applicationId = applicationRepository.save(Application.builder()
                    .name("윤찬명")
                    .sex(Sex.MAN)
                    .phone("010-0000-00" + (i >= 10 ? i : "0" + i))
                    .email("test" + i + "@test.com")
                    .password("1234")
                    .area(Area.LIFE)
                    .position(Position.DEVELOP)
                    .belong("테스트고등학교")
                    .isAccepted(i <= LIMIT_LIFE_DEVELOP)
                    .build()).getApplicationId();

            applicationIds.add(applicationId);
        }
    }

    @Test
    public void 신청서_상태를_조회한다() {
        for (long i = 1; i <= LIMIT_LIFE_DEVELOP + 1; i++) {
            ApplicationCondition condition = applicationRepository.getApplicationConditionById(applicationIds.remove());
            boolean isAccepted = condition.getIsAccepted();

            if (i > LIMIT_LIFE_DEVELOP)
                assertThat(condition.getWaitingNumber(), is(i - LIMIT_LIFE_DEVELOP));

            assertThat(isAccepted, is(i <= LIMIT_LIFE_DEVELOP));
        }
    }

    @After
    public void cleanup() {
        applicationRepository.deleteAll();
    }

}
