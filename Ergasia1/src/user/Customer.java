package user;

public class Customer extends User {
	private String numOfSeenFilms;

	
	public Customer() {
		
		
		}
	
	
	public Customer setNumOfSeenFilms(String numOfSeenFilms) {
		this.numOfSeenFilms=numOfSeenFilms;
		return this;
	}

	public String Customer getNumOfSeenFilms() {
		return this.numOfSeenFilms;
	}
	
	public void increaseSeenFilms() {
		this.numOfSeenFilms +=1;
	}
	
	private void makeReservation() {
		System.out.println("You succesfully made a reservation");
	}
	private void showAvailableFilms() {
		System.out.println("These are the available films today:");
	}
	private void viewReservation() {
		System.out.println("Your reservation:");
	}
}
