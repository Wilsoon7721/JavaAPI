package com.gmail.calorious.util;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class OptionalHandler {
	
	public static <T> T getResult(Optional<T> optional) {
		return optional.get();
	}
	
	public static <T> boolean hasResult(Optional<T> optional) {
		return optional.isPresent();
	}
	
	public static <T> void functionIfPresent(Optional<T> optional, Consumer<? super T> action) {
		optional.ifPresent(action);
	}
	
	public static <T> Optional<T> empty(Optional<T> optional) {
		optional = Optional.empty();
		return optional;
	}
	
	public static <T, U> Optional<U> map(Optional<T> optional, Function<? super T, ? extends U> function) { 
		return optional.map(function);
	}
	
	public static <T, U> Optional<U> flatMap(Optional<T> optional, Function<? super T, Optional<U>> function) {
		return optional.flatMap(function);
	}
}
