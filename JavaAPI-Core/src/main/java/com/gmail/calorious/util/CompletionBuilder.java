package com.gmail.calorious.util;

import java.util.concurrent.CompletableFuture;

public class CompletionBuilder<T> {
	public CompletableFuture<T> future = null;
	protected CompletionBuilder(CompletableFuture<T> future) {
		this.future = future;
	}
}
