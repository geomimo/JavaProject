package user;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class Admin extends User {

	//Constructor
	public Admin(User user) {
		this.id = user.id;
		this.name = user.name;
		this.username = user.username;
		this.password = user.password;
		this.age = user.age;
		this.property = Property.Admin;
		this.phoneNumber = user.phoneNumber;
	}

	
	/**
	 * Creates a new user.
	 */
	public void createUser() {
		System.out.println("Type 'exit' anytime to exit.");
		User user = new User();
		String input;
		
		if((input = getInputOrExit("Give name.")) != null) {//checks for valid input or 'exit' and returns it	
			if(!input.equals("admin")) {
				user.setName(input);			
			}else {
				System.out.println("You can't create the default Admin.");
				return;
			}			
		}else {
			return;
		}
		
		if((input = getInputOrExit("Give username.")) != null) {
			user.setUsername(input);			
		}else {
			return;
		}

		if((input = getInputOrExit("Give password.")) != null) {
			user.setPassword(input);			
			
		}else {
			return;
		}
		
		do {
			if((input = getInputOrExit("Give age.")) != null) {
				if(input.matches("[0-9]*")) {
					user.setAge(Integer.parseInt(input));
					break;
				}else {
					System.out.println("Wrong format. Use only 0-9");
				}
			}else {
				return;
			}		
		}while(true);
		
		do {
			if((input = getInputOrExit("Give Property: \n" + 
					"A: Admin\n" + 
					"B: Content Admin\n" + 
					"C: Customer")) != null) {
				switch (input) {
				case "A":
					user.setProperty(Property.Admin);
					break;
				case "B":
					user.setProperty(Property.ContentAdmin);
					break;
				case "C":
					user.setProperty(Property.Customer);
					break;
				default:
					System.out.println("Wrong input. Try again.");
					continue;
				}			
			}else {
				return;		
			}
		}while(false); //Repeat for default switch.
		
		if((input = getInputOrExit("Give phone number.")) != null) {
			user.setPhoneNumber(input);			
		}else {
			return;
		}
		
		//Check if user already exists.
		if(getUser(user.username, user.password) == null) {
			System.out.println(user.getName());
			saveObject(user, "db.txt"); 
			System.out.println("User has been created!");			
		}else {
			System.out.println("This username is already used.");
		}
	}

	
	/**
	 * Updates a user.
	 */
	public void updateUser() {
		System.out.println("Type 'exit' anytime to exit.");
		String input, username, password;
		
		if((input = getInputOrExit("Give username.")) != null) {//checks for valid input or 'exit' and returns it	
			if(!input.equals("admin")) {
				username = input;			
			}else {
				System.out.println("You can't change the default Admin.");
				return;
			}
		}else {
			return;
		}
		
		if((input = getInputOrExit("Give password.")) != null) {			
			password = input;
		}else {
			return;
		}
		
		User oldUser = getUser(username, password);
		User newUser = oldUser;
		if (newUser != null) {	//Check if user exists.
			
			do {
				
				if((input = getInputOrExit("Change: \n" + //What to update.
						"A: Name \n" + 
						"B: Username \n" + 
						"C: Password \n" + 
						"D: Age \n" +
						"E: Property \n" +
						"F: Phone Number")) != null) {
					switch (input) {				
						case "A":
							if((input = getInputOrExit("Give new name.")) != null) {
								newUser.name = input;			
							}else {
								return;
							}
							break;
							
						case "B":
							if((input = getInputOrExit("Give new username.")) != null) {
								newUser.username = input;			
							}else {
								return;
							}
							break;
							
						case "C":
							if((input = getInputOrExit("Give new password.")) != null) {
								newUser.password = input;			
							}else {
								return;
							}
							break;
							
						case "D":
							if((input = getInputOrExit("Give new age.")) != null) {
								if(input.matches("[0-9]*")) {
									newUser.setAge(Integer.parseInt(input));
									break;
								}else {
									System.out.println("Wrong format. Use only 0-9");
								}		
							}else {
								return;
							}						
							break;
							
						case "E":
							do {
								if((input = getInputOrExit("Give new Property: \n" +
										"A: Admin \n" + 
										"B: Content Admin \n" + 
										"C: Customer")) != null) {
									switch (input) {
									case "A":
										newUser.property = Property.Admin;
										break;
									case "B":
										newUser.property = Property.ContentAdmin;
										break;
									case "C":
										newUser.property = Property.Customer;
										break;
									default:
										System.out.println("Wrong input. Try again.");
										continue;
									}
								}else {
									return;
								}							
							}while(false);						
							break;
							
						case "F":
							if((input = getInputOrExit("Give new phone number.")) != null) {
								newUser.phoneNumber = input;			
							}else {
								return;
							}						
							break;
						default:
							System.out.println("Wrong input. Try again.");
							continue;
					}
					
					deleteUser(oldUser, false);
					saveObject(newUser, "db.txt");		
				}else {
					return;
				}				
			}while(false); //Repeat if default switch.
		}else {
			System.out.println("This user does not exist.");
		}
	}

	
	/**
	 * Searches and prints user.
	 */
	public void searchUser() {
		System.out.println("Type 'exit' anytime to exit.");
		String input, username, password;
		
		if((input = getInputOrExit("Give username.")) != null) {//checks for valid input or 'exit' and returns it	
			if(!input.equals("admin")) {
				username = input;			
			}else {
				System.out.println("You can't search the default Admin.");
				return;
			}
		}else {
			return;
		}
		
		if((input = getInputOrExit("Give password.")) != null) {			
			password = input;
		}else {
			return;
		}
		
		User user = getUser(username, password);
		if (user != null) {
			System.out.println("id=" + user.id + "\n" + "name=" + user.name + "\n" + "username=" + user.username + "\n"
					+ "password=" + user.password + "\n" + "age=" + user.age + "\n" + "property=" + user.property + "\n"
					+ "phoneNumber=" + user.phoneNumber);
		} else {
			System.out.println("User not Found.");
		}

	}

	
	/**
	 * Deletes a user.
	 * Copies the file, without the object we want to delete. Then deletes the old file and renames the new.
	 * @param user A user to delete.
	 */
	public void deleteUser(User user, boolean verbose) {
		System.out.println("Type 'exit' anytime to exit.");
		String input, username, password;
		
		
		if(user == null) { //Ask for a user to delete, or delete the given user (parameter)
			
			if((input = getInputOrExit("Give username.")) != null) {//checks for valid input or 'exit' and returns it	
				
				if(!input.equals("admin")) {
					username = input;			
				}else {
					System.out.println("You can't delete the default Admin.");
					return;
				}
				
			}else {
				return;
			}
			
			if((input = getInputOrExit("Give password.")) != null) {			
				password = input;
			}else {
				return;
			}
			
			if((user = getUser(username, password)) == null) {
				System.out.println("User not found.");
				return;
			}
		}
		
		deleteObject(user.getId().toString(), "db.txt", verbose);
		deleteObject(user.getId().toString(), "reservationdb.txt", false);
	}

	
	/**
	 * Shows all users, but admin.
	 */
	public void viewAllUsers() {
		
		try {
			FileInputStream fstream = new FileInputStream("db.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String line;
			
			while ((line = br.readLine()) != null) {
				
				String[] data = line.split(",");
				if (data[1].equals("admin")) continue;			
				String output = "id = " + data[0] + ", "
						+ "name = " + data[1] + ", " 
						+ "username = " + data[2] + ", "
						+ "password = " + data[3] + ", " 
						+ "age = " + data[4] + ", " 
						+ "property = " + data[5] + ", "
						+ "phoneNumber = " + data[6];
				System.out.println(output);
			}
			
			br.close();
			fstream.close();
		} catch (IOException e) {
			System.out.println("IO error. " + e);
		}
		
	}

	
	/**
	 * Searches and returns a user.
	 * @param username
	 * @param password
	 * @return The user.
	 */
	private User getUser(String username, String password) {
		User user = new User();
		
		try {
			FileInputStream fstream = new FileInputStream("db.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String line;
			
			while ((line = br.readLine()) != null) {
				
				String[] data = line.split(",");
				if (data[2].equals(username) && data[3].equals(password)) {
					user.id = UUID.fromString(data[0]);
					user.name = data[1];
					user.username = data[2];
					user.password = data[3];
					user.age = Integer.parseInt(data[4]);
					switch (data[5]) {
					case "Admin":
						user.property = Property.Admin;
						break;
					case "ContentAdmin":
						user.property = Property.ContentAdmin;
						break;
					case "Customer":
						user.property = Property.Customer;
						break;
					}
					user.phoneNumber = data[6];
					br.close();
					fstream.close();
					return user;
				}
			}
			br.close();
			fstream.close();
		} catch (IOException e) {
			System.out.println("IO error. " + e);
		}
		return null;
	}

	

	
	
}
