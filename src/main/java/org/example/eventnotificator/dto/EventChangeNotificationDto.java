package org.example.eventnotificator.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventChangeNotificationDto {

    private Integer eventId;
    private FieldChangeString name;
    private FieldChangeInteger maxPlaces;
    private FieldChangeDateTime date;
    private FieldChangeInteger cost;
    private FieldChangeInteger duration;
    private FieldChangeInteger locationId;

    public EventChangeNotificationDto() {
    }

    public EventChangeNotificationDto(
            Integer eventId,
            FieldChangeString name,
            FieldChangeInteger maxPlaces,
            FieldChangeDateTime date,
            FieldChangeInteger cost,
            FieldChangeInteger duration,
            FieldChangeInteger locationId) {
        this.eventId = eventId;
        this.name = name;
        this.maxPlaces = maxPlaces;
        this.date = date;
        this.cost = cost;
        this.duration = duration;
        this.locationId = locationId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public FieldChangeString getName() {
        return name;
    }

    public void setName(FieldChangeString name) {
        this.name = name;
    }

    public FieldChangeInteger getMaxPlaces() {
        return maxPlaces;
    }

    public void setMaxPlaces(FieldChangeInteger maxPlaces) {
        this.maxPlaces = maxPlaces;
    }

    public FieldChangeDateTime getDate() {
        return date;
    }

    public void setDate(FieldChangeDateTime date) {
        this.date = date;
    }

    public FieldChangeInteger getCost() {
        return cost;
    }

    public void setCost(FieldChangeInteger cost) {
        this.cost = cost;
    }

    public FieldChangeInteger getDuration() {
        return duration;
    }

    public void setDuration(FieldChangeInteger duration) {
        this.duration = duration;
    }

    public FieldChangeInteger getLocationId() {
        return locationId;
    }

    public void setLocationId(FieldChangeInteger locationId) {
        this.locationId = locationId;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FieldChangeString {
        private String oldField;
        private String newField;

        public FieldChangeString() {
        }

        public FieldChangeString(String oldField, String newField) {
            this.oldField = oldField;
            this.newField = newField;
        }

        public String getOldField() {
            return oldField;
        }

        public void setOldField(String oldField) {
            this.oldField = oldField;
        }

        public String getNewField() {
            return newField;
        }

        public void setNewField(String newField) {
            this.newField = newField;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FieldChangeInteger {
        private Integer oldField;
        private Integer newField;

        public FieldChangeInteger() {
        }

        public FieldChangeInteger(Integer oldField, Integer newField) {
            this.oldField = oldField;
            this.newField = newField;
        }

        public Integer getOldField() {
            return oldField;
        }

        public void setOldField(Integer oldField) {
            this.oldField = oldField;
        }

        public Integer getNewField() {
            return newField;
        }

        public void setNewField(Integer newField) {
            this.newField = newField;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class FieldChangeDateTime {
        private String oldField; // формат ISO-8601 "YYYY-MM-DDThh:mm:ss"
        private String newField;

        public FieldChangeDateTime() {
        }

        public FieldChangeDateTime(String oldField, String newField) {
            this.oldField = oldField;
            this.newField = newField;
        }

        public String getOldField() {
            return oldField;
        }

        public void setOldField(String oldField) {
            this.oldField = oldField;
        }

        public String getNewField() {
            return newField;
        }

        public void setNewField(String newField) {
            this.newField = newField;
        }
    }
}
