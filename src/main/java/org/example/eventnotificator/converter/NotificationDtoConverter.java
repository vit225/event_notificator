package org.example.eventnotificator.converter;

import org.example.eventnotificator.domain.Notification;
import org.example.eventnotificator.dto.EventChangeNotificationDto;
import org.example.eventnotificator.kafka.KafkaEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificationDtoConverter {

    public EventChangeNotificationDto toDto(Notification notification) {
        Map<String, KafkaEvent.ChangedField> changesMap = notification.getChangesJson();

        EventChangeNotificationDto dto = new EventChangeNotificationDto();
        dto.setEventId(notification.getEventId());

        if (changesMap != null) {
            if (changesMap.containsKey("name")) {
                KafkaEvent.ChangedField changedField = changesMap.get("name");
                String oldField = (String) changedField.getOldValue();
                String newField = (String) changedField.getNewValue();
                dto.setName(new EventChangeNotificationDto.FieldChangeString(oldField, newField));
            }

            if (changesMap.containsKey("maxPlaces")) {
                KafkaEvent.ChangedField changedField = changesMap.get("maxPlaces");
                Integer oldField = changedField.getOldValue() != null ? ((Number) changedField.getOldValue()).intValue() : null;
                Integer newField = changedField.getNewValue() != null ? ((Number) changedField.getNewValue()).intValue() : null;
                dto.setMaxPlaces(new EventChangeNotificationDto.FieldChangeInteger(oldField, newField));
            }
            if (changesMap.containsKey("date")) {
                KafkaEvent.ChangedField changedField = changesMap.get("date");
                String oldField = (String) changedField.getOldValue();
                String newField = (String) changedField.getNewValue();
                dto.setDate(new EventChangeNotificationDto.FieldChangeDateTime(oldField, newField));
            }
            if (changesMap.containsKey("cost")) {
                KafkaEvent.ChangedField changedField = changesMap.get("cost");
                Integer oldField = changedField.getOldValue() != null ? ((Number) changedField.getOldValue()).intValue() : null;
                Integer newField = changedField.getNewValue() != null ? ((Number) changedField.getNewValue()).intValue() : null;
                dto.setCost(new EventChangeNotificationDto.FieldChangeInteger(oldField, newField));
            }
            if (changesMap.containsKey("duration")) {
                KafkaEvent.ChangedField changedField = changesMap.get("duration");
                Integer oldField = changedField.getOldValue() != null ? ((Number) changedField.getOldValue()).intValue() : null;
                Integer newField = changedField.getNewValue() != null ? ((Number) changedField.getNewValue()).intValue() : null;
                dto.setDuration(new EventChangeNotificationDto.FieldChangeInteger(oldField, newField));
            }
            if (changesMap.containsKey("locationId")) {
                KafkaEvent.ChangedField changedField = changesMap.get("locationId");
                Integer oldField = changedField.getOldValue() != null ? ((Number) changedField.getOldValue()).intValue() : null;
                Integer newField = changedField.getNewValue() != null ? ((Number) changedField.getNewValue()).intValue() : null;
                dto.setLocationId(new EventChangeNotificationDto.FieldChangeInteger(oldField, newField));
            }
        }
        return dto;
    }
}
