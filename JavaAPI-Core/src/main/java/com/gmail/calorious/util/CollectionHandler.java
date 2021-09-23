package com.gmail.calorious.util;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectionHandler {
	
	public static enum Type {	
		LIST, SET;
	}
	
	private CollectionHandler() {}
	
	public static <T> void performActionOnAllElements(Collection<T> collection, Consumer<? super T> action) {
		collection.forEach(action);
		return;
	}
	
	// Returns collection wtih matching entries if boolean is true OR returns collection with non matching entries if boolean is false
	public static <T> Collection<T> filter(Collection<T> collection, Predicate<? super T> filter, boolean removeNonMatching) {
		if(removeNonMatching) return collection.stream().filter(filter).collect(Collectors.toSet());
		return collection.stream().filter(filter.negate()).collect(Collectors.toSet());
	}
	
	public static <T> Collection<T> convertType(Collection<T> collection, CollectionHandler.Type type) {
		if(type == CollectionHandler.Type.LIST) {
			return collection.stream().collect(Collectors.toList());
		}
		if(type == CollectionHandler.Type.SET) {
			return collection.stream().collect(Collectors.toSet());
		}
		return null;
	}
}
