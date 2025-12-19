package per.app.crossing;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrossingRepository extends JpaRepository<CrossingEvent, Long> {
	
	Page<CrossingEvent> findByTravelerId(Long travelerId, Pageable pageable);
	
	Page<CrossingEvent> findByPortEntryId(Long portId, Pageable pageable);
	
	Page<CrossingEvent> findByTravelerIdAndPortOfEntryId(Long travelerId, Long portOfEntryId, Pageable pageable);
}
