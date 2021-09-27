package com.gmail.calorious.api.javacord;

import org.javacord.api.entity.DiscordEntity;
import org.javacord.api.entity.Permissionable;
import org.javacord.api.entity.channel.ChannelCategory;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.internal.ServerVoiceChannelBuilderDelegate;
import org.javacord.api.entity.permission.Permissions;
import org.javacord.api.entity.server.Server;
import org.javacord.api.util.internal.DelegateFactory;

import com.gmail.calorious.util.FutureCompleter;

public class API_ServerVoiceChannelBuilder {
	private ServerVoiceChannelBuilderDelegate updater;
	protected API_ServerVoiceChannelBuilder(Server server) {
		this.updater = DelegateFactory.createServerVoiceChannelBuilderDelegate(server);
	}
	
	public API_ServerVoiceChannelBuilder setAuditLogReason(String reason) {
		updater.setAuditLogReason(reason);
		return this;
	}
	
	public API_ServerVoiceChannelBuilder setName(String name) {
		updater.setName(name);
		return this;
	}
	
	public API_ServerVoiceChannelBuilder setCategory(ChannelCategory category) {
		updater.setCategory(category);
		return this;
	}
	
	public <T extends Permissionable & DiscordEntity> API_ServerVoiceChannelBuilder addPermissionOverwrite(T permissionable, Permissions permissions) {
		updater.addPermissionOverwrite(permissionable, permissions);
		return this;
	}
	
	public <T extends Permissionable & DiscordEntity> API_ServerVoiceChannelBuilder removePermissionOverwrite(T permissionable) {
		updater.removePermissionOverwrite(permissionable);
		return this;
	}
	
	public API_ServerVoiceChannelBuilder setBitrate(int bitrate) {
		updater.setBitrate(bitrate);
		return this;
	}
	
	public API_ServerVoiceChannelBuilder setUserLimit(int userLimit) {
		updater.setUserlimit(userLimit);
		return this;
	}
	
	public ServerVoiceChannel create() {
		return FutureCompleter.handle(updater.create());
	}
}
