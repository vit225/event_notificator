package org.example.eventnotificator.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.example.eventnotificator.kafka.KafkaEvent;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "notification")

public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "user_id")
    private Integer userId;

    @Type(JsonType.class)
    @Column(name = "changes_json", columnDefinition = "jsonb")
    private Map<String, KafkaEvent.ChangedField> changesJson;

    @Column(name = "is_read")
    private boolean read;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public NotificationEntity() {
    }

    public NotificationEntity(
            Integer id,
            Integer eventId,
            Integer userId,
            Map<String, KafkaEvent.ChangedField> changesJson,
            boolean read,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.changesJson = changesJson;
        this.read = read;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Map<String, KafkaEvent.ChangedField> getChangesJson() {
        return changesJson;
    }

    public void setChangesJson(Map<String, KafkaEvent.ChangedField> changesJson) {
        this.changesJson = changesJson;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
