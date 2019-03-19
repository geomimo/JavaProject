package cinema;


public class Theater {

	private int id;
	private int numberOfSeats;
	private TheaterCategory category;
	
	public Theater() {
		//set id
	}

	public Theater setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
		return this;
	}
	
	public Theater setCategory(TheaterCategory category) {
		this.category = category;
		return this;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getNumberOfSeats() {
		return this.numberOfSeats;
	}
	
	public TheaterCategory getCategory() {
		return this.category;
	}
}
