package per.app.port;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import per.app.port.PortOfEntry;

public interface PortRepository extends JpaRepository<PortOfEntry, Long>, JpaSpecificationExecutor<PortOfEntry>{

}
