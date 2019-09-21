package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;

@SpringBootApplication
@PropertySource(value = "templates/application.properties", ignoreResourceNotFound = true)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
