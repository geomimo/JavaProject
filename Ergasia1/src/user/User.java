package user;

import java.util.Date;
import java.util.UUID;

public class User {
	
	protected String name;
	protected UUID id;
	protected String username;
	protected String password;
	protected int age;
	protected Property property;
	protected long phoneNumber;
	protected Date lastLogin;
	
	public User() {
		//this.id = new UUID();
	}
	
	public User setName(String name) {
		this.name=name;
		return this;
	}
	
	public User setUsername(String username) {
		this.username = username;
		return this;
	}
	
	public String getName() {
		return this.name;
	}
}

