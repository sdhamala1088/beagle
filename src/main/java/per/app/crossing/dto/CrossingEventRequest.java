package per.app.crossing.dto;

import java.time.LocalDateTime;

import per.app.crossing.CrossingEvent;

public record CrossingEventRequest (
	Long travelerId,
    Long portId,
    per.app.crossing.CrossingEvent.Direction direction,
    CrossingEvent.Mode mode,
    LocalDateTime crossingTime,
    String vehiclePlate,
    String notes
) {}
