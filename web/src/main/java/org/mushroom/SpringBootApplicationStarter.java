package org.mushroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = "org.mushroom")
@EnableConfigurationProperties
public class SpringBootApplicationStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationStarter.class, args);
    }
}
