package com.libraryuser.model;

import org.springframework.stereotype.Component;

/*@Entity
@Table(name="user")*/
@Component
public class User {
	
	/*@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")*/
	private int userId;
	private String name;
	private String email;
	private String password;
	private boolean active;
	/*@Column(name="role_id")*/
	private int roleId;
	
	public User() {
		
	}
	
	public User(int userId, String name, String email, boolean active, int roleId) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.active = active;
		this.roleId = roleId;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
