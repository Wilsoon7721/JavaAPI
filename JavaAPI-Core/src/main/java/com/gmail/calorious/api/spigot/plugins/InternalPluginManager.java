package com.gmail.calorious.api.spigot.plugins;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.gmail.calorious.api.spigot.plugins.coreprotect.CPIntegration;
import com.gmail.calorious.api.spigot.plugins.discordsrv.DiscordSRVIntegration;
import com.gmail.calorious.api.spigot.plugins.luckperms.LPIntegration;
import com.gmail.calorious.api.spigot.plugins.multiverse.MVCoreIntegration;
import com.gmail.calorious.api.spigot.util.MsgUtils;

import net.luckperms.api.LuckPerms;

public class InternalPluginManager {
  // Manages API Hooks
	// Do not hook if plugin does not exist.
	private InternalPluginManager() {} // Do not instanitate
	private static int activatedDependencies = 0;
	public static void startAll() {
		// CoreProtect
		if(Bukkit.getPluginManager().getPlugin("CoreProtect") != null) {
			CPIntegration.initialize();
			activatedDependencies++;
		}
		// DiscordSRV
		if(Bukkit.getPluginManager().getPlugin("DiscordSRV") != null) {
			DiscordSRVIntegration.initialize();
			activatedDependencies++;
		}
		// LuckPerms
		RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
		if(provider != null) {
			LPIntegration.initialize();
			activatedDependencies++;
		}
		// MVCore
		if(Bukkit.getPluginManager().getPlugin("MultiverseCore") != null) {
			MVCoreIntegration.initialize();
			activatedDependencies++;
		}
		MsgUtils.sendConsoleMessage("&aSuccessfully loaded " + activatedDependencies + " dependencies.");
		return;
	}
}
