package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CouriersEntity;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

@Service
public class MailSender {

    private JavaMailSender mailSender;

    @Autowired
    public MailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String username;

    public void send(String emailTo, String subject, String message) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

    public void sendEmailForActivation(UsersEntity user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Привет, %s!\n" +
                            "Добро пожаловать в нашу службу доставки Delivery Service!\n" +
                            "Пожалуйста, перейдите по следующей ссылке:\n" +
                            "http://localhost:8080/activate/%s,\n" +
                            "чтобы активировать вашу учётную запись!",
                    user.getUsername(),
                    user.getActivationCode()
            );
            send(user.getEmail(), "Активация учётной записи Delivery Service!", message);
        }
    }

    public void sendHiredEmail(UsersEntity user, String professionChoice) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String job;
            if (professionChoice.equals("courier")) {
                job = "курьера";
            } else {
                job = "менеджера";
            }
            String message = String.format(
                    "Привет, %s!\n" +
                            "Наши поздравления! Мы принимаем вас на должность %s в нашу службу доставки.\n" +
                            "Мы добавили вас в нашу базу курьеров, теперь вы можете приступать к работе\n" +
                            "в любое удобное для вас время.\n" +
                            "Для этого подтвердите готовность работать в своём профиле:\n" +
                            "http://localhost:8080/profile.",
                    user.getUsername(),
                    job
            );
            send(user.getEmail(), "О принятии на работу в Delivery Service!", message);
        }
    }

    public void sendRejectedEmail(UsersEntity user, String professionChoice) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String job;
            if (professionChoice.equals("courier")) {
                job = "курьера";
            } else {
                job = "менеджера";
            }
            String message = String.format(
                    "Привет, %s :(\n" +
                            "Мы вынуждены отказать вам в принятии на должность %s.\n" +
                            "Если вы считаете, что мы отказали вам неправомерно,\n" +
                            "вы можете оставить еще одну заявку в своем профиле:\n" +
                            "http://localhost:8080/profile для повторного рассмотрения.",
                    user.getUsername(),
                    job
            );
            send(user.getEmail(), "О принятии на работу в Delivery Service :(", message);
        }
    }

    public void sendFiredEmail(CouriersEntity courier) {
        if (!StringUtils.isEmpty(courier.getEmail())) {
            String message = String.format(
                    "Привет, %s!\n" +
                            "Твой рейтинг опустился ниже плинтуса и ты позоришь нашу компанию, нахер тебя!\n" +
                            "Можешь оставить плаксивый отзыв о нашей компании на:\n" +
                            "http://localhost:8080/reviews.",
                    courier.getFirstName()
            );
            send(courier.getEmail(), "Уведомление об увольнении из Delivery Service!", message);
        }
    }
}
