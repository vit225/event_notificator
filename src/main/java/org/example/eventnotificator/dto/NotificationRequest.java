package org.example.eventnotificator.dto;

import java.util.List;

public class NotificationRequest {

    List<Integer> notificationIds;

    public NotificationRequest() {
    }

    public NotificationRequest(List<Integer> notificationIds) {
        this.notificationIds = notificationIds;
    }

    public List<Integer> getNotificationIds() {
        return notificationIds;
    }

    public void setNotificationIds(List<Integer> notificationIds) {
        this.notificationIds = notificationIds;
    }
}
