package org.example.eventnotificator.service;

import org.example.eventnotificator.converter.EntityNotificationConverter;
import org.example.eventnotificator.domain.Notification;
import org.example.eventnotificator.entity.NotificationEntity;
import org.example.eventnotificator.kafka.KafkaEvent;
import org.example.eventnotificator.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final EntityNotificationConverter converter;

    public NotificationService(NotificationRepository notificationRepository, EntityNotificationConverter converter) {
        this.notificationRepository = notificationRepository;
        this.converter = converter;
    }

    public void createNotification(KafkaEvent kafkaEvent) {
        List<Integer> subscriberUserIds = kafkaEvent.getSubscriberUserIds();
        Map<String, KafkaEvent.ChangedField> changesJson = serializeChanges(kafkaEvent);
        List<NotificationEntity> notificationEntities = subscriberUserIds.stream()
                .map(userId -> new NotificationEntity(null, kafkaEvent.getEventId(), userId, changesJson, false, LocalDateTime.now()))
                .toList();
        notificationRepository.saveAll(notificationEntities);
    }

    public List<Notification> getUnreadNotifications(Integer userId) {
        return notificationRepository.findByUserIdAndReadFalse(userId)
                .stream()
                .map(converter::toDomain)
                .toList();
    }

    public void markNotificationsAsRead(Integer userId, List<Integer> notificationIds) {
        List<NotificationEntity> notifications = notificationRepository.findByUserIdAndIdIn(userId, notificationIds);
        notifications.forEach(n -> n.setRead(true));
        notificationRepository.saveAll(notifications);
    }

    private Map<String, KafkaEvent.ChangedField> serializeChanges(KafkaEvent kafkaEvent) {
        return kafkaEvent.getChangedFields() != null ? kafkaEvent.getChangedFields() : Map.of();
    }

}
