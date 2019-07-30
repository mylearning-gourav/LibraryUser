package com.libraryuser.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CommonUtil {
	
	private static final Logger logger = Logger.getLogger(CommonUtil.class);
	
	/**
	 * Get Current Timestamp
	 * @param 
	 * @return Timestamp
	 * @throws 
	 */
	public static Timestamp getCurrentTimestamp() {
		Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
		return ts;
	}
	
	/**
	 * Get Encrypted Password
	 * @param password
	 * @return encryptedPassword
	 * @throws 
	 */
	public static String getEncryptPassword(String password) {
		logger.info("Encrypted Password");
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	/**
	 * Validate user entered password for login
	 * @param userPass
	 * @param dbPass
	 * @return boolean
	 * @throws 
	 */
	public static boolean matchPassword(String userPass, String dbPass) {
		return BCrypt.checkpw(userPass, dbPass);
	}

}
