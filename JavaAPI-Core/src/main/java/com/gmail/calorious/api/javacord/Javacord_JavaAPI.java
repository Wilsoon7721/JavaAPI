package com.gmail.calorious.api.javacord;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

import com.gmail.calorious.security.SafeKey;

public class Javacord_JavaAPI {	
	private SafeKey tokenKey = null;
	public Intent[] intents = new Intent[32];
	public boolean guild_presences = false;
	public boolean guild_members = false;
	public boolean running = false;
	public DiscordApiBuilder builder = null;
	private DiscordApi api = null;
	public Thread thread = null;
	
	public DiscordApi getApi() {
		return api;
	}
	
	protected void setApi(DiscordApi api) {
		this.api = api;
	}
	
	protected Javacord_JavaAPI(SafeKey tokenKey) {
		this.tokenKey = tokenKey;
	}
	
	public void startBot() {
		thread = new Thread() {
			@Override
			public void run() {
				try {
					builder = new DiscordApiBuilder().setToken(tokenKey.decrypt());
				} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {}
				if(guild_presences && guild_members) {
					// Both enable
					builder.setAllIntentsExcept(intents);
				} else if(!(guild_presences && guild_members)) {
					// Both disable
					builder.setAllNonPrivilegedIntentsExcept(intents);
				} else if(!(guild_presences) && guild_members) {
					// presences disable only
					ArrayList<Intent> list = new ArrayList<Intent>(Arrays.asList(intents));
					list.add(Intent.GUILD_PRESENCES);
					builder.setAllIntentsExcept(list.toArray(new Intent[list.size()]));
				} else if(guild_presences && !(guild_members)) {
					// members disable only
					ArrayList<Intent> list = new ArrayList<Intent>(Arrays.asList(intents));
					list.add(Intent.GUILD_MEMBERS);
					builder.setAllIntentsExcept(list.toArray(new Intent[list.size()]));
				}
				DiscordApi api = builder.login().join();
				System.out.println("Successfully logged in as " + api.getYourself().getDiscriminatedName() + ".");
				System.out.println("JavaAPI is performing API functions on behalf of application: " + JavacordRegistration.getApplicationNameFromKey(tokenKey));
				setApi(api);
				running = true;
			}
		};
		thread.start();
	}
	
	public void stopBot() {
		if(thread.isInterrupted()) throw new IllegalStateException("Thread already interrupted!");
		thread.interrupt();
		System.out.println("Bot's main thread was interrupted.");
		return;
	}
	
	public void disableIntents(Intent[] intents) {
		this.intents = intents;
		return;
	}
	
	public void modifyPrivilegedIntents(boolean guild_presences, boolean guild_members) {
		this.guild_presences = guild_presences;
		this.guild_members = guild_members;
		return;
	}
	
	public boolean isBotRunning() {
		return running;
	}
	
}
