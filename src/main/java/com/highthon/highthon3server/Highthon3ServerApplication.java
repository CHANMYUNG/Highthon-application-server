package com.highthon.highthon3server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class Highthon3ServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Highthon3ServerApplication.class)
                .properties("application.yml").run(args);
    }
}
