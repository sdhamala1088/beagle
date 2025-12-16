package per.app.port;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import per.app.port.dto.PortRequest;
import per.app.port.dto.PortResponse;

@RestController
@RequestMapping("/api/ports")
public class PortController {
	
	private final PortService portService;
	
	public PortController(PortService portService) {
		this.portService = portService;
	}
	
	@GetMapping
	public List<PortResponse> getAllPorts() {
		return portService.getAllPorts();
	}
	
	@PostMapping
	public PortResponse createPortOfEntry(@RequestBody PortRequest portRequest) {
		return portService.createPortOfEntry(portRequest);
	}
}