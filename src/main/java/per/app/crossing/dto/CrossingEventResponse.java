package per.app.crossing.dto;

import java.time.LocalDateTime;

import per.app.crossing.CrossingEvent;

public record CrossingEventResponse (
		Long id,
        Long travelerId,
        String travelerName,
        Long portId,
        String portCode,
        String portName,
        CrossingEvent.Direction direction,
		CrossingEvent.Mode mode,
		LocalDateTime crossingTime,
		String vehiclePlate,
        String notes
) {}
