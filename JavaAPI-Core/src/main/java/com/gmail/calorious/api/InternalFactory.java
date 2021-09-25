package com.gmail.calorious.api;

import java.util.HashMap;

import org.javacord.api.entity.user.User;

import com.gmail.calorious.api.javacord.util.UserDetail;

public class InternalFactory {
	private InternalFactory() {}
	public static class Javacord {
		// Returns a HashMap with name, discriminator and discriminated name.
		public static HashMap<UserDetail, String> updateUser(User user) {
			HashMap<UserDetail, String> map = new HashMap<>();
			map.put(UserDetail.DISCRIMINATOR, user.getDiscriminator());
			map.put(UserDetail.USERNAME, user.getName());
			map.put(UserDetail.COMBINED_NAME, user.getDiscriminatedName());
			return map;
		}
	}
	
	public static class JDA {
		
	}
}
