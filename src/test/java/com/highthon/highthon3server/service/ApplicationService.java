package com.highthon.highthon3server.service;


import com.highthon.highthon3server.domain.application.ApplicationRepository;
import com.highthon.highthon3server.domain.application.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    public void countByApplyType() {
        Integer count = applicationRepository.countByApplyType(Area.DEVELOPMENT);

        System.out.println(count);
        assertTrue(count != null);
    }
}
