package com.gmail.calorious.api.jda;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.security.auth.login.LoginException;

import com.gmail.calorious.security.SafeKey;
import com.gmail.calorious.util.Printer;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDA.Status;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;


public class JDA_JavaAPI {
	private static SafeKey tokenKey = null;
	public JDABuilder builder = null;
	public JDA api = null;
	public Thread botThread = null;
	public boolean running = false;
	public Activity activity = null;
	public CacheFlag[] disabledFlags = null;
	public Compression compressionState = Compression.ZLIB;
	public boolean bulkSplitting = false; // Experimental Feature [BULK DELETE EVENT]
	public GatewayIntent[] disabledIntents = null;
	public OnlineStatus onlineStatus = OnlineStatus.ONLINE;
	public boolean autoReconnect = true;
	
	protected JDA_JavaAPI(SafeKey key) {
		tokenKey = key;
	}
	
	public JDA getApi() {
		return api;
	}
	
	protected void setApi(JDA api) {		
		this.api = api;
	}
	
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	

	public void disableCache(CacheFlag... cacheFlags) {
		disabledFlags = cacheFlags;
	}
	
	public void disableCompression() {
		compressionState = Compression.NONE;
	}
	
	public void enableBulkDeleteEvent() {
		bulkSplitting = true;
	}
	
	public void disableGatewayIntents(GatewayIntent... intents) {
		disabledIntents = intents;
	}
	
	public void disableAutoReconnect() {
		autoReconnect = false;
	}
	
	public void setStatus(OnlineStatus status) {
		onlineStatus = status;
	}
	
	public synchronized void startBot() {
		Thread currentThread = Thread.currentThread();
		botThread = new Thread() {
			@Override
			public void run() {
				try {
					builder = JDABuilder.createDefault(tokenKey.decrypt());
				} catch(InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {}
				builder.setBulkDeleteSplittingEnabled(bulkSplitting);
				builder.setEnabledIntents(Arrays.asList(GatewayIntent.values()));
				builder.disableIntents(Arrays.asList(disabledIntents));
				builder.setCompression(compressionState);
				builder.disableCache(Arrays.asList(disabledFlags));
				builder.setActivity(activity);
				builder.setStatus(onlineStatus);
				builder.setAutoReconnect(autoReconnect);
				builder.setEnableShutdownHook(true);
				JDA jda = null;
				try {
					jda = builder.build();
				} catch (LoginException e) {
					System.out.println("JDA - Login failed.");
					e.printStackTrace();
					return;
				}
				Printer.print("Successfully logged in as " + jda.getSelfUser().getName() + "#" + jda.getSelfUser().getDiscriminator() + ".");
				Printer.print("JavaAPI is performing API functions on behalf of application: " + JDARegistration.getApplicationNameFromKey(tokenKey));
				if(jda.getAccountType() != AccountType.BOT) {
					Printer.separator();
					Printer.print("WARNING: Client bots are not supported by Discord and may lead to an account ban. Please use at your own risk.");
					Printer.separator();
				}
				Printer.print(" -- Ready to accept methods passed by API -- ");
				setApi(jda);
				currentThread.notifyAll();
			}
		};
	}
	
	public synchronized SelfUser getBotUser() {
		while(api == null) {
			try {
				this.wait();
			} catch (InterruptedException e) {}
		}
		return api.getSelfUser();
	}
	
	public synchronized User getUserById(long id) {
		while(api == null) {
			try {
				this.wait();
			} catch (InterruptedException e) {}
		}
		return api.getUserById(id);
	}
	
	public synchronized User getUserById(String id) {
		while(api == null) {
			try {
				this.wait();
			} catch (InterruptedException e) {}
		}
		return api.getUserById(id);
	}
	
	public synchronized AccountType getAccountType() {
		while(api == null) {
			try {
				this.wait();
			} catch(InterruptedException ex) {}
		}
		return api.getAccountType();
	}
	
	public synchronized Status getAPIStatus() {
		while(api == null) {
			try {
				this.wait();
			} catch(InterruptedException e) {}
		}
		return api.getStatus();
	}
	
	public synchronized ScheduledFuture<?> scheduleTask(Runnable runnable, long delay, TimeUnit delayUnit) {
		while(api == null) {
			try {
				this.wait();
			} catch(InterruptedException ex) {}
		}
		return api.getGatewayPool().schedule(runnable, delay, delayUnit);
	}
	
	public synchronized JDA getAPI() {
		return api;
	}
}
