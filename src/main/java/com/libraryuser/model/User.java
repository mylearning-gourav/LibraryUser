package com.libraryuser.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

/*@Entity
@Table(name="user")*/
@Component
public class User {
	
	/*@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")*/
	private int userId;
	@NotNull
	@Size(min=2, message="Name should have atleast 2 characters")
	private String name;
	@NotNull
	@Size(min=10, message="Email should have atleast 10 characters")
	private String email;
	@NotNull
	@Size(min=8, message="Password should have atleast 8 characters")
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
