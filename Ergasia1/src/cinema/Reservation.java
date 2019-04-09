package cinema;

import java.util.UUID;

public class Reservation {

	private UUID id;
	private UUID customerId;
	private int numberOfReservedSeats;
	private Show show;
	
	public Reservation() {
		
	}
	
	public Reservation(UUID customerId, int numberOfReservatedSeats, Show show) {
		this.id = UUID.randomUUID();
		this.customerId = customerId;
		this.numberOfReservedSeats = numberOfReservatedSeats;
		this.show = show;
	}
	
	public Reservation setId(UUID id) {
		this.id = id;
		return this;
	}
	
	public Reservation setCustomerId(UUID customerId) {
		this.customerId = customerId;
		return this;
	}
	
	public Reservation setNumberOfReservedTickets(int numberOfReservedSeats) {
		this.numberOfReservedSeats = numberOfReservedSeats;
		return this;
	}
	
	public Reservation setShow(Show show) {
		this.show = show;
		return this;
	}
	
	public UUID getId() {
		return id;
	}
	
	public UUID getCustomerId() {
		return customerId;
	}
	
	public int getNumberOfReservedSeats() {
		return numberOfReservedSeats;
	}
		
	public Show getShow() {
		return show;
	}
		
}
