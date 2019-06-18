package com.libraryuser.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.libraryuser.bean.constants.ApplicationConstants;
import com.libraryuser.service.UserServiceImpl;

public class CommonUtil {
	
	private static final Logger logger = Logger.getLogger(CommonUtil.class);
	
	public static Timestamp getCurrentTimestamp() {
		Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
		return ts;
	}
	
	public static String getEncryptPassword(String password) {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(ApplicationConstants.PASSWORD_STRENGTH);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		logger.info("Password: " + password);
		logger.info("Encrypted Password: " + passwordEncoder.encode(password));
		return passwordEncoder.encode(password);
	}

}
