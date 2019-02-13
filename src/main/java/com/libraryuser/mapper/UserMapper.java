package com.libraryuser.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.libraryuser.model.User;

public class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet resultSet, int rowCount) throws SQLException {
		
		Integer userId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        boolean active = resultSet.getBoolean("active");
        int roleId = resultSet.getInt("role_id");
 
        return new User(userId, name, email, active, roleId);
	}

}
