package net.UGA.springboot.dto;

public class PassengerDto {
	private Long id = -1L;
	private String firstName;
	private String lastName;
	private String nationality;
	private String destination;
	private String flightNumber;
	
	public PassengerDto() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
}
