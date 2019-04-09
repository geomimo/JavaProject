package user;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import cinema.Film;
import cinema.FilmCategory;
import cinema.Show;
import cinema.Theater;
import cinema.TheaterCategory;


public class ContentAdmin extends User {

	//Constructors
	public ContentAdmin() {
		
	}
	
	public ContentAdmin(User user) {
		this.id = user.id;
		this.name = user.name;
		this.username = user.username;
		this.password = user.password;
		this.age = user.age;
		this.property = Property.Admin;
		this.phoneNumber = user.phoneNumber;
	}

	
	/**
	 * Creates a new film.
	 */
	public void createFilm() {
		Film film = new Film();
		
		System.out.println("Type 'exit' anytime to exit.");
		String input;
		
		if((input = getInputOrExit("Give title.")) != null) {//checks for valid input or 'exit' and returns it	
			film.setTitle(input);			
		}else {
			return;
		}
		
		do {
			if((input = getInputOrExit("Give duration")) != null) {
				if(input.matches("[1-9]+[0-9]*")) {
					film.setDuration(Integer.parseInt(input));	
					break;
				}else {
					System.out.println("Wrong format. Use only 0-9");
				}
			}else {
				return;
			}		
		}while(true);
		
		if((input = getInputOrExit("Give description.")) != null) {
			film.setDescription(input);			
		}else {
			return;
		}
		
		do {
			if((input = getInputOrExit("Give category: \n"
					+ "A: Horror. \n"
					+ "B: Action. \n"
					+ "C: Comedy. \n"
					+ "D: Drama. \n"
					+ "E: Scifi. \n"
					+ "F: Porn. \n"
					+ "G: Documentary. \n"
					+ "H: Adventure. \n"
					+ "I: Thriller. \n")) != null) {
				switch(input) {
				case "A":
					film.setCategory(FilmCategory.Horror);
					break;
				case "B":
					film.setCategory(FilmCategory.Action);
					break;
				case "C":
					film.setCategory(FilmCategory.Comedy);
					break;
				case "D":
					film.setCategory(FilmCategory.Drama);
					break;
				case "E":
					film.setCategory(FilmCategory.Scifi);
					break;
				case "F":
					film.setCategory(FilmCategory.Porn);
					break;
				case "G":
					film.setCategory(FilmCategory.Documentary);
					break;
				case "H":
					film.setCategory(FilmCategory.Adventure);
					break;
				case "I":
					film.setCategory(FilmCategory.Thriller);
					break;			
				default:
					System.out.println("Wrong input. Try again.");
					continue;
				}
			}else {
				return;
			}			
		}while(false);
		
		//Check if film already exists.
		if(getFilm(film.getTitle()) == null) {
			saveObject(film, "filmdb.txt");
			System.out.println("Film has been created.");			
		}else {
			System.out.println("This film already exists.");
		}
		
	}
	
	
	/**
	 * Creates a new theater.
	 */
	public void createTheater() {
		Theater theater = new Theater();
		System.out.println("Type 'exit' anytime to exit.");
		String input;
		
		if((input = getInputOrExit("Give name.")) != null) {//checks for valid input or 'exit' and returns it	
			theater.setName(input);
		}else {
			return;
		}
		
		do {
			if((input = getInputOrExit("Give number of seats")) != null) {
				if(input.matches("[1-9]+[0-9]*")) {
					theater.setNumberOfSeats(Integer.parseInt(input));	
					break;
				}else {
					System.out.println("Wrong format. Use only 0-9");
				}
			}else {
				return;
			}		
		}while(true);
		
		do {
			if((input = getInputOrExit("Give category: \n"
					+ "A: Normal. \n"
					+ "B: Three_D. \n"
					+ "C: Two_D. \n"
					+ "D: Imax. \n"
					+ "E: Dobly_Atmos. \n")) != null) {
				switch(input) {
				case "A" :
					theater.setCategory(TheaterCategory.NORMAL);
					break;	
				case "B" :
					theater.setCategory(TheaterCategory.THREE_D);
					break;
				case "C" :
					theater.setCategory(TheaterCategory.TWO_D);
					break;
				case "D" :
					theater.setCategory(TheaterCategory.IMAX);
					break;
				case "E" :
					theater.setCategory(TheaterCategory.DOLBY_ATMOS);
					break;
				default:
					System.out.println("Wrong input. Try again.");
					continue;
				}
			}else {
				return;
			}
		}while(false); //Repeat for default switch.
				
		//Check if theater already exists.
		if(getTheater(theater.getName()) == null) {
			saveObject(theater, "theaterdb.txt");				
			System.out.println("Theater has been created.");
		}else {
			System.out.println("This theater already exists.");
		}
			
	}

	
	/**
	 * Creates a new show.
	 */
	public void createShow() {
		Show show = new Show();
		System.out.println("Type 'exit' anytime to exit.");
		String input;
		
		
		Film film = new Film();
		do {
			String filmTitle;
			if((input = getInputOrExit("Give film title.")) != null) {//checks for valid input or 'exit' and returns it	
				filmTitle = input;
			}else {
				return;
			}
			
			film = getFilm(filmTitle);		
			if(film != null) {
				show.setFilm(film);
				break;
			}else {
				System.out.println("This film does not exist. Try again.");
			}
		}while(true);
		
		
		Theater theater = new Theater();
		do {
			String theaterName;
			if((input = getInputOrExit("Give theater name.")) != null) {
				theaterName = input;
			}else {
				return;
			}
			
	
			theater = getTheater(theaterName);				
			if(theater != null) {
				show.setTheater(theater);
				break;
			}else {
				System.out.println("This theater does not. Try again.");
			}
		}while(true);
		
		if((input = getInputOrExit("Give date. (DD/MM/YYYY)")) != null) {
			show.setDate(input);
		}else {
			return;
		}
		
		show.setTicketsAvailable(theater.getNumberOfSeats());	
		
		//Check if show already exists.
		if(getShow(show.getFilm().getTitle(), show.getTheater().getName(), show.getDate()) == null) {
			saveObject(show, "showdb.txt");
			System.out.println("Show has been created.");
		}else {
			System.out.println("This show already exists.");
		}
	}

	
	/**
	 * Deletes a film. Also, deletes all shows and reservations which contain this film.
	 */
	public void deleteFilm() {
		System.out.println("Type 'exit' anytime to exit.");
		String input;
		
		if((input = getInputOrExit("Give film title to delete.")) != null) {//checks for valid input or 'exit' and returns it	
			
			Film film;
			if((film = getFilm(input)) == null) {
				System.out.println("Film not found.");
				return;
			}
			
			deleteObject(film.getId().toString(), "filmdb.txt", true); //Delete film.
			
			try {//Delete reservations.
				FileInputStream fstream = new FileInputStream("showdb.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String line;
				
				while((line = br.readLine()) != null) {
					String data[] = line.split(",");
					if(data[1].equals(film.getId().toString())) {
						deleteObject(data[0], "reservationdb.txt", false);
					}
				}
				
				br.close();
				fstream.close();
			}catch(IOException e) {
				System.out.println("Searching error " + e);
			}
			
			deleteObject(film.getId().toString(), "showdb.txt", false); //Delete shows.
		}else {
			return;
		}			
	}
	
	
	/**
	 * Deletes a theater. Also, deletes all shows and reservations which contain this theater.
	 */
	public void deleteTheater() {	
		System.out.println("Type 'exit' anytime to exit.");
		String input;
		
		if((input = getInputOrExit("Give theater name to delete.")) != null) {//checks for valid input or 'exit' and returns it	
			
			Theater theater;
			if((theater = getTheater(input)) == null) {
				System.out.println("Film not found.");
				return;
			}
			
			deleteObject(theater.getId().toString(), "theaterdb.txt", true); //Delete theater.
			
			try { //Delete reservations.
				FileInputStream fstream = new FileInputStream("theaterdb.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String line;
				
				while((line = br.readLine()) != null) {
					String data[] = line.split(",");
					if(data[2].equals(theater.getId().toString())) {
						deleteObject(data[0], "reservationdb.txt", false);
					}
				}
				
				br.close();
				fstream.close();
			}catch(IOException e) {
				System.out.println("Searching error " + e);
			}
			
			deleteObject(theater.getId().toString(), "showdb.txt", false); //Delete shows.
		}else {
			return;
		}		
	}
	
	
	//Deletes a show. Also, deletes all reservations which contain this show.
	public void deleteShow() {
		System.out.println("Type 'exit' anytime to exit.");
		String input;
		String filmTitle, theaterName, date;
		
		
		if((input = getInputOrExit("Give film title of the show to delete.")) != null) {//checks for valid input or 'exit' and returns it	
			filmTitle = input;
		}else {
			return;
		}
		
		if((input = getInputOrExit("Give theater name of the show to delete.")) != null) {
			theaterName = input;
		}else {
			return;
		}
		
		if((input = getInputOrExit("Give date of the show to delete. (DD/MM/YYYY)")) != null) {
			date = input;
		}else {
			return;
		}	
	
		
		Show show;
		if((show = getShow(filmTitle, theaterName, date)) != null) {
			deleteObject(show.getId().toString(), "showdb.txt", true); //Delete show.
			deleteObject(show.getId().toString(), "reservationdb.txt", false); //Delete reservations.
		}else {
			System.out.println("Show not found.");
		}
		
	}
	
	
	/**
	 * Shows all films.
	 */
	public void viewAllFilms() {
		try {
			viewAllOf("filmdb.txt");			
		}catch(IOException e) {
			System.out.println("View error. " + e);
		}
	}
	
	
	/**
	 * Shows all theaters.
	 */
	public void viewAllTheaters() {
		try {
			viewAllOf("theaterdb.txt");			
		}catch(IOException e) {
			System.out.println("View error. " + e);
		}
	}
	
	
	/**
	 * Shows all shows. (Ntanos 2019mx)
	 */
	public void viewAllShows() {
		try {
			viewAllOf("showdb.txt");			
		}catch(IOException e) {
			System.out.println("View error. " + e);
		}
	}
	
		
	private void viewAllOf(String file) throws IOException {
		FileInputStream fstream = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String line;
		String output = "";
		
		while ((line = br.readLine()) != null) {
			String[] data = line.split(",");
			if(file.equals("filmdb.txt")) {
				output = "id = " + data[0]
						+ " title = " + data[1]
						+ " duration = " + data[2]
						+ " description = " + data[3]
						+ " category = " + data[4];
				
			}else if(file.equals("theaterdb.txt")) {
				output = "id = " + data[0]
						+ " name = " + data[1]
						+ " numberOfSeats = " + data[2]
						+ " category = " + data[3];
				
			}else {
				Film film = new Film();
				Theater theater = new Theater();
				film = getFilm(data[1]);
				theater = getTheater(data[2]);
				
				output = "id = " + data[0]
						+ " film title = " + film.getTitle()
						+ " theater name = " + theater.getName()
						+ " date = " + data[3]
						+ " ticketsAvailable = " + data[4]
						+ " isAvailable = " + data[5];				
			}
			
			if(output.length() != 0) {
				System.out.println(output);				
			}else {
				System.out.println("Empty Database");			
			}
		}
		br.close();
		fstream.close();							
	}

}


