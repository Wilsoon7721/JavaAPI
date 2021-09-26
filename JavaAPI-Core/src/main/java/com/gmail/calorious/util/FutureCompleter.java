package com.gmail.calorious.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class FutureCompleter {
	
	private FutureCompleter() {}
	
	public static <T> CompletableFuture<Void> completeFuture(CompletableFuture<T> future, Consumer<? super T> action) {
		CompletionBuilder<T> builder = new CompletionBuilder<T>(future);
		return builder.getActionBuilder().setAction(action).execute(false);
	}
	
	public static <T> CompletableFuture<Void> completeFuture(CompletableFuture<T> future, Consumer<? super T> action, boolean async) {
		CompletionBuilder<T> builder = new CompletionBuilder<T>(future);
		return builder.getActionBuilder().setAction(action).execute(async);
	}
	
	public static <T> T getResult(CompletableFuture<T> future) {
		T obj = null;
		try {
			obj = future.get();
		} catch(InterruptedException | ExecutionException e) {
			e.printStackTrace();
			System.out.println("Failed to invoke getResult on CompletableFuture object.");
			System.out.println("Caused by: " + e.getLocalizedMessage());
			return null;
		}
		if(obj == null) throw new RuntimeException("Could not get object. CompletableFuture<T> - Object is null.");
		return obj;
	}
	
	public static <T> T handle(CompletableFuture<T> future) {
		return future.join();
	}
}
