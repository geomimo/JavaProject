package user;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


import cinema.*;

public class Customer extends User {
	
	
	public Customer(User user) {
		this.id = user.id;
		this.name = user.name;
		this.username = user.username;
		this.password = user.password;
		this.age = user.age;
		this.property = Property.Admin;
		this.phoneNumber = user.phoneNumber;
	}
		
	/**
	 * Creates a new reservation.
	 */
	public void makeReservation() {
		Show show = new Show();
		System.out.println("Type 'exit' anytime to exit.");
		String input, filmTitle, theaterName, date;
		
		if((input = getInputOrExit("Give film title.")) != null) { //checks for valid input or 'exit' and returns it	
			filmTitle = input;
		}else {
			return;
		}
		
		if((input = getInputOrExit("Give theater name.")) != null) {
			theaterName = input;
		}else {
			return;
		}
		
		if((input = getInputOrExit("Give date. (DD/MM/YYYY)")) != null) {
			date = input;
		}else {
			return;
		}
		
		if((show = getShow(filmTitle, theaterName, date)) == null) {
			System.out.println("Show not found.");
			return;
		}
		
		int numberOfTickets;	
		do {
			if((input = getInputOrExit("Give number of tickets.")) != null) {
				if(input.matches("[1-9]+[0-9]*")) {
					numberOfTickets = Integer.parseInt(input);	
					break;
				}else {
					System.out.println("Wrong format. Use only 0-9");
				}
			}else {
				return;
			}		
		}while(true);
		
		
		// Check for tickets.	 
		if(show.isAvailable()) {
			if(show.getTicketsAvailable() >= numberOfTickets) {
				
				Reservation reservation = new Reservation(id, numberOfTickets, show);				
				saveObject(reservation, "reservationdb.txt");
				
				//Decrease tickets of the show and update the db.
				show.decreaseTickets(numberOfTickets);	
				if(show.getTicketsAvailable() == 0) show.setIsAvailable(false);
				deleteObject(show.getId().toString(),"showdb.txt", false);
				saveObject(show, "showdb.txt");			
				
				System.out.println("You succesfully made a reservation.");
			}else {
				System.out.println("Not enough tickets.");
			}
		}else {
			System.out.println("Full theater!");
		}			
	}
	
	
	/**
	 * Shows all available shows.
	 */
	public void viewAvailableShows() {
		String line;
		String output = "";
		
		try {
			FileInputStream fstream = new FileInputStream("showdb.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				Film film = new Film();
				Theater theater = new Theater();
				film = getFilm(data[1]);
				theater = getTheater(data[2]);
				
				output = "film title = " + film.getTitle()
						+ " theater name = " + theater.getName()
						+ " date = " + data[3]
						+ " ticketsAvailable = " + data[4];				
				if(output.length() != 0) {
					System.out.println(output);				
				}else {
					System.out.println("Empty Database");			
				}
			}			
			br.close();
			fstream.close();			
		}catch(IOException e) {
			System.out.println("View error. " + e);
		}
	}
	
	
	/**
	 * Shows all reservations made by the customer.
	 */
	public void viewReservations() {
		String line;
		String output = "";	
		
		try {
			FileInputStream fstream = new FileInputStream("reservationdb.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				
				if(!data[3].equals(id.toString())) continue; //Show only this customer's reservations.
				Show show = getShow(data[1]);
				output = "film title = " + show.getFilm().getTitle()
						+ " theater name = " + show.getTheater().getName()
						+ " date = " + show.getDate()
						+ " tickets = " + data[2];				
				if(output.length() != 0) {
					System.out.println(output);				
				}else {
					System.out.println("Empty Database");			
				}
			}
			br.close();
			fstream.close();				
		}catch(IOException e) {
			System.out.println("View error. " + e);
		}
	}

	
	/**
	 * Cancels a reservation
	 */
	public void cancelReservation() {
		System.out.println("Type 'exit' anytime to exit.");
		String input, filmTitle, theaterName, date;
		
		if((input = getInputOrExit("Give film title.")) != null) {//checks for valid input or 'exit' and returns it	
			filmTitle = input;
		}else {
			return;
		}
		
		if((input = getInputOrExit("Give theater name.")) != null) {
			theaterName = input;
		}else {
			return;
		}
		
		if((input = getInputOrExit("Give date. (DD/MM/YYYY)")) != null) {
			date = input;
		}else {
			return;
		}
		
		Show show;
		Reservation reservation;
		
		if((show = getShow(filmTitle, theaterName, date)) == null) {
			System.out.println("Show not found.");
			return;
		}
		
		if((reservation = getReservation(show.getId().toString())) == null){
			System.out.println("Reservation not found.");
			return;
		}
		
		show.setTicketsAvailable(show.getTicketsAvailable() + reservation.getNumberOfReservedSeats()); //Increase tickets and update db
		deleteObject(show.getId().toString(), "showdb.txt", false);
		saveObject(show, "showdb.txt");
		deleteObject(reservation.getId().toString(), "reservationdb.txt", true);
	}
	
	
}
