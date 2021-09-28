package com.gmail.calorious.api.javacord;

import org.javacord.api.entity.DiscordEntity;
import org.javacord.api.entity.Permissionable;
import org.javacord.api.entity.channel.ChannelCategory;
import org.javacord.api.entity.channel.internal.ChannelCategoryBuilderDelegate;
import org.javacord.api.entity.permission.Permissions;
import org.javacord.api.entity.server.Server;
import org.javacord.api.util.internal.DelegateFactory;

import com.gmail.calorious.util.FutureCompleter;

public class API_ChannelCategoryBuilder {
	private ChannelCategoryBuilderDelegate updater;
	protected API_ChannelCategoryBuilder(Server server) {
		this.updater = DelegateFactory.createChannelCategoryBuilderDelegate(server);
	}
	
	public <T extends Permissionable & DiscordEntity> API_ChannelCategoryBuilder addPermisisonOverwrite(T permissionable, Permissions permissions) {
		updater.addPermissionOverwrite(permissionable, permissions);
		return this;
	}
	
	public <T extends Permissionable & DiscordEntity> API_ChannelCategoryBuilder removePermissionOverwrite(T permissionable, Permissions permissions) {
		updater.removePermissionOverwrite(permissionable);
		return this;
	}
	
	public API_ChannelCategoryBuilder setAuditLogReason(String reason) {
		updater.setAuditLogReason(reason);
		return this;
	}
	
	public API_ChannelCategoryBuilder setName(String name) {
		updater.setName(name);
		return this;
	}
	
	public ChannelCategory create() {
		return FutureCompleter.handle(updater.create());
	}
}
