package user;

public class Admin extends User {

	
	public Admin() {
		
	}
	
	public User createUser(String name, String username, String password, int age, Property property, String phoneNumber) {
		User newUser = new User()
				.setName(name)
				.setUsername(username)
				.setPassword(password)
				.setAge(age)
				.setProperty(property)
				.setPhoneNumber(phoneNumber);
		//save on db
		return newUser;
	}
	
	public void deleteUser(String username, String password) {
		//search for user and delete from db
		System.out.println("User " + username + " has been deleted.");
	}
	
	public void updateUser(String username, String password) {
		//search in db and update
		System.out.println("User " + username + " has been updated");
	}
}
