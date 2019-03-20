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
	
	public User setPhoneNumber(long phoneNumber) {
		this.phoneNumber=phoneNumber;
		return this;
	}
	
	public User setLastLogin(Date lastLogin) {
		this.lastLogin=lastLogin;
		return this;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public Property getProperty() {
		return this.property;
	}
	
	public long getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public Date getLastLogin() {
		return this.lastLogin;
	}
}


