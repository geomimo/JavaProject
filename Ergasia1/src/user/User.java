package user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.UUID;

import cinema.Film;
import cinema.FilmCategory;
import cinema.Reservation;
import cinema.Show;
import cinema.Theater;
import cinema.TheaterCategory;
import mainpackage.ProjectWeb1;

public class User {
	
	protected UUID id;
	protected String name;
	protected String username;
	protected String password;
	protected int age;
	protected Property property;
	protected String phoneNumber;

	
	//Constructor, setters, getters.
	
	public User() {
		this.id = UUID.randomUUID();
	}
	
	public User setId(UUID id) {
		this.id = id;
		return this;
	}
	
	public User setName(String name) {
		this.name=name;
		return this;
	}
	
	public User setUsername(String username) {
		this.username = username;
		return this;
	}
	
	public User setPassword(String password) {
		this.password=password;
		return this;
	}
	
	public User setAge(int age) {
		this.age=age;
		return this;
	}
	
	public User setProperty(Property property) {
		this.property=property;
		return this;
	}
	
	public User setPhoneNumber(String phoneNumber) {
		this.phoneNumber=phoneNumber;
		return this;
	}
	
	public UUID getId() {
		return id;
	}
		
	public String getName() {
		return name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getAge() {
		return age;
	}
	
	public Property getProperty() {
		return property;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
		
	
	/**
	 * Searches db, authenticates and sets the user's properties. 
	 * @param username
	 * @param password
	 * @return True if user found, else false.
	 */
	public boolean login(String username, String password) {
		try {	
			FileInputStream fstream = new FileInputStream("db.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String input;

			while ((input = br.readLine()) != null)   {
			  String[] data = input.split(","); //data format -> [ UUID, name , username, password, age, property, phone number]
			  
			  if (data[2].equals(username) && data[3].equals(password)) {
				  id = UUID.fromString(data[0]);
				  name = data[1];
				  username = data[2];
				  password = data[3];
				  age = Integer.parseInt(data[4]);
				  switch (data[5]) {
				  case "Admin":
					  property = Property.Admin;
					  break;
				  case "ContentAdmin":
					  property = Property.ContentAdmin;
					  break;
				  case "Customer":
					  property = Property.Customer;
					  break;
				  }
				  phoneNumber = data[6];
				  
				  br.close();
				  fstream.close();
				  System.out.println("Successfully logged in!");
				  return true;
			  }			  
			}
			br.close();
			fstream.close();
		}catch(IOException e) {
			System.out.println("IO error. " + e);
		}		
		System.out.println("Log in failed! Please try again.");			
		return false;
	}
	
	
	/**
	 * Just a log out message.
	 */
	public void logout() {	
		System.out.println("Goodbye!");
	}
	
	
	/**
	 * Checks and asks for a valid input and returns null or the input.
	 * @param print the message to print
	 * @return Null for 'exit', otherwise the input.
	 */
	protected String getInputOrExit(String print) {
		System.out.println(print);
		String input;
		do {
			input = ProjectWeb1.keyboard.nextLine();
			if(!input.matches("[a-zA-Z0-9]?[ a-zA-Z0-9./]*")){
				System.out.println("Wrong format. Use only a-z, A-Z, 0-9, '.', '/");
			}else {
				break;
			}
		}while(true);
		return input.toLowerCase().equals("exit") ? null : input;
	}
	
	
	/**
	 * Searches and returns a film.
	 * @param inp An id or a film title.
	 * @return The film.
	 */
	protected Film getFilm(String inp){		
		try {
			FileInputStream fstream = new FileInputStream("filmdb.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String line;
			
			while((line = br.readLine()) != null) {
				
				String[] data = line.split(",");//data format: [ film id, film title, duration, description, category ]
				if(data[0].equals(inp) || data[1].equals(inp)){ //data[0] for id or data[1] for film title.
					Film film = new Film()						
							.setId(UUID.fromString(data[0]))
							.setTitle(data[1])
							.setDuration(Integer.parseInt(data[2]))
							.setDescription(data[3]);
					switch (data[4]) {
					case "Horror":
						film.setCategory(FilmCategory.Horror);
						break;							
					case "Action":
						film.setCategory(FilmCategory.Action);
						break;
					case "Comedy":
						film.setCategory(FilmCategory.Comedy);
						break;
					case "Drama":
						film.setCategory(FilmCategory.Drama);
						break;
					case "Scifi":
						film.setCategory(FilmCategory.Scifi);
						break;
					case "Porn":
						film.setCategory(FilmCategory.Porn);
						break;
					case "Documentary":
						film.setCategory(FilmCategory.Documentary);
						break;
					case "Adventure":
						film.setCategory(FilmCategory.Adventure);
						break;
					case "Thriller":
						film.setCategory(FilmCategory.Thriller);
						break;
					}
					br.close();
					fstream.close();
					return film;
				}
			}
			br.close();
			fstream.close();
		}catch(IOException e) {
			System.out.println("Getting error " + e);
		}
		
		return null; //not found.
	}
	
	
	
	/**
	 * Searches and returns a theater.
	 * @param inp An id or a theater name.
	 * @return The theater
	 */
	protected Theater getTheater(String inp) {
		try {
			FileInputStream fstream = new FileInputStream("theaterdb.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String line;
			
			while ((line = br.readLine()) != null) {
				
				String[] data = line.split(","); //data format: [ theater id , theater name, number of seats, category ]
				if (data[0].equals(inp) || data[1].equals(inp)) { //data[0] for id, data[1] for theater name.
					Theater theater = new Theater()
							.setId(UUID.fromString(data[0]))
							.setName(data[1])
							.setNumberOfSeats(Integer.parseInt(data[2]));
					switch (data[3]) {
					case "NORMAL":
						theater.setCategory(TheaterCategory.NORMAL);
						break;
					case "THREE_D":
						theater.setCategory(TheaterCategory.THREE_D);
						break;
					case "TWO_D":
						theater.setCategory(TheaterCategory.TWO_D);
						break;
					case "IMAX":
						theater.setCategory(TheaterCategory.IMAX);
						break;
					case "DOLBY_ATMOS":
						theater.setCategory(TheaterCategory.DOLBY_ATMOS);
						break;
					}
					br.close();
					fstream.close();
					return theater;
				}
			}
			br.close();
			fstream.close();			
		}catch(IOException e) {
			System.out.println("Getting error " + e);
		}
		return null; // not found.
	}

	
	/**
	 * Searches and returns a show. 
	 * Major characteristics of a show are ( film, theater and date ) or ( show id ).
	 * Overloaded.
	 * @param filmTitle
	 * @param theaterName
	 * @param date
	 * @return The show.
	 */
	protected Show getShow(String filmTitle, String theaterName, String date) {
		Film film;
		Theater theater ;
		
		if((film = getFilm(filmTitle)) == null) {
			return null;
		}
		
		if((theater = getTheater(theaterName)) == null) {
			return null;
		}
			
		try {		
			FileInputStream fstream = new FileInputStream("showdb.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String line;
			while((line = br.readLine()) != null) {
				String[] data = line.split(","); //data format: [ show id, film id, theater id, date, tickets available, is available ]
				if(data[1].equals(film.getId().toString()) && data[2].equals(theater.getId().toString()) && data[3].equals(date)) {
					Show show = new Show()
							.setId(UUID.fromString(data[0]))
							.setFilm(film)
							.setTheater(theater)
							.setDate(date)
							.setTicketsAvailable(Integer.parseInt(data[4]))
							.setIsAvailable(Boolean.getBoolean(line));
					br.close();
					fstream.close();
					return show;			
				}
			}
			br.close();
			fstream.close();		
		}catch(IOException e) {
			System.out.println("Getting error " + e);
		}
		return null;// not found.
	}
	
	/**
	 * Searches and returns a show. 
	 * Major characteristics of a show are ( film, theater and date ) or ( show id ).
	 * Overloaded.
	 * @param showId
	 * @return The show.
	 */
	protected Show getShow(String showId) {
		try {
			FileInputStream fstream = new FileInputStream("showdb.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String line;
			
			Film film;
			Theater theater;
			
			while((line = br.readLine()) != null) {
				String[] data = line.split(",");//data format: [ show id, film id, theater id, date, tickets available, is available ]
				if(data[0].equals(showId)) {		
					film = getFilm(data[1]);
					theater = getTheater(data[2]);
					Show show = new Show()
							.setId(UUID.fromString(data[0]))
							.setFilm(film)
							.setTheater(theater)
							.setDate(data[3])
							.setTicketsAvailable(Integer.parseInt(data[4]))
							.setIsAvailable(Boolean.getBoolean(line));
					
					br.close();
					fstream.close();
					return show;			
				}
			}
			br.close();
			fstream.close();		
		}catch(IOException e) {
			System.out.println("Getting error " + e);
		}
		return null;// not found.
	}
	
	
	/**
	 * Searches and returns a reservation.
	 * @param showId
	 * @return The reservation.
	 */
	protected Reservation getReservation(String showId) {
		try {
			FileInputStream fstream = new FileInputStream("reservationdb.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String line;
			
			Show show = getShow(showId);
			while ((line = br.readLine()) != null)   {
				String[] data = line.split(","); //data format: [ reservation id, show id, number of tickets, customer's id ]
				if (data[1].equals(showId) && data[3].equals(id.toString())) {
					Reservation reservation = new Reservation()
							.setId(UUID.fromString(data[0]))
							.setCustomerId(UUID.fromString(data[1]))
							.setNumberOfReservedTickets(Integer.parseInt(data[2]))
							.setShow(show);
					br.close();
					return reservation;
				}		  
			}		
			br.close();
			fstream.close();			
		}catch(IOException e) {
			System.out.println("Getting error " + e);
		}
		return null;// not found.
	}
		
	
	/**
	 * Saves an instance of user/film/theater/show/reservation.
	 * @param obj A instacne of user/film/theater/show/reservation.
	 * @param file The file for storage.
	 */
	protected<T> void saveObject(T obj, String file) {
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));
			String data = "";
			if(file.equals("db.txt")) {
				User user = (User)obj;
				data = user.id + "," + 
					   user.getName() + "," + 
					   user.getUsername() + "," + 
					   user.getPassword() + "," +
					   user.getAge() + "," +
					   user.getProperty() + "," +
					   user.getPhoneNumber() + "\n";
				
			}else if(file.equals("filmdb.txt")) {
				Film film = (Film)obj;
				data = film.getId() + "," +
					   film.getTitle() + "," +
					   film.getDuration() + "," +
					   film.getDescription() + "," +
					   film.getCategory() + "\n";
				
			}else if(file.equals("theaterdb.txt")) {
				Theater theater = (Theater)obj;
				data = theater.getId() + "," +
					   theater.getName() + "," +
					   theater.getNumberOfSeats() + "," +
					   theater.getCategory() + "\n";
				
			}else if(file.equals("showdb.txt")) {
				Show show = (Show)obj;
				data = show.getId() + "," +
					   show.getFilm().getId() + "," +
					   show.getTheater().getId() + "," +
					   show.getDate() + "," +
					   show.getTicketsAvailable() + "," +
					   show.isAvailable() + "\n";
				
			}else if(file.equals("reservationdb.txt")) {
				Reservation reservation = (Reservation)obj;
				data = reservation.getId() + "," +
					   reservation.getShow().getId() + "," +
					   reservation.getNumberOfReservedSeats() + "," +
					   reservation.getCustomerId() + "\n";
			}
			
			writer.write(data);				
			writer.close();		
		}catch(IOException e) {
			System.out.println("Saving error. " + e);
		}
	}
	
	
	/**
	 *  Deletes a record from a specific file/db.
	 *  Copies the file, without the object we want to delete. Then deletes the old file and renames the new.
	 * @param inp An id of an instance.
	 * @param file The file in which the data is recorded.
	 * @param verbose Print messages.
	 */
	protected void deleteObject(String inp, String file, boolean verbose) {
		boolean deleted = false;
		try {
			FileInputStream finstream = new FileInputStream(file);//old file
			BufferedReader br = new BufferedReader(new InputStreamReader(finstream));
			
			FileOutputStream foutstream = new FileOutputStream("temp.txt");//new file
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(foutstream));
			
			String line;
			while ((line = br.readLine()) != null)   {
				String[] data = line.split(",");
				if (data[0].equals(inp) || data[1].equals(inp) || data[2].equals(inp)) { //skip the object we want to delete.
					deleted = true;
					continue;
				}
				else {
					bw.write(line + "\n");
				}			  
			}
			bw.close();			
			foutstream.close();
			finstream.close();
		}catch(IOException e) {
			System.out.println("Deleting error " + e);
		}
		
		File oldFile = new File(file);
		File newFile = new File("temp.txt");
		
		try {
			Files.delete(oldFile.toPath());
			Files.move(newFile.toPath(), oldFile.toPath());			
			if(verbose) System.out.println(deleted ? "Deleted!" : "Not Found.");
		}catch(IOException e) {
			System.out.println("Renaming error " + e);
		}
	
	}
	
	
}


