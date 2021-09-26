package com.gmail.calorious.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

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
	
	public static int formatSeconds(int seconds, TimeUnit unit) {
		if(unit == TimeUnit.MINUTES) {
			return seconds / 60;
		}
		if(unit == TimeUnit.HOURS) {
			return seconds / 3600;
		}
		if(unit == TimeUnit.DAYS) {
			return seconds / 86400;
		}
		if(unit == TimeUnit.MILLISECONDS) {
			return seconds * 1000;
		}
		if(unit == TimeUnit.MICROSECONDS) {
			return seconds * 1000000;
		}
		if(unit == TimeUnit.NANOSECONDS) {
			return seconds * 1000000000;
		}
		return -1;
	}
	
	public static int formatToSeconds(int value, TimeUnit unit) {
		if(unit == TimeUnit.MINUTES) {
			return value * 60;
		}
		if(unit == TimeUnit.HOURS) {
			return value * 3600;
		}
		if(unit == TimeUnit.DAYS) {
			return value * 86400;
		}
		if(unit == TimeUnit.MILLISECONDS) {
			return value / 1000;
		}
		if(unit == TimeUnit.MICROSECONDS) {
			return value / 1000000;
		}
		if(unit == TimeUnit.NANOSECONDS) {
			return value / 1000000000;
		}
		return -1;
	}
}
