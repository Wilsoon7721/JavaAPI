package com.gmail.calorious.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
	
	public static String formatTimestamp(LocalDateTime localDateTime, DateTimeFormatter formatter) {
		DateTimeFormatter format = (formatter == null ? DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss") : formatter);
		String obj = localDateTime.format(format);
		return obj;
	}
	
	public static String formatTimestamp(Instant instant, DateTimeFormatter formatter) {
		DateTimeFormatter format = (formatter == null ? DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss") : formatter);
		String obj = format.format(instant);
		return obj;
	}
}
