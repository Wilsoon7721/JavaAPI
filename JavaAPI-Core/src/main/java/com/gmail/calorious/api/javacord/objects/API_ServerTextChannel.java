package com.gmail.calorious.api.javacord.objects;

import java.util.concurrent.TimeUnit;

import org.javacord.api.entity.channel.ChannelCategory;
import org.javacord.api.entity.channel.ChannelType;
import org.javacord.api.entity.channel.ServerTextChannel;

import com.gmail.calorious.util.Formatter;
import com.gmail.calorious.util.FutureCompleter;

public class API_ServerTextChannel implements API_DiscordEntity {
	private ServerTextChannel channel;
	protected API_ServerTextChannel(ServerTextChannel channel) {
		this.channel = channel;
	}
	
	public int getSlowmodeDelay(TimeUnit unit) {	
		int seconds = channel.getSlowmodeDelayInSeconds();
		return Formatter.formatSeconds(seconds, unit);
	}
	
	public String getTopic() {
		return channel.getTopic();
	}
	
	public ChannelType getType() {
		return channel.getType();
	}
	
	public boolean hasSlowmode() {
		return channel.hasSlowmode();
	}
	
	public boolean nsfw() {
		return channel.isNsfw();
	}
	
	public boolean isNsfw() {
		return channel.isNsfw();
	}
	
	public void removeCategory() {
		FutureCompleter.handle(channel.removeCategory());
	}
	
	public void unsetSlowmode() {
		FutureCompleter.handle(channel.unsetSlowmode());
	}
	
	public void removeSlowmode() {
		FutureCompleter.handle(channel.unsetSlowmode());
	}
	
	public void updateCategory(ChannelCategory category) {
		FutureCompleter.handle(channel.updateCategory(category));
	}
	
	public void updateNsfw(boolean nsfw) {
		FutureCompleter.handle(channel.updateNsfwFlag(nsfw));
	}
	
	public void updateSlowmodeDelay(int delay, TimeUnit unit) {
		FutureCompleter.handle(channel.updateSlowmodeDelayInSeconds(Formatter.formatToSeconds(delay, unit)));
	}
	
	public void updateTopic(String topic) {
		FutureCompleter.handle(channel.updateTopic(topic));
	}
	
	public API_ServerTextChannelUpdater updater() {
		return new API_ServerTextChannelUpdater(channel);
	}
	
	public API_ServerTextChannelUpdater callUpdater() {
		return new API_ServerTextChannelUpdater(channel);
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
