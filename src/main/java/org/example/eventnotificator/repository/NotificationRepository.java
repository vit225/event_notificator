package org.example.eventnotificator.repository;

import org.example.eventnotificator.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {

    List<NotificationEntity> findByUserIdAndReadFalse(Integer userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM NotificationEntity n WHERE n.createdAt < :threshold")
    int deleteAllByCreatedAtBefore(@Param("threshold") LocalDateTime threshold);

    List<NotificationEntity> findByUserIdAndIdIn(Integer userId, List<Integer> ids);
}
