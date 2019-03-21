package cinema;

import java.util.Date;
import java.util.UUID;

public class Show {

	private UUID id;
	private Film film;
	private Theater theater;
	private Date date;
	private int ticketsAvailable;
	private boolean isAvailable;
	
	
	public Show() {
		id = UUID.randomUUID();
		isAvailable = true;
	}
	
	public Show setFilm(Film film) {
		this.film = film;
		return this;
	}
	
	public Show setTheater(Theater theater) {
		this.theater = theater;
		return this;
	}
	
	public Show setDate(Date date) {
		this.date = date;
		return this;
	}
	
	
	public Show setTicketsAvailable(int ticketsAvailable) {
		this.ticketsAvailable = ticketsAvailable;
		return this;
	}

	public Film getFilm() {
		return film;
	}
	
	public Theater getTheater() {
		return theater;
	}
	
	public Date getDate() {
		return date;
	}
	
	public int getTicketsAvailable() {
		return ticketsAvailable;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
}
