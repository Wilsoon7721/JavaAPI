package com.gmail.calorious.api.javacord;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.javacord.api.AccountType;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

import com.gmail.calorious.security.SafeKey;
import com.gmail.calorious.util.Defaults;
import com.gmail.calorious.util.ExceptionLogger;
import com.gmail.calorious.util.Printer;

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
		/*
		 * SafeKey must be established properly as it involves a private key that could cause credential leak
		 */
		this.tokenKey = tokenKey;
	}
	
	public synchronized User getBotUser() {
		while(api == null) {
			try {
				this.wait();
			} catch(InterruptedException e) {}
		}
		return api.getYourself();
	}
	
	public synchronized API_RoleBuilder createRoleBuilder(Server server) {
		while(api == null)	{
			try {
				this.wait();
			} catch(InterruptedException e) {}	
		}
		return new API_RoleBuilder(server);
	}
	
	public synchronized void startBot() {
		Thread currentThread = Thread.currentThread();
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
				Printer.print("Successfully logged in as " + api.getYourself().getDiscriminatedName() + ".");
				Printer.print("JavaAPI is performing API functions on behalf of application: " + JavacordRegistration.getApplicationNameFromKey(tokenKey));
				if(api.getAccountType() == AccountType.CLIENT) {
					Printer.separator();
					Printer.print("WARNING: Client bots are not supported by Discord and may lead to an account ban. Please use at your own risk.");
					Printer.separator();
				}
				Printer.print(" -- Ready to accept methods passed by API -- ");
				Printer.separator();
				setApi(api);
				running = true;
				Defaults.registerDefaultListeners(api);
				currentThread.notifyAll();
			}
		};
		thread.start();
	}
	
	public synchronized API_User getUserById(long id) {
		User user = api.getUserById(id).exceptionally(new ExceptionLogger<User>()).join();
		return new API_User(user);
	}
	
	public synchronized API_User getUserById(String id) {
		User user = api.getUserById(id).exceptionally(new ExceptionLogger<User>()).join();
		return new API_User(user);
	}
	
	public void stopBot() {
		if(thread.isInterrupted()) throw new IllegalStateException("Thread already interrupted!");
		thread.interrupt();
		Printer.print("Successfully stopped the bot.");
		return;
	}
	
	public void setAccountType(AccountType type) {
		builder.setAccountType(type);
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
	
	public Thread getBotThread() {
		return thread;
	}
	
}
