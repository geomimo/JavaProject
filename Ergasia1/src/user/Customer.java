package user;

import cinema.*;

public class Customer extends User {
	
	private int numOfSeenFilms;
	
	public Customer() {
		numOfSeenFilms = 0;
	}
		
	
	public void makeReservation(Show show, int numberOfTickets, String reservedName) {
		if(show.isAvailable()) {
			if(show.getTicketsAvailable() >= numberOfTickets) {
				System.out.println("You succesfully made a reservation " + reservedName);
				numOfSeenFilms++;
			}else {
				System.out.println("Not enough tickets.");
			}
		}else {
			System.out.println("Full theater!");
		}
	}
	
	public void showAvailableFilms() {
		//TODO Schedule
		System.out.println("These are the available films today:");
	}
	
	public void viewReservation() {
		//Find reservation and print
		System.out.println("Your reservation:");
	}
}
