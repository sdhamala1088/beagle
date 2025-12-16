package per.app.port.dto;

import per.app.port.PortOfEntry;

public class PortRequest implements PortOfEntryConverter {

	private String code;
	
	private String name;
	
	private String country;
	
	private String state;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public PortOfEntry toPortOfEntry() {
		PortOfEntry portOfEntry = new PortOfEntry();
		portOfEntry.setName(name);
		portOfEntry.setCountry(country);
		portOfEntry.setState(state);
		portOfEntry.setCode(code);
		return portOfEntry;
	}
}
