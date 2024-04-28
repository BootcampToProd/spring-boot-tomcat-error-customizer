package com.bootcamptoprod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class SpringBootTomcatErrorCustomizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTomcatErrorCustomizerApplication.class, args);
    }

}
