package per.app.traveler;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import per.app.traveler.dto.TravelerRequest;
import per.app.traveler.dto.TravelerResponse;

@Service
public class TravelerService {
	
	private final TravelerRepository travelerRepository;
	
	public TravelerService(TravelerRepository travelerRepository) {
		this.travelerRepository = travelerRepository;
	}

	public Page<TravelerResponse> searchTravelers(String name, String nationality, int page, int size) {
		PageRequest pageable = PageRequest.of(page, size, Sort.by("lastName").ascending());
		
		Specification<Traveler> spec = (root, query, cb) -> cb.conjunction();
		
		if (name != null && !name.isBlank()) {
			spec = spec.and(TravelerSpecifications.nameContains(name));
		}
		
		if (nationality != null && !nationality.isBlank()) {
			spec = spec.and(TravelerSpecifications.nationalityIs(nationality));
		}
		
		Page<Traveler> searchedTravelers = travelerRepository.findAll(spec, pageable);
		return searchedTravelers.map(this::toResponse);
	}

	public Optional<TravelerResponse> getTraveler(Long id) {
        return travelerRepository.findById(id).map(this::toResponse);
	}

	public TravelerResponse createTraveler(TravelerRequest travelerRequest) {
		Traveler traveler = new Traveler();
        traveler.setFirstName(travelerRequest.getFirstName());
        traveler.setLastName(travelerRequest.getLastName());
        traveler.setNationality(travelerRequest.getNationality());
        traveler.setDateOfBirth(travelerRequest.getDateOfBirth());
        traveler.setPassportNumber(travelerRequest.getPassportNumber());
        
        Traveler saved = travelerRepository.save(traveler);
        return toResponse(saved);
	}

	public Optional<TravelerResponse> updateTraveler(Long id, TravelerRequest travelerRequest) {
		return travelerRepository.findById(id).map(existing -> {
            existing.setFirstName(travelerRequest.getFirstName());
            existing.setLastName(travelerRequest.getLastName());
            existing.setNationality(travelerRequest.getNationality());
            existing.setDateOfBirth(travelerRequest.getDateOfBirth());
            existing.setPassportNumber(travelerRequest.getPassportNumber());
            return toResponse(travelerRepository.save(existing));
        });
	}

	public void deleteTraveler(Long id) {
		travelerRepository.deleteById(id);
	}
	
	private TravelerResponse toResponse(Traveler traveler) {
	    TravelerResponse res = new TravelerResponse();
	    res.setId(traveler.getId());
	    res.setFirstName(traveler.getFirstName());
	    res.setLastName(traveler.getLastName());
	    res.setNationality(traveler.getNationality());
	    res.setDateOfBirth(traveler.getDateOfBirth());
	    res.setPassportNumber(traveler.getPassportNumber());
	    return res;
	}
}
