package com.gmail.calorious.util;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class CompletionBuilder<T> {
	private CompletableFuture<T> future = null;
	private ActionBuilder<T> actionBuilder = null;
	protected CompletionBuilder(CompletableFuture<T> future) {
		this.future = future;
	}
	
	public void setFallback(Function<Throwable, ? extends T> fallBack) {
		future.exceptionally(fallBack);
		return;
	}
	
	// Returns code from execution
	public CompletableFuture<Void> execute(boolean async) {
		if(!(async)) { 
			CompletableFuture<Void> completed = future.thenAccept(actionBuilder.getAction());
			if(completed.isCompletedExceptionally() || completed.isCancelled()) return null;; 
			return completed;
		}
		CompletableFuture<Void> completed = future.thenAcceptAsync(actionBuilder.getAction());
		if(completed.isCompletedExceptionally() || completed.isCancelled()) return null;
		return completed;
	}
	
	public CompletableFuture<T> getFuture() {
		return future;
	}
	
	public ActionBuilder<T> getActionBuilder() {
		if(actionBuilder == null) actionBuilder = new ActionBuilder<T>(this);
		return actionBuilder;
	}
	
	public CompletionBuilder<T> setActionBuilder(ActionBuilder<T> builder) {
		this.actionBuilder = builder;
		return this;
	}
}
