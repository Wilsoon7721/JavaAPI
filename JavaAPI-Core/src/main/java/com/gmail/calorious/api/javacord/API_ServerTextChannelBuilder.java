package com.gmail.calorious.api.javacord;

import java.util.concurrent.TimeUnit;

import org.javacord.api.entity.DiscordEntity;
import org.javacord.api.entity.Permissionable;
import org.javacord.api.entity.channel.ChannelCategory;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.internal.ServerTextChannelBuilderDelegate;
import org.javacord.api.entity.permission.Permissions;
import org.javacord.api.entity.server.Server;
import org.javacord.api.util.internal.DelegateFactory;

import com.gmail.calorious.util.Formatter;
import com.gmail.calorious.util.FutureCompleter;

public class API_ServerTextChannelBuilder {
	private ServerTextChannelBuilderDelegate updater;
	protected API_ServerTextChannelBuilder(Server server) {
		this.updater = DelegateFactory.createServerTextChannelBuilderDelegate(server);
	}
	
	public API_ServerTextChannelBuilder setAuditLogReason(String reason) {
		updater.setAuditLogReason(reason);
		return this;
	}
	
	public API_ServerTextChannelBuilder setName(String name) {
		updater.setName(name);
		return this;
	}
	
	public API_ServerTextChannelBuilder setTopic(String topic) {
		updater.setTopic(topic);
		return this;
	}
	
	public API_ServerTextChannelBuilder setCategory(ChannelCategory category) {
		updater.setCategory(category);
		return this;
	}
	
	public API_ServerTextChannelBuilder setSlowmodeDelay(int time, TimeUnit unit) {
		updater.setSlowmodeDelayInSeconds(Formatter.formatToSeconds(time, unit));
		return this;
	}
	
	public <T extends Permissionable & DiscordEntity> API_ServerTextChannelBuilder addPermissionOverwrite(T permissionable, Permissions permissions) {
		updater.addPermissionOverwrite(permissionable, permissions);
		return this;
	}
	
	public <T extends Permissionable & DiscordEntity> API_ServerTextChannelBuilder removePermissionOverwrite(T permissionable) {
		updater.removePermissionOverwrite(permissionable);
		return this;
	}
	
	public ServerTextChannel create() {
		return FutureCompleter.handle(updater.create());
	}
}
