package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities;

import lombok.Getter;
import lombok.Setter;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.OrderDetailsEntity;

@Getter
@Setter
public class SendOrderDetailsToAjax {

    private String msg;
    private Iterable<OrderDetailsEntity> result;
}
