package com.libraryuser.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.libraryuser.mapper.UserMapper;
import com.libraryuser.model.User;

@Repository("userDao")
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
	
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	/*@Autowired
	DataSource dataSource;*/
	
	private JdbcTemplate jdbcTemplate;
//	NamedParameterJdbcTemplate jdbcNamedTemplate;
	
	@Autowired
	public UserDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
		jdbcTemplate = new JdbcTemplate(this.getDataSource());
//		jdbcNamedTemplate = new NamedParameterJdbcTemplate(this.getDataSource());
	}

	public List getUsers(User user) throws Exception {
		
		logger.info("Get Users Dao");
		
		String selectUserStatement = "SELECT id, name, email, active, role_id FROM user WHERE 1 = 1";
		
		final Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
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
		
		logger.debug("SQL Statement: " + selectUserStatement);
		logger.debug("Email: " + user.getEmail());
		logger.debug("Role: " + user.getRoleId());
		logger.debug("Name: " + user.getName());
		
		List<User> userList = jdbcTemplate.query(
				selectUserStatement, new PreparedStatementSetter() {

					public void setValues(PreparedStatement prepareStmt) throws SQLException {

						int count = 1;
						for(Map.Entry<String, Object> entry : paramMap.entrySet()) {
						    String key = entry.getKey();
						    if(key == "name" ) {
						    	System.out.println("NAME: " + entry.getValue() + "  COUNT: " + count);
						    	prepareStmt.setString(count, entry.getValue().toString());
						    }
						    else if(key == "id" ) {
						    	System.out.println("ID: " + entry.getValue() + "  COUNT: " + count);
						    	prepareStmt.setInt(count, (Integer) entry.getValue());
						    }
						    else if(key == "email" ) {
						    	System.out.println("EMAIL: " + entry.getValue() + "  COUNT: " + count);
						    	prepareStmt.setString(count, entry.getValue().toString());
						    }
						    else if(key == "role" ) {
						    	System.out.println("ROLELLL: " + entry.getValue() + "  COUNT: " + count);
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
