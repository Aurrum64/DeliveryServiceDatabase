package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.configs.AppProperties;

import java.security.Principal;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
