package com.gmail.calorious.api.javacord.objects;

import java.util.concurrent.CompletableFuture;

import org.javacord.api.entity.DiscordEntity;
import org.javacord.api.entity.Permissionable;
import org.javacord.api.entity.channel.ChannelCategory;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.internal.ServerVoiceChannelUpdaterDelegate;
import org.javacord.api.entity.permission.Permissions;
import org.javacord.api.util.internal.DelegateFactory;

public class API_ServerVoiceChannelUpdater {
	private ServerVoiceChannelUpdaterDelegate updater;
	protected API_ServerVoiceChannelUpdater(ServerVoiceChannel channel) {
		this.updater = DelegateFactory.createServerVoiceChannelUpdaterDelegate(channel);
	}
	
	public <T extends Permissionable & DiscordEntity> API_ServerVoiceChannelUpdater addPermissionOverwrite(T permissionable, Permissions permissions) {
		updater.addPermissionOverwrite(permissionable, permissions);
		return this;
	}
	
	public API_ServerVoiceChannelUpdater removeCategory() {
		updater.removeCategory();
		return this;
	}
	
	public <T extends Permissionable & DiscordEntity> API_ServerVoiceChannelUpdater removePermissionOverwrite(T permissionable) {
		updater.removePermissionOverwrite(permissionable);
		return this;
	}
	
	public API_ServerVoiceChannelUpdater removeUserLimit() {
		updater.removeUserLimit();
		return this;
	}
	
	public API_ServerVoiceChannelUpdater setAuditLogReason(String reason) {
		updater.setAuditLogReason(reason);
		return this;
	}
	
	public API_ServerVoiceChannelUpdater setBitrate(int bitrate) {
		updater.setBitrate(bitrate);
		return this;
	}
	
	public API_ServerVoiceChannelUpdater setCategory(ChannelCategory category) {
		updater.setCategory(category);
		return this;
	}
	
	public API_ServerVoiceChannelUpdater setName(String name) {
		updater.setName(name);
		return this;
	}
	
	public API_ServerVoiceChannelUpdater setRawPosition(int rawPosition) {
		updater.setRawPosition(rawPosition);
		return this;
	}
	
	public API_ServerVoiceChannelUpdater setUserLimit(int userLimit) {
		updater.setUserLimit(userLimit);
		return this;
	}
	
	public CompletableFuture<Void> update() {
		return updater.update();
	}
}
