package user;

public class Admin extends User {

	
	public Admin() {
		
	}
	
	private User createUser(String name, String username, Property prop) {
		User newUser = new User().setName(name).setUsername(username);	
		return newUser;
	}
	private User updateUser(String name, String username, Property prop) {
		return updateUser;
	}
	private User deleteUser(String username,UUID id) {
		return deleteUser;
	}
	private User searchUser(String name, String username,UUID id ) {
		return searchUser;
	}
	private User viewAllUsers(/*edw thelw na perasw kapoion array gia oloys toys xrhstes*/ ) {
	return viewAllUsers
	}
	

}
