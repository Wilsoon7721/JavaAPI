package com.gmail.calorious.api.javacord.util;

import org.javacord.api.BotInviteBuilder;
import org.javacord.api.entity.permission.Permissions;

public class BotInvite {
	private static BotInviteBuilder delegate;
	public BotInvite(long clientId) {		
		delegate = new BotInviteBuilder(clientId);
	}
	
	public BotInvite setPermissions(Permissions permissions) {
		delegate.setPermissions(permissions);
		return this;
	}
	
	public String build() {
		return delegate.build();
	}
}
