package com.libraryuser.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CommonUtil {
	
	public static Timestamp getCurrentTimestamp() {
		Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
		return ts;
	}

}
