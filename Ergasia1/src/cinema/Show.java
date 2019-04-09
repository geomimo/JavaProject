package cinema;

import java.util.UUID;

public class Show {

	private UUID id;
	private Film film;
	private Theater theater;
	private String date;
	private int ticketsAvailable;
	private boolean isAvailable;
	
	
	public Show() {
		id = UUID.randomUUID();
		isAvailable = true;
	}

	public Show setId(UUID id) {
		this.id = id;
		return this;
	}
	
	public Show setFilm(Film film) {
		this.film = film;
		return this;
	}
	
	public Show setTheater(Theater theater) {
		this.theater = theater;
		return this;
	}
	
	public Show setDate(String date) {
		this.date = date;
		return this;
	}
		
	public Show setTicketsAvailable(int ticketsAvailable) {
		this.ticketsAvailable = ticketsAvailable;
		return this;
	}
	
	public Show setIsAvailable(boolean available) {
		this.isAvailable = available;
		return this;
	}

	public UUID getId() {
		return id;
	}
	
	public Film getFilm() {
		return film;
	}
	
	public Theater getTheater() {
		return theater;
	}
	
	public String getDate() {
		return date;
	}
	
	public int getTicketsAvailable() {
		return ticketsAvailable;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
	
	public void decreaseTickets(int n) {
		ticketsAvailable -= n;
		if(ticketsAvailable == 0) isAvailable = false;
	}

	
}
