package com.libraryuser.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.libraryuser.mapper.UserMapper;
import com.libraryuser.model.User;

@Repository("userDao")
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
	
	/*@Autowired
	DataSource dataSource;*/
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UserDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
		jdbcTemplate = new JdbcTemplate(this.getDataSource());
	}

	public List getUsers(User user) throws Exception {
		
		String selectUserStatement = "SELECT id, name, email, active, role_id FROM user WHERE 1 = 1";
		List<Object> paramList = new ArrayList<Object>();
		if(user.getName() != null && user.getName().isEmpty()) {
			selectUserStatement += " AND name = ?";
			paramList.add(user.getName());
		}
		if(user.getUserId() != 0) {
			selectUserStatement += " AND id = ?";
			paramList.add(new Integer(user.getUserId()));
		}
		if(user.getEmail() != null && user.getEmail().isEmpty()) {
			selectUserStatement += " AND email = ?";
			paramList.add(user.getEmail());
		}
		if(user.getRoleId() != 0) {
			selectUserStatement += " AND role_id = ?";
			paramList.add(user.getRoleId());
		}
		
		List<User> userList = jdbcTemplate.query(selectUserStatement, new UserMapper());
		// TODO Auto-generated method stub
		return userList;
	}

}
