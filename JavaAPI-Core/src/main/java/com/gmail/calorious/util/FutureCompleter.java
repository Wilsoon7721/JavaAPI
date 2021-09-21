package com.gmail.calorious.util;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class FutureCompleter {
	
	public static <T> CompletableFuture<T> completeFuture(CompletableFuture<T> future, Consumer<? super T> action) {
		CompletionBuilder<T> builder = new CompletionBuilder<T>(future);
	}
}
