package cinema;

import java.util.UUID;

public class Theater {

	private UUID id;
	private String name;
	private int numberOfSeats;
	private TheaterCategory category;
	
	public Theater() {
		id = UUID.randomUUID();
	}

	public Theater setName(String name) {
		this.name = name;
		return this;
	}
	
	public Theater setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
		return this;
	}
	
	public Theater setCategory(TheaterCategory category) {
		this.category = category;
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	
	public TheaterCategory getCategory() {
		return category;
	}
}
