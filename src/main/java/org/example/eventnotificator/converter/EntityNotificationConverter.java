package org.example.eventnotificator.converter;

import org.example.eventnotificator.domain.Notification;
import org.example.eventnotificator.entity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityNotificationConverter {

    public Notification toDomain(final NotificationEntity entity) {
        return new Notification(entity.getEventId(), entity.getUserId(), entity.getCreatedAt(), entity.isRead(), entity.getChangesJson());
    }
}
