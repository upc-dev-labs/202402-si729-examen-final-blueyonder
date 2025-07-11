package com.blueyonder.platform.u20201e843;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PracticeEbBlueyonderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeEbBlueyonderApplication.class, args);
    }

}
