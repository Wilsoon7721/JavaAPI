package com.gmail.calorious.api.javacord;

import java.util.HashMap;

import org.javacord.api.entity.Icon;
import org.javacord.api.entity.user.User;

import com.gmail.calorious.api.Factory;

public class API_User {
	private String discriminator = "";
	private String username = "";
	private String combinedUser = "";
	private User user = null;
	protected API_User(User user) {
		if(user == null) return;
		discriminator = user.getDiscriminator();
		username = user.getName();
		combinedUser = user.getDiscriminatedName();
		this.user = user;
	}
	
	public void update() {
		HashMap<String, String> map = Factory.Javacord.updateUser(user);
		this.discriminator = map.get("discriminator");
		this.username = map.get("username");
		this.combinedUser = map.get("combined");
		// Complete
		return;
	}
	
	public Icon getAvatar() {
		return user.getAvatar();
	}
	
	public long getUserID() {
		return user.getId();
	}
	
	public String getCombinedUsername() {
		return combinedUser;
	}
	
	public String getDiscriminator() {
		return discriminator;
	}
	
	public String getUsername() {
		return username;
	}
}
