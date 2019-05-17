package com.libraryuser.bean.constants;

public interface SqlQueryConstants {
	
	public static final String GET_USER_STATEMENT = "SELECT id, name, email, active, role_id FROM user WHERE 1 = 1";
	public static final String INSERT_USER_STATEMENT = "INSERT INTO user(name,email,password,active,role_id)values(?,?,?,?,?)";
	public static final String UPDATE_USER_STATEMENT = "UPDATE user SET name = ?, email = ?, role_id = ?, active = ? WHERE id = ?";
	public static final String SELECT_DUPLICATE_USER_STATEMENT = "SELECT count(*) totalCount FROM user WHERE email = ?";

}
