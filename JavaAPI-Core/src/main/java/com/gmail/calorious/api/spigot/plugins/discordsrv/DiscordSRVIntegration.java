package com.gmail.calorious.api.spigot.plugins.discordsrv;

import org.bukkit.Bukkit;

import github.scarsz.discordsrv.DiscordSRV;

public class DiscordSRVIntegration {
	public static boolean initialized = false;
	private static DiscordSRV discordsrv = null;
	public static void initialize() {
		discordsrv = (DiscordSRV) Bukkit.getPluginManager().getPlugin("DiscordSRV");
		initialized = true;
	}
	// No Built-in methods
	
	public static DiscordSRV getAPIInstance() {
		return discordsrv;
	}
}
