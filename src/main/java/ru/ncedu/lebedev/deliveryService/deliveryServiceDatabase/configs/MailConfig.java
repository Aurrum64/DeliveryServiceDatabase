package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    /*@Value("${spring.mail.host}")
    private String host;*/

    /*@Value("${spring.mail.username}")
    private String username;*/

    /*@Value("${spring.mail.password}")
    private String password;*/

    /*@Value("${spring.mail.port}")
    private int port;*/

    /*@Value("${spring.mail.protocol}")
    private String protocol;*/

    /*@Value("${mail.debug}")
    private String debug;*/

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.yandex.ru");
        mailSender.setPort(465);
        mailSender.setUsername("Aurrumm@yandex.ru");
        mailSender.setPassword("eklmn85cool84net");

        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.transport.protocol", "smtps");
        /*properties.setProperty("mail.debug", debug);*/
        properties.setProperty("mail.smtp.ssl.enable", "true");

        return mailSender;
    }
}
