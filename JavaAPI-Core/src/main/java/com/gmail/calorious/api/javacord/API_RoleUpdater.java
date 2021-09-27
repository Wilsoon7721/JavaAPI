package com.gmail.calorious.api.javacord;

import java.awt.Color;
import java.util.concurrent.CompletableFuture;

import org.javacord.api.entity.permission.Permissions;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.permission.internal.RoleUpdaterDelegate;
import org.javacord.api.util.internal.DelegateFactory;

public class API_RoleUpdater {
	private RoleUpdaterDelegate updater;
	protected API_RoleUpdater(Role role) {
		this.updater = DelegateFactory.createRoleUpdaterDelegate(role);
	}
	
	public API_RoleUpdater setAuditLogReason(String reason) {
		updater.setAuditLogReason(reason);
		return this;
	}
	
	public API_RoleUpdater setColor(Color color) {
		updater.setColor(color);
		return this;
	}
	
	public API_RoleUpdater setDisplaySeparately(boolean displaySeparately) {
		updater.setDisplaySeparatelyFlag(displaySeparately);
		return this;
	}
	
	public API_RoleUpdater setMentionable(boolean mentionable) {
		updater.setMentionableFlag(mentionable);
		return this;
	}
	
	public API_RoleUpdater setName(String name) {
		updater.setName(name);
		return this;
	}
	
	public API_RoleUpdater setPermissions(Permissions permissions) {
		updater.setPermissions(permissions);
		return this;
	}
	
	public CompletableFuture<Void> update() {
		return updater.update();
	}
}
