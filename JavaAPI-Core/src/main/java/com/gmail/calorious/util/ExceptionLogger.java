package com.gmail.calorious.util;

import java.util.function.Function;

public class ExceptionLogger<T> implements Function<Throwable, T> {
	
	@Override
	public T apply(Throwable throwable) {
		Printer.print(" -- Exception Thrown -- ");
		Printer.print("Caused by: " + throwable.getCause());
		Printer.print("Message: " + throwable.getMessage());
		Printer.separator();
		Printer.print(" -- STACK TRACE -- ");
		Printer.separator();
		throwable.printStackTrace();
		Printer.separator();
		Printer.print(" -- STACK TRACE END --");
		return null;
	}
}
