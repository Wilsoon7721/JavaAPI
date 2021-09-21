package com.gmail.calorious.util;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ActionBuilder<T> {
	private CompletionBuilder<T> completer = null;
	private Consumer<? super T> action = null;
	protected ActionBuilder(CompletionBuilder<T> completer) {
		this.completer = completer;
	}
	
	// Updates action builder and immediately executes Future according to parameters provided.
	public CompletableFuture<Void> execute(boolean async) {
		completer.setActionBuilder(this);
		return completer.execute(async);
	}
	
	public CompletionBuilder<T> build() {
		completer.setActionBuilder(this);
		return completer;
	}
	
	public ActionBuilder<T> setAction(Consumer<? super T> action) {
		if(action == null) throw new NullPointerException("Action cannot be null.");
		this.action = action;
		return this;
	}
	
	protected Consumer<? super T> getAction() {
		return action;
	}
	
	public CompletionBuilder<T> getCompleter() {
		return completer;
	}
	
	protected ActionBuilder<T> setCompleter(CompletionBuilder<T> completer){
		this.completer = completer;
		return this;
	}
}
