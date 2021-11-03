package com.gmail.calorious.api;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.user.User;

import com.gmail.calorious.api.javacord.util.UserDetail;

public class Factory {
	private Factory() {}
	public static class Javacord {
		// Returns a HashMap with name, discriminator and discriminated name.
		public static HashMap<UserDetail, String> updateUser(User user) {
			HashMap<UserDetail, String> map = new HashMap<>();
			map.put(UserDetail.DISCRIMINATOR, user.getDiscriminator());
			map.put(UserDetail.USERNAME, user.getName());
			map.put(UserDetail.COMBINED_NAME, user.getDiscriminatedName());
			return map;
		}
		
		public static void deleteMessageAfter(DiscordApi api, org.javacord.api.entity.message.Message message, long time, TimeUnit timeUnit) {
			api.getThreadPool().getScheduler().schedule(() -> message.delete(), time, timeUnit);
			return;
		}
	}
	
	public static class JDA {
		
		public static void deleteMessageAfter(net.dv8tion.jda.api.JDA jda, net.dv8tion.jda.api.entities.Message message, long time, TimeUnit timeUnit) {
			jda.getGatewayPool().schedule(() -> message.delete().reason("Bot commanded action - com.gmail.calorious.api.Factory.JDA").complete(), time, timeUnit);
		}
	}
}
