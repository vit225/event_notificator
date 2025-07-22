package org.example.eventnotificator.controller;

import org.example.eventnotificator.converter.NotificationDtoConverter;
import org.example.eventnotificator.domain.Notification;
import org.example.eventnotificator.dto.EventChangeNotificationDto;
import org.example.eventnotificator.dto.NotificationRequest;
import org.example.eventnotificator.security.CustomUserDetail;
import org.example.eventnotificator.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationDtoConverter converter;

    public NotificationController(NotificationService notificationService, NotificationDtoConverter converter) {
        this.notificationService = notificationService;
        this.converter = converter;
    }


    @GetMapping
    public ResponseEntity<List<EventChangeNotificationDto>> getUnreadNotifications() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetail customUserDetail)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Notification> unreadNotifications = notificationService.getUnreadNotifications(customUserDetail.getId());

        return ResponseEntity.ok(unreadNotifications.stream()
                .map(converter::toDto)
                .toList());
    }

    @PostMapping
    public ResponseEntity<Void> markAsRead(@RequestBody NotificationRequest notificationIds) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetail customUserDetail)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        notificationService.markNotificationsAsRead(customUserDetail.getId(), notificationIds.getNotificationIds());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
