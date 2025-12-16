package per.app.port;

import java.util.List;

import org.springframework.stereotype.Service;

import per.app.port.dto.PortRequest;
import per.app.port.dto.PortResponse;

@Service
public class PortService {

    private final PortRepository portRepository;
		
	public PortService(PortRepository portRepository) {
		this.portRepository = portRepository;
	}

	public List<PortResponse> getAllPorts() {
		return portRepository.findAll().stream().map(this::toResponse).toList();
	}
	
	public PortResponse toResponse(PortOfEntry portOfEntry) {
		PortResponse portResponse = new PortResponse();
		portResponse.setId(portOfEntry.getId());
		portResponse.setCode(portOfEntry.getCode());
		portResponse.setCountry(portOfEntry.getCountry());
		portResponse.setName(portOfEntry.getName());
		portResponse.setState(portOfEntry.getState());
		return portResponse;
	}

	public PortResponse createPortOfEntry(PortRequest portRequest) {
		PortOfEntry poe = portRequest.toPortOfEntry();
		PortOfEntry saved = portRepository.save(poe);
		return toPortResponse(saved);
	}

	private PortResponse toPortResponse(PortOfEntry saved) {
		PortResponse portResponse = new PortResponse();
		portResponse.setName(saved.getName());
		portResponse.setCode(saved.getCode());
		portResponse.setCountry(saved.getCountry());
		portResponse.setState(saved.getState());
		return portResponse;
	}
}
