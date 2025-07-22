package org.example.eventnotificator.domain;

import org.example.eventnotificator.kafka.KafkaEvent;

import java.time.LocalDateTime;
import java.util.Map;

public class Notification {

    private Integer eventId;

    private Integer userId;

    private LocalDateTime createdAt;

    private boolean isRead;

    private Map<String, KafkaEvent.ChangedField> changesJson;

    public Notification() {
    }

    public Notification(Integer eventId, Integer userId, LocalDateTime createdAt, boolean isRead, Map<String, KafkaEvent.ChangedField> changesJson) {
        this.eventId = eventId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.isRead = isRead;
        this.changesJson = changesJson;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Map<String, KafkaEvent.ChangedField> getChangesJson() {
        return changesJson;
    }

    public void setChangesJson(Map<String, KafkaEvent.ChangedField> changesJson) {
        this.changesJson = changesJson;
    }
}
