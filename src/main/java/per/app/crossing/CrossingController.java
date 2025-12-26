package per.app.crossing;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import per.app.crossing.dto.CrossingEventRequest;
import per.app.crossing.dto.CrossingEventResponse;
import per.app.port.PortOfEntry;
import per.app.port.PortRepository;
import per.app.traveler.Traveler;
import per.app.traveler.TravelerRepository;

@RestController
@RequestMapping("/api/crossings")
public class CrossingController {

	private CrossingRepository crossingRepository;
	private PortRepository portRepository;
	private TravelerRepository travelerRepository;
	
	public CrossingController(CrossingRepository crossingRepository, PortRepository portRepository, TravelerRepository travelerRepository) {
		this.crossingRepository = crossingRepository;
		this.portRepository = portRepository;
		this.travelerRepository = travelerRepository;
	}
	
	@GetMapping
	public Page<CrossingEventResponse> listCrossing (
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(required = false) Long travelerId,
			@RequestParam(required = false) Long portOfEntryId) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<CrossingEvent> events;
		
		if (travelerId != null && portOfEntryId != null) {
			events = crossingRepository.findByTravelerIdAndPortOfEntryId(travelerId, portOfEntryId, pageable);
		} else if (travelerId != null ) {
			events = crossingRepository.findByTravelerId(travelerId, pageable);
		} else if (portOfEntryId != null) {
			events = crossingRepository.findByPortOfEntryId(portOfEntryId, pageable);
		} else {
			events = crossingRepository.findAll(pageable);
		}
		
		return events.map(this::toResponse);
	}
	
	@PostMapping
	public CrossingEventResponse createCrossingEvent(@RequestBody CrossingEventRequest crossingEventRequest) {
		Traveler traveler = travelerRepository.findById(crossingEventRequest.travelerId())
				.orElseThrow(() -> new IllegalArgumentException("Traveler with id " + crossingEventRequest.travelerId() + " not found."));
		PortOfEntry portOfEntry = portRepository.findById(crossingEventRequest.portId())
				.orElseThrow(() -> new IllegalArgumentException("Port of entry with id " + crossingEventRequest.portId() + " not found."));
		
		CrossingEvent crossingEvent = new CrossingEvent();
		crossingEvent.setCrossingTime(crossingEventRequest.crossingTime());
		crossingEvent.setDirection(crossingEventRequest.direction());
		crossingEvent.setMode(crossingEventRequest.mode());
		crossingEvent.setNotes(crossingEventRequest.notes());
		crossingEvent.setPortOfEntry(portOfEntry);
		crossingEvent.setTraveler(traveler);
		crossingEvent.setVehiclePlate(crossingEventRequest.vehiclePlate());
		
		CrossingEvent saved = crossingRepository.save(crossingEvent);
		return toResponse(saved);
	}
	
	
	
	private CrossingEventResponse toResponse(CrossingEvent crossingEvent) {
		String travelerName = crossingEvent.getTraveler().getFirstName() + " " + crossingEvent.getTraveler().getLastName();
        return new CrossingEventResponse(
                crossingEvent.getId(),
                crossingEvent.getTraveler().getId(),
                travelerName,
                crossingEvent.getPortOfEntry().getId(),
                crossingEvent.getPortOfEntry().getCode(),
                crossingEvent.getPortOfEntry().getName(),
                crossingEvent.getDirection(),
                crossingEvent.getMode(),
                crossingEvent.getCrossingTime(),
                crossingEvent.getVehiclePlate(),
                crossingEvent.getNotes()
        );
	}	
}
