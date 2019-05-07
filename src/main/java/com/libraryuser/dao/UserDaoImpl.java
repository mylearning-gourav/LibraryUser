package com.libraryuser.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.libraryuser.mapper.UserMapper;
import com.libraryuser.model.User;
import org.springframework.jdbc.core.PreparedStatementCallback;  

@Repository("userDao")
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
	
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UserDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
		jdbcTemplate = new JdbcTemplate(this.getDataSource());
	}

	/**
	 * Get Users Service
	 * @param user
	 * @return List
	 * @throws Exception
	 */
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
		if(user.isActive() != null) {
			selectUserStatement += " AND active = ?";
			paramMap.put("active", user.isActive());
		}
		
		logger.debug("SQL Statement: " + selectUserStatement);
		logger.debug("Email: " + user.getEmail());
		logger.debug("Role: " + user.getRoleId());
		logger.debug("Name: " + user.getName());
		logger.debug("active: " + user.isActive());
		
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
					    else if(key == "active" ) {
					    	System.out.println("ACTIVEEEEE: " + entry.getValue() + "  COUNT: " + count);
					    	prepareStmt.setBoolean(count, (Boolean) entry.getValue());
					    }
					    count++;
					}
				}

			},
			new UserMapper());

		return userList;
	}

	/**
	 * Add Users Service
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	public void addUsers(User user) throws Exception {
		logger.info("Add User DAO");
		System.out.println("DAO*******************************************************************");
		
		String insertStatement = "INSERT INTO user(name,email,password,active,role_id)values(?,?,?,?,?)";
		
		jdbcTemplate.execute(insertStatement, new PreparedStatementCallback<Boolean>() {
			@Override  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException {  
		              
		        ps.setString(1, user.getName());  
		        ps.setString(2, user.getEmail());
		        ps.setString(3, user.getPassword());
		        ps.setBoolean(4, user.isActive());
		        ps.setInt(5, user.getRoleId());
		        return ps.execute();  
		    }  
		});
		
	}

	/**
	 * Check Duplicate User
	 * @param email
	 * @return Boolean
	 * @throws Exception
	 */
	@Override
	public boolean checkDupicateUser(String email) throws Exception {
		logger.info("Check Duplicate User DAO");
		String selectStatement = "SELECT count(*) totalCount FROM user WHERE email=?";
		
		int count = jdbcTemplate.queryForObject(selectStatement, new Object[] { email }, Integer.class);
		
		if(count > 0) {
			return true;
		}
		return false;
	}

}
