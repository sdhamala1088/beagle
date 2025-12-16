package per.app.traveler;

import org.springframework.data.jpa.domain.Specification;

public class TravelerSpecifications {
	
	public static Specification<Traveler> nameContains(String name) {
		
		return (root, query, cb) -> {
            String pattern = "%" + name.toLowerCase() + "%";
            return cb.or(
            		cb.like(cb.lower(root.get("firstName")), pattern),
            		cb.like(cb.lower(root.get("lastName")), pattern));
		};
	}
	
	public static Specification<Traveler> nationalityIs(String nationality) {
		return (root, query, cb) ->
			cb.equal(cb.lower(root.get("nationality")), nationality.toLowerCase());
	}
}
