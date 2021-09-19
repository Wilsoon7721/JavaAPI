package com.gmail.calorious.api.spigot.plugins.luckperms;

import org.bukkit.Bukkit;

import net.luckperms.api.LuckPerms;

public class LPIntegration {
	public static boolean initialized = false;
	private static LuckPerms lp = null;
	private static LPGroupManager lpgroupmanager = null;
	public static void initialize() {
		lp = Bukkit.getServicesManager().getRegistration(LuckPerms.class).getProvider();
		lpgroupmanager = new LPGroupManager();
		initialized = true;
	}
	
	public static LPGroupManager getGroupManager() {
		return lpgroupmanager;
	}

	
	
	
	public static LuckPerms getAPIInstance() {
		return lp;
	}
}
