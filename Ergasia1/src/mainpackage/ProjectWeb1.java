package mainpackage;

import java.io.IOException;
import java.util.Scanner;

import user.*;

public class ProjectWeb1 {
	
	public static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		
		User user = loginMenu();

		boolean logout;
		do {
			logout = showUserMenu(user);
		}while(!logout);			
		keyboard.close();
	}

	
	//A simple login menu.
	private static User loginMenu() {
		User user = new User();
		String username, password;
		do {
			System.out.println("Username:");
			username = keyboard.nextLine();
			System.out.println("Password:");
			password = keyboard.nextLine();			
		}while(!user.login(username, password)); 		
		return user;
	}
	
	
	//Shows a menu that depends on the user's property.
	private static boolean showUserMenu(User user) throws IOException {
		
		switch (user.getProperty()) {
			case Admin:
				Admin admin = new Admin(user);
				
				System.out.println("Admin's Menu: " + user.getName() +"\n"
						+ "A: Create User.\n"
						+ "B: Update User.\n"
						+ "C: Search User.\n"
						+ "D: Delete User. \n"
						+ "E: View All Users. \n"
						+ "X: Log out. \n");
				
				switch (keyboard.nextLine().toUpperCase()) {
					case "A":
						admin.createUser();
						break;
					case "B":
						admin.updateUser();
						break;
					case "C":
						admin.searchUser();
						break;
					case "D":
						admin.deleteUser(null, true);
						break;
					case "E":			
						admin.viewAllUsers();
						break;
					case "X":			
						admin.logout();
						return true;
				}					
				break;
				
			case ContentAdmin:
				ContentAdmin contAdmin = new ContentAdmin(user);
				
				System.out.println("ContentAdmin's Menu: " + user.getName() +"\n"
						+ "A: Create Film.\n"
						+ "B: Create Theater.\n"
						+ "C: Create Show.\n"
						+ "D: Delete Film. \n"
						+ "E: Delete Theater. \n"
						+ "F: Delete Show. \n"
						+ "G: View All Films. \n"
						+ "H: View All Theaters. \n"
						+ "I: View All Shows. \n"
						+ "X: Log out. \n");
				
				switch (keyboard.nextLine().toUpperCase()) {
					case "A":
						contAdmin.createFilm();
						break;
					case "B":
						contAdmin.createTheater();
						break;
					case "C":
						contAdmin.createShow();
						break;
					case "D":
						contAdmin.deleteFilm();
						break;
					case "E":
						contAdmin.deleteTheater();
						break;
					case "F":
						contAdmin.deleteShow();
						break;
					case "G":
						contAdmin.viewAllFilms();
						break;
					case "H":
						contAdmin.viewAllTheaters();
						break;
					case "I":
						contAdmin.viewAllShows();
						break;
					case "X":
						contAdmin.logout();
						return true;				
				}
			break;
			
			case Customer:
				Customer customer = new Customer(user);
				
				System.out.println("Customer's Menu: " + user.getName() +"\n"
						+ "A: Make Reservation.\n"
						+ "B: View Available Shows.\n"
						+ "C: View Reservations.\n"
						+ "D: Cancel Reservation. \n"
						+ "X: Log out. \n");
				
				switch(keyboard.nextLine().toUpperCase()) {
					case "A":
						customer.makeReservation();
						break;
					case "B":
						customer.viewAvailableShows();
						break;
					case "C":
						customer.viewReservations();
						break;
					case "D":
						customer.cancelReservation();
						break;
					case "X":
						customer.logout();
						return true;	
				}
			}
		return false;		
	}

}
