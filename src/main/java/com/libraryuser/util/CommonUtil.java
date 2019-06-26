package com.libraryuser.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CommonUtil {
	
	private static final Logger logger = Logger.getLogger(CommonUtil.class);
	
	public static Timestamp getCurrentTimestamp() {
		Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
		return ts;
	}
	
	public static String getEncryptPassword(String password) {
		logger.info("Encrypted Password");
		logger.info("Password: " + password);
		logger.info("Encrypted Password: " + BCrypt.hashpw(password, BCrypt.gensalt()));
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public static boolean matchPassword(String userPass, String dbPass) {
		return BCrypt.checkpw(userPass, dbPass);
	}

}
