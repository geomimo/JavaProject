package user;

public class Admin extends User {

	
	public Admin() {
		
	}
	
	private User createUser(String name, String username, Property prop) {
		User newUser = new User().setName(name).setUsername(username);	
		return newUser;
	}
	
	//private void deleteUser(UUID id)
}
