package com.gmail.calorious.api.javacord;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.javacord.api.entity.DiscordEntity;
import org.javacord.api.entity.Permissionable;
import org.javacord.api.entity.channel.ChannelCategory;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.internal.ServerTextChannelUpdaterDelegate;
import org.javacord.api.entity.permission.Permissions;
import org.javacord.api.util.internal.DelegateFactory;

import com.gmail.calorious.util.Formatter;

public class API_ServerTextChannelUpdater {
	private ServerTextChannelUpdaterDelegate updater;
	protected API_ServerTextChannelUpdater(ServerTextChannel channel) {
		this.updater = DelegateFactory.createServerTextChannelUpdaterDelegate(channel);
	}
	
	public <T extends Permissionable & DiscordEntity> API_ServerTextChannelUpdater addPermissionOverwrite(T permissionable, Permissions permissions) {
		updater.addPermissionOverwrite(permissionable, permissions);
		return this;
	}
	
	public <T extends Permissionable & DiscordEntity> API_ServerTextChannelUpdater removePermissionOverwrite(T permissionable) {
		updater.removePermissionOverwrite(permissionable);
		return this;
	}
	
	public API_ServerTextChannelUpdater removeCategory() {
		updater.removeCategory();
		return this;
	}
	
	public API_ServerTextChannelUpdater setAuditLogReason(String reason) {
		updater.setAuditLogReason(reason);
		return this;
	}
	
	public API_ServerTextChannelUpdater setCategory(ChannelCategory category) {
		updater.setCategory(category);
		return this;
	}
	
	public API_ServerTextChannelUpdater setName(String name) {
		updater.setName(name);
		return this;
	}
	
	public API_ServerTextChannelUpdater setNsfw(boolean nsfw) {
		updater.setNsfwFlag(nsfw);
		return this;
	}
	
	public API_ServerTextChannelUpdater nsfw(boolean nsfw) {
		updater.setNsfwFlag(nsfw);
		return this;
	}
	
	public API_ServerTextChannelUpdater setRawPosition(int rawPosition) {
		updater.setRawPosition(rawPosition);
		return this;
	}
	
	public API_ServerTextChannelUpdater setSlowmodeDelay(int delay, TimeUnit unit) {
		updater.setSlowmodeDelayInSeconds(Formatter.formatToSeconds(delay, unit));
		return this;
	}
	
	public API_ServerTextChannelUpdater setTopic(String topic) {
		updater.setTopic(topic);
		return this;
	}
	
	public CompletableFuture<Void> update() {
		return updater.update();
	}
}
