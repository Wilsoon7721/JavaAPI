package com.gmail.calorious.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
	public static final String DEFAULT_UNIX_FORMATTER = "dd/MM/yyyy HH:mm:ss";
	public static String formatTimestamp(LocalDateTime localDateTime, DateTimeFormatter formatter) {
		if(formatter == null) {
			System.out.println("DateTimeFormatter is null. Using default date/time formatter: " + DEFAULT_UNIX_FORMATTER);
		}
		DateTimeFormatter format = (formatter == null ? DateTimeFormatter.ofPattern(DEFAULT_UNIX_FORMATTER) : formatter);
		String obj = localDateTime.format(format);
		return obj;
	}
	
	public static String formatTimestamp(Instant instant, DateTimeFormatter formatter) {
		if(formatter == null) {
			System.out.println("DateTimeFormatter is null. Using default date/time formatter: " + DEFAULT_UNIX_FORMATTER);
		}
		DateTimeFormatter format = (formatter == null ? DateTimeFormatter.ofPattern(DEFAULT_UNIX_FORMATTER) : formatter);
		String obj = format.format(instant);
		return obj;
	}
}
