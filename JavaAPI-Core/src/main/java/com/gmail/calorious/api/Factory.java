package com.gmail.calorious.api;

import java.util.HashMap;

import org.javacord.api.entity.user.User;

public class Factory {
	private Factory() {}
	public static class Javacord {
		// Returns a HashMap with name, discriminator and discriminated name.
		public static HashMap<String, String> updateUser(User user) {
			HashMap<String, String> map = new HashMap<>();
			map.put("discriminator", user.getDiscriminator());
			map.put("username", user.getName());
			map.put("combined", user.getDiscriminatedName());
			return map;
		}
	}
	
	public static class JDA {
		
	}
}
