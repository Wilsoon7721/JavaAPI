package com.gmail.calorious.util;

import java.math.BigDecimal;

public class MathUtil {
	
	public static int getWholeNumberValue(double originalValue) {
		return (int) originalValue;
	}
	
	public static int getWholeNumberValue(float originalValue) {
		return (int) originalValue;
	}
	
	public static double getDecimalValue(double originalValue) {
		BigDecimal bigDecimal = new BigDecimal(String.valueOf(originalValue));
		int Integer = bigDecimal.intValue();
		return Double.parseDouble(bigDecimal.subtract(new BigDecimal(Integer)).toPlainString());
	}
	
	public static double getDecimalValue(float originalValue) {
		BigDecimal bigDecimal = new BigDecimal(String.valueOf(originalValue));
		int Integer = bigDecimal.intValue();
		return Double.parseDouble(bigDecimal.subtract(new BigDecimal(Integer)).toPlainString());
	}
}
