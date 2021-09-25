package com.gmail.calorious.api.javacord.objects;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.javacord.api.entity.channel.ChannelCategory;
import org.javacord.api.entity.channel.ChannelType;
import org.javacord.api.entity.channel.ServerTextChannel;

import com.gmail.calorious.util.Formatter;
import com.gmail.calorious.util.FutureCompleter;

public class API_ServerTextChannel {
	private ServerTextChannel channel;
	protected API_ServerTextChannel(ServerTextChannel channel) {
		this.channel = channel;
	}
	
	public int getSlowmodeDelay(TimeUnit unit) {
		if(unit == null || unit == TimeUnit.SECONDS) return channel.getSlowmodeDelayInSeconds();
		if(unit == TimeUnit.MINUTES) return channel.getSlowmodeDelayInSeconds() / 60;
		if(unit == TimeUnit.HOURS) return channel.getSlowmodeDelayInSeconds() / 3600;
		if(unit == TimeUnit.DAYS) return channel.getSlowmodeDelayInSeconds() / 86400;
		if(unit == TimeUnit.MILLISECONDS) return channel.getSlowmodeDelayInSeconds() / 1000;
		if(unit == TimeUnit.MICROSECONDS) return channel.getSlowmodeDelayInSeconds() / 1000000;
		if(unit == TimeUnit.NANOSECONDS) return channel.getSlowmodeDelayInSeconds() / 1000000000;
		return -1;
	}
	
	public String getCreationTimestamp(DateTimeFormatter formatter) {
		String obj = Formatter.formatTimestamp(channel.getCreationTimestamp(), formatter);
		return obj;
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
	
	public void updateCategory(ChannelCategory category) {
		FutureCompleter.handle(channel.updateCategory(category));
	}
	
	public void setNsfw(boolean nsfw) { 
		FutureCompleter.handle(channel.updateNsfwFlag(nsfw));
	}
	
	public void setSlowmodeDelay(int time, TimeUnit unit) {
		if(unit == TimeUnit.NANOSECONDS) {
			int seconds = time / 1000000000;
			FutureCompleter.handle(channel.updateSlowmodeDelayInSeconds(seconds));
			
		}
		if(unit == TimeUnit.MICROSECONDS) {
			int seconds = time / 1000000;
			FutureCompleter.handle(channel.updateSlowmodeDelayInSeconds(seconds));
			
		}
		if(unit == TimeUnit.MILLISECONDS) {
			int seconds = time / 1000;
			FutureCompleter.handle(channel.updateSlowmodeDelayInSeconds(seconds));
			
		}
		if(unit == TimeUnit.SECONDS) {
			FutureCompleter.handle(channel.updateSlowmodeDelayInSeconds(time));
		}
		if(unit == TimeUnit.MINUTES) {
			int seconds = time * 60;
			FutureCompleter.handle(channel.updateSlowmodeDelayInSeconds(seconds));
		}
		if(unit == TimeUnit.HOURS) {
			int seconds = time * 3600;
			FutureCompleter.handle(channel.updateSlowmodeDelayInSeconds(seconds));
			
		}
		if(unit == TimeUnit.DAYS) {
			int seconds = time * 86400;
			FutureCompleter.handle(channel.updateSlowmodeDelayInSeconds(seconds));
		}
		return;
	}
	
	public void removeSlowmode() {
		FutureCompleter.handle(channel.unsetSlowmode());
	}
	
	public long getId() {
		return channel.getId();
	}
	
	public String getMentionTag() {
		return channel.getMentionTag();
	}
	
	public API_ServerTextChannelUpdater updater() {
		return new API_ServerTextChannelUpdater(channel);
	}
}
