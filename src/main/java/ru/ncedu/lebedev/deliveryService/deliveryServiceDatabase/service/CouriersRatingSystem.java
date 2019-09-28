package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.repositories.CouriersRepository;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CouriersEntity;

@Service
public class CouriersRatingSystem {

    private CouriersRepository couriersRepository;
    private MailSender mailSender;

    @Autowired
    public CouriersRatingSystem(CouriersRepository couriersRepository,
                                MailSender mailSender) {
        this.couriersRepository = couriersRepository;
        this.mailSender = mailSender;
    }

    public void setCourierRating(CouriersEntity courier, Integer rating) {

        Integer currentCourierRating = courier.getRating();
        Integer courierRatingAfterReview;

        if (rating == 1) {
            courierRatingAfterReview = currentCourierRating - 2;
        } else if (rating == 2) {
            courierRatingAfterReview = currentCourierRating - 1;
        } else if (rating == 3) {
            courierRatingAfterReview = currentCourierRating;
        } else if (rating == 4) {
            courierRatingAfterReview = currentCourierRating + 1;
        } else {
            courierRatingAfterReview = currentCourierRating + 2;
        }
        courier.setRating(courierRatingAfterReview);
        couriersRepository.save(courier);

        /*if (courier.getRating() <= 0) {
            sendFiredEmail(courier);
        }*/
    }

    private void sendFiredEmail(CouriersEntity courier) {
        if (!StringUtils.isEmpty(courier.getEmail())) {
            String message = String.format(
                    "Привет, %s!\n" +
                            "Твой рейтинг опустился ниже плинтуса и ты позоришь нашу компанию, нахер тебя!",
                    courier.getFirstName()
            );
            mailSender.send(courier.getEmail(), "Уведомление об увольнении из Delivery Service!", message);
        }
    }
}
