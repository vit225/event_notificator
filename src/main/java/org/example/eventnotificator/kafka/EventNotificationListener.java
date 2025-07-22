package org.example.eventnotificator.kafka;

import org.example.eventnotificator.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventNotificationListener {

    private final NotificationService notificationService;

    public EventNotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "events-topic", containerFactory = "containerFactory")
    public void listenEvents(KafkaEvent event) {
        System.out.println(event);
        notificationService.createNotification(event);
    }
}
