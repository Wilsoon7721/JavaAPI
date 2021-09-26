package com.gmail.calorious.api.javacord.objects;

import java.util.Collection;

import org.javacord.api.entity.channel.ChannelCategory;
import org.javacord.api.entity.channel.ChannelType;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.user.User;

import com.gmail.calorious.util.FutureCompleter;

public class API_ServerVoiceChannel implements API_DiscordEntity {
	private ServerVoiceChannel channel;
	protected API_ServerVoiceChannel(ServerVoiceChannel channel) {
		this.channel = channel;
	}
	
	public int getBitrate() {
		return channel.getBitrate();
	}
	
	public Collection<Long> getConnectedUserIds() {
		return channel.getConnectedUserIds();
	}
	
	public Collection<User> getConnectedUsers(){
		return channel.getConnectedUsers();
	}
	
	public ChannelType getType() {
		return channel.getType();
	}
	
	public int getUserLimit() {
		if(channel.getUserLimit().isPresent()) return channel.getUserLimit().get();
		return -1;
	}
	
	public boolean isConnected(long userId) {
		return channel.isConnected(userId);
	}
	
	public boolean isConnected(User user) {
		return channel.isConnected(user);
	}
	
	public void removeCategory() {
		FutureCompleter.handle(channel.removeCategory());
	}
	
	public void removeUserLimit() {
		FutureCompleter.handle(channel.removeUserLimit());
	}
	
	public void updateBitrate(int bitrate) {
		FutureCompleter.handle(channel.updateBitrate(bitrate));
	}
	
	public void updateCategory(ChannelCategory category) {
		FutureCompleter.handle(channel.updateCategory(category));
	}
	
	public void updateUserLimit(int userLimit) {
		FutureCompleter.handle(channel.updateUserLimit(userLimit));
	}
	
	public API_ServerVoiceChannelUpdater updater() {
		return new API_ServerVoiceChannelUpdater(channel);
	}
	
	public API_ServerVoiceChannelUpdater callUpdater() {
		return new API_ServerVoiceChannelUpdater(channel);
	}
	
	@Override
	public long getId() {
		return channel.getId();
	}

	@Override
	public String getIdAsString() {
		return channel.getIdAsString();
	}

	@Override
	public String getName() {
		return channel.getName();
	}
	
}
