package per.app.crossing;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import per.app.port.PortOfEntry;
import per.app.traveler.Traveler;

@Entity
@Table(name = "crossing_events")
public class CrossingEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "traveler_id", nullable = false)
	private Traveler traveler;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "port_id", nullable = false)
	private PortOfEntry portOfEntry;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Direction direction;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Mode mode;
	
	@Column(nullable = false)
	private LocalDateTime crossingTime;
	
	private String vehiclePlate;
	
	@Column(length = 2000)
	private String notes;
	
	public enum Direction {
		ENTRY,
		EXIT
	}
	
	public enum Mode {
		PEDESTRIAN,
		VEHICLE,
		AIR,
		SEA
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Traveler getTraveler() {
		return traveler;
	}

	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}

	public PortOfEntry getPortOfEntry() {
		return portOfEntry;
	}

	public void setPortOfEntry(PortOfEntry portOfEntry) {
		this.portOfEntry = portOfEntry;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public LocalDateTime getCrossingTime() {
		return crossingTime;
	}

	public void setCrossingTime(LocalDateTime crossingTime) {
		this.crossingTime = crossingTime;
	}

	public String getVehiclePlate() {
		return vehiclePlate;
	}

	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
