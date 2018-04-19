package com.highthon.highthon3server;

import org.hamcrest.core.IsNot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ValueInjectionTest {

    @Value("${limit.life-develop}")
    private Integer LIMIT_LIFE_DEVELOP;

    @Test
    public void LIMIT_LIFE_DEVELOP_인젝션_테스트() {
        assertThat(LIMIT_LIFE_DEVELOP, IsNot.not(nullValue()));
    }
}
