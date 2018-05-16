package com.highthon.highthon3server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class Highthon3ServerApplication {

    private static final String APPLICATION_YML_LOCATIONS = "spring.config.location=" +
            "classpath:application.yml," +
            "/app/config/highthon/application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(Highthon3ServerApplication.class)
                .properties(APPLICATION_YML_LOCATIONS).run(args);
    }
}
