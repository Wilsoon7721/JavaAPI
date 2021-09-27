package com.gmail.calorious.api.javacord.objects;

import java.awt.Color;

import org.javacord.api.entity.permission.Permissions;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.permission.internal.RoleBuilderDelegate;
import org.javacord.api.entity.server.Server;
import org.javacord.api.util.internal.DelegateFactory;

import com.gmail.calorious.util.FutureCompleter;

public class API_RoleBuilder {
	private RoleBuilderDelegate updater;
	protected API_RoleBuilder(Server server) {
		this.updater = DelegateFactory.createRoleBuilderDelegate(server);
	}
	
	public API_RoleBuilder setAuditLogReason(String reason) {
		updater.setAuditLogReason(reason);
		return this;
	}
	
	public API_RoleBuilder setColor(Color color) {
		updater.setColor(color);
		return this;
	}
	
	public API_RoleBuilder setDisplaySeparately(boolean displaySeparately) {
		updater.setDisplaySeparately(displaySeparately);
		return this;
	}
	
	public API_RoleBuilder setMentionable(boolean mentionable) {
		updater.setMentionable(mentionable);
		return this;
	}
	
	public API_RoleBuilder setName(String name) {
		updater.setName(name);
		return this;
	}
	
	public API_RoleBuilder setPermissions(Permissions permissions) {
		updater.setPermissions(permissions);
		return this;
	}
	
	public Role createRole() {
		return FutureCompleter.handle(updater.create());
	}

}
