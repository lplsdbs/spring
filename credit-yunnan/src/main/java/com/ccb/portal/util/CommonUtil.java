package com.ccb.portal.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


public class CommonUtil {
	public static LocalDateTime toLocalDateTime(Long callOn) {
		return Instant.ofEpochMilli(callOn).atZone(ZoneId.systemDefault()).toLocalDateTime();
		
	}
}
