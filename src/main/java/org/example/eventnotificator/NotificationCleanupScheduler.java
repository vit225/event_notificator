package org.example.eventnotificator;

import org.example.eventnotificator.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
public class NotificationCleanupScheduler {

    private final NotificationRepository notificationRepository;
    private static final Logger log = LoggerFactory.getLogger(NotificationCleanupScheduler.class);


    public NotificationCleanupScheduler(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void deleteOldNotifications() {
        LocalDateTime threshold = LocalDateTime.now().minusDays(7);
        Integer deletedCount = notificationRepository.deleteAllByCreatedAtBefore(threshold);
        log.info("Deleted {} old notifications.", deletedCount);
    }
}
