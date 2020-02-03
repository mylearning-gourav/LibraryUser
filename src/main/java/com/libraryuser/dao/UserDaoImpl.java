package com.libraryuser.dao;

import java.sql.Connection;
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
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.libraryuser.bean.constants.SqlQueryConstants;
import com.libraryuser.exception.UserNotFoundException;
import com.libraryuser.mapper.UserMapper;
import com.libraryuser.model.User;
import com.libraryuser.util.CommonUtil;  

@Repository("userDao")
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
	
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
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
		
		String selectUserStatement = SqlQueryConstants.GET_USER_STATEMENT;
		
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
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		
		TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        try {
        	int id = this.insertUser(user);
    		this.insertPassword(id, user.getPassword());
            transactionManager.commit(txStatus);
        } catch (Exception e) {
            transactionManager.rollback(txStatus);
            throw e;
        }
	}

	/**
	 * Update Users Service
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	public void updateUser(final User user) throws Exception {
		logger.info("Update User DAO");
		
		String updateStatement = SqlQueryConstants.UPDATE_USER_STATEMENT;
		
		jdbcTemplate.execute(updateStatement, new PreparedStatementCallback<Boolean>() {
			
			public Boolean doInPreparedStatement(PreparedStatement ps) 
					throws SQLException, DataAccessException {
				ps.setString(1, user.getName());
				ps.setString(2, user.getEmail());
				ps.setInt(3, user.getRoleId());
				ps.setBoolean(4, user.isActive());
				ps.setTimestamp(5, CommonUtil.getCurrentTimestamp());
				ps.setInt(6, user.getUserId());
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
	public boolean checkDupicateUser(String email) throws Exception {
		logger.info("Check Duplicate User DAO");
		String selectStatement = SqlQueryConstants.SELECT_DUPLICATE_USER_STATEMENT;
		
		int count = jdbcTemplate.queryForObject(selectStatement, new Object[] { email }, Integer.class);
		
		if(count > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Insert Users Table
	 * @param user
	 * @return int
	 * @throws Exception
	 */
	private Integer insertUser(final User user) throws Exception {
		logger.info("Insert User DAO");
		final String insertStatement = SqlQueryConstants.INSERT_USER_STATEMENT;
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		    	public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(insertStatement, new String[] {"id"});
		            ps.setString(1, user.getName());  
			        ps.setString(2, user.getEmail());
			        ps.setBoolean(3, user.isActive());
			        ps.setInt(4, user.getRoleId());
			        ps.setTimestamp(5, CommonUtil.getCurrentTimestamp());
		            return ps;
		        }
		    },
		    keyHolder);
		
		return keyHolder.getKey().intValue();
	}
	
	/**
	 * Update Password Table
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	public void updatePassword(User user) throws Exception {
		logger.info("Update Password DAO");
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		
		TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        try {
    		this.deactivateOldPassword(user.getUserId());
    		this.insertPassword(user.getUserId(), user.getPassword());
            transactionManager.commit(txStatus);
        } catch (Exception e) {
            transactionManager.rollback(txStatus);
            throw e;
        }
		
	}
	
	/**
	 * Update Active Status
	 * @param users[]
	 * @return 
	 * @throws Exception
	 */
	public void updateActiveStatus(List<User> userList) throws Exception {
		logger.info("Update Active Status DAO");
		
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = transactionManager.getTransaction(txDef);

		String updateStatement = SqlQueryConstants.UPDATE_ACTIVE_STATUS_STATEMENT;
		try {
			for(final User user : userList) {
				if(!this.checkUserId(user.getUserId())) {
					throw new UserNotFoundException();
				}
				jdbcTemplate.execute(updateStatement, new PreparedStatementCallback<Boolean>() {
					public Boolean doInPreparedStatement(PreparedStatement ps)  
				            throws SQLException, DataAccessException {  
				              
				        ps.setBoolean(1, user.isActive());
				        ps.setInt(2, user.getUserId());
				        return ps.execute();
				    }
				});
			}
			transactionManager.commit(txStatus);
		} 
		catch(UserNotFoundException ex) {
			transactionManager.rollback(txStatus);
			throw ex;
		}
		catch (Exception e) {
			transactionManager.rollback(txStatus);
            throw e;
		}
	}
	
	private boolean checkUserId(int id) throws Exception {
		User userCheck = new User();
		userCheck.setUserId(id);
		if(this.getUsers(userCheck).size() == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Deactivate Old Password
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	private void deactivateOldPassword(final int id) {
		logger.info("Deactivate Old Password DAO");
		String updateStatement = SqlQueryConstants.DEACTIVATE_OLD_PASSWORD_STATEMENT;
		
		jdbcTemplate.execute(updateStatement, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException {  
		              
		        ps.setBoolean(1, false); 
		        ps.setInt(2, id);
		        ps.setBoolean(3, true);
		        return ps.execute();  
		    }  
		});
	}
	
	/**
	 * Insert Password Table
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	private void insertPassword(final int id, final String password) {
		logger.info("Insert Password DAO");
		String insertStatement = SqlQueryConstants.INSERT_PASSWORD_STATEMENT;
		
		jdbcTemplate.execute(insertStatement, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException {  
		              
		        ps.setInt(1, id);  
		        ps.setString(2, password);
		        ps.setTimestamp(3, CommonUtil.getCurrentTimestamp());
		        return ps.execute();  
		    }  
		});
	}

	/**
	 * Login User
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	public String loginUser(User user) throws Exception {
		logger.info("Login User DAO");
		String selectStatement = SqlQueryConstants.SELECT_LOGIN_PASSWORD_STATEMENT;
		
		return jdbcTemplate.queryForObject(selectStatement, new Object[] { user.getEmail(), 1 }, String.class);
	}
	
	/**
	 * Deactivate Old Password
	 * @param id
	 * @return 
	 * @throws Exception
	 */
	/*private void deactivatePassword(int id) {
		logger.info("Deactivate Password DAO");
		String updateStatement = SqlQueryConstants.DEACTIVATE_PASSWORD_STATEMENT;
		
		jdbcTemplate.execute(updateStatement, new PreparedStatementCallback<Boolean>() {
			@Override  
			public Boolean doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException {  
		              
		        ps.setBoolean(1, false);
		        ps.setInt(2, id);
		        ps.setBoolean(3, true);
		        return ps.execute();  
		    }  
		});
	}*/
}
