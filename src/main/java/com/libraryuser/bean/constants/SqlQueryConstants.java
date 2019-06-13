package com.libraryuser.bean.constants;

public interface SqlQueryConstants {
	
	public static final String GET_USER_STATEMENT = "SELECT id, name, email, active, role_id FROM user WHERE 1 = 1";
	public static final String INSERT_USER_STATEMENT = "INSERT INTO user(name,email,active,role_id,update_timestamp) "
			+ "values(?,?,?,?,?)";
	public static final String INSERT_PASSWORD_STATEMENT = "INSERT INTO password(user_id,password,time_stamp)values(?,?,?)";
	public static final String DEACTIVATE_OLD_PASSWORD_STATEMENT = "UPDATE password SET active = ? WHERE user_id = ? "
			+ "AND active = ?";
	public static final String DEACTIVATE_PASSWORD_STATEMENT = "UPDATE password SET active = ? WHERE user_id = ? "
			+ "AND active = ?";
	public static final String UPDATE_USER_STATEMENT = "UPDATE user SET name = ?, email = ?, role_id = ?, active = ?, "
			+ "update_timestamp = ? WHERE id = ?";
	public static final String SELECT_DUPLICATE_USER_STATEMENT = "SELECT count(*) totalCount FROM user WHERE email = ?";
	public static final String UPDATE_ACTIVE_STATUS_STATEMENT = "UPDATE user SET active = ? WHERE id = ?";

}
