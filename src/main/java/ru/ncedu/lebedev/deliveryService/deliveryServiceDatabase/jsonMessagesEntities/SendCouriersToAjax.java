package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities;

import lombok.Getter;
import lombok.Setter;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.CouriersEntity;

@Getter
@Setter
public class SendCouriersToAjax {

    private String msg;
    private Iterable<CouriersEntity> result;
}
