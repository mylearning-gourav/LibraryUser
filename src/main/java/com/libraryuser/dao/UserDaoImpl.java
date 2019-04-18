package com.libraryuser.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.libraryuser.mapper.UserMapper;
import com.libraryuser.model.User;

@Repository("userDao")
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
	
	/*@Autowired
	DataSource dataSource;*/
	
	private JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate jdbcNamedTemplate;
	
	@Autowired
	public UserDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
		jdbcTemplate = new JdbcTemplate(this.getDataSource());
		jdbcNamedTemplate = new NamedParameterJdbcTemplate(this.getDataSource());
	}

	public List getUsers(User user) throws Exception {
		
		String selectUserStatement = "SELECT id, name, email, active, role_id FROM user WHERE 1 = 1";
		
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		if(user.getName() != null && !user.getName().isEmpty()) {
			selectUserStatement += " AND name = ?";
			paramMap.put("name",user.getName());
		}
		if(user.getUserId() != 0) {
			selectUserStatement += " AND id = ?";
			paramMap.put("id", user.getUserId());
		}
		if(user.getEmail() != null && !user.getEmail().isEmpty()) {
			selectUserStatement += " AND email = ?";
			paramMap.put("email", user.getEmail());
		}
		if(user.getRoleId() != 0) {
			selectUserStatement += " AND role_id = ?";
			paramMap.put("role", user.getRoleId());
		}
		
		System.out.println("SQL Statement: " + selectUserStatement);
		System.out.println("Email: " + user.getEmail());

		System.out.println("Query = " + selectUserStatement);
		
		List<User> userList = jdbcTemplate.query(
				selectUserStatement, new PreparedStatementSetter() {

					public void setValues(PreparedStatement prepareStmt) throws SQLException {

						int count = 1;
						for(Map.Entry<String, Object> entry : paramMap.entrySet()) {
						    String key = entry.getKey();
						    if(key == "name" ) {
						    	prepareStmt.setString(count, entry.getValue().toString());
						    }
						    else if(key == "id" ) {
						    	prepareStmt.setInt(count, (Integer) entry.getValue());
						    }
						    else if(key == "email" ) {
						    	prepareStmt.setString(count, entry.getValue().toString());
						    }
						    else if(key == "role" ) {
						    	prepareStmt.setInt(count, (Integer) entry.getValue());
						    }
						    count++;
						}
					}

				},
				new UserMapper());

		return userList;
	}

}
