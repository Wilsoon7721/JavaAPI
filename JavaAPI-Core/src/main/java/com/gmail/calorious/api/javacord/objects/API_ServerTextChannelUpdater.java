package com.gmail.calorious.api.javacord.objects;

import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.internal.ServerTextChannelUpdaterDelegate;
import org.javacord.api.util.internal.DelegateFactory;

public class API_ServerTextChannelUpdater {
	private ServerTextChannelUpdaterDelegate updater;
	protected API_ServerTextChannelUpdater(ServerTextChannel channel) {
		if(channel == null) throw new NullPointerException("Server cannot be null!");
		this.updater = DelegateFactory.createServerTextChannelUpdaterDelegate(channel);
	}
}
