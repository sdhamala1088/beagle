package per.app.traveler;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import per.app.traveler.dto.TravelerRequest;
import per.app.traveler.dto.TravelerResponse;

@RestController
@RequestMapping("/api/travelers")
public class TravelerController {
	
	private final TravelerService travelerService;
	
	public TravelerController(TravelerService travelerService) {
		this.travelerService = travelerService;
	}
	
	@GetMapping
	public Page<TravelerResponse> searchTravelers(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String nationality,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		
		return travelerService.searchTravelers(name, nationality, page, size);
	}
	
	@GetMapping("/{id}")
	public TravelerResponse getTraveler(@PathVariable Long id) {
		
		return travelerService.getTraveler(id)
				.orElseThrow(() -> new RuntimeException("Traveler not found " + id));
	}
	
	@PostMapping
	public TravelerResponse createTraveler(@RequestBody TravelerRequest travelerRequest) {
		return travelerService.createTraveler(travelerRequest);
	}
	
	@PutMapping("/{id}")
	public TravelerResponse updateTraveler(@PathVariable Long id, @RequestBody TravelerRequest travelerRequest) {
		return travelerService.updateTraveler(id, travelerRequest)
				.orElseThrow(() -> new RuntimeException("Traveler not found " + id));
	}
	
	@DeleteMapping("/{id}")
	public void deleteTraveler(@PathVariable Long id) {
		travelerService.deleteTraveler(id);
	}
}
