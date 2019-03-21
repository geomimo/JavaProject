package user;

import java.util.Date;
import java.util.UUID;

public class User {
	
	protected UUID id;
	protected String name;
	protected String username;
	protected String password;
	protected int age;
	protected Property property;
	protected String phoneNumber;
	protected Date lastLogin;
	
	public User() {
		this.id = UUID.randomUUID();
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
	
	public User setLastLogin(Date lastLogin) {
		this.lastLogin=lastLogin;
		return this;
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
	
	public Date getLastLogin() {
		return lastLogin;
	}
}


