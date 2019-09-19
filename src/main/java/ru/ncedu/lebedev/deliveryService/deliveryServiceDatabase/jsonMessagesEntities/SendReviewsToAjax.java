package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities;

import lombok.Getter;
import lombok.Setter;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.ReviewsEntity;

@Getter
@Setter
public class SendReviewsToAjax {

    private String message;
    private Iterable<ReviewsEntity> result;
}
