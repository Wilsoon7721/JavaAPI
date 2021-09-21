package com.gmail.calorious.util;

import java.util.concurrent.CompletableFuture;

public class CompletionBuilder<T> {
	public CompletableFuture<T> future = null;
	public ActionBuilder<T> actionBuilder = null;
	protected CompletionBuilder(CompletableFuture<T> future) {
		this.future = future;
	}
	
	public CompletableFuture<T> getFuture() {
		return future;
	}
	
	public void performAction(boolean async) {
		if(!(async)) { 
			future.thenAccept(actionBuilder.getAction());
			return;
		}
		future.thenAcceptAsync(actionBuilder.getAction());
		return;
	}
	
	public ActionBuilder<T> getActionBuilder() {
		if(actionBuilder == null) actionBuilder = new ActionBuilder<T>(this);
		return actionBuilder;
	}
	
	public CompletionBuilder<T> setActionBuilder(ActionBuilder<T> actions) {
		this.actionBuilder = actions;
		return this;
	}
}
