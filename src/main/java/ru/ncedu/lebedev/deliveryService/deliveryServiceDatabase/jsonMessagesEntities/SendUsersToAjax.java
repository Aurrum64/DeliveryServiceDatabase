package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.jsonMessagesEntities;

import lombok.Getter;
import lombok.Setter;
import ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.tableEntities.UsersEntity;

@Getter
@Setter
public class SendUsersToAjax {

    private String message;
    private Iterable<UsersEntity> result;
}
