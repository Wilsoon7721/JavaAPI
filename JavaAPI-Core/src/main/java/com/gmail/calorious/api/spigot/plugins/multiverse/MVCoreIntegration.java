package com.gmail.calorious.api.spigot.plugins.multiverse;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldType;

import com.onarandombox.MultiverseCore.MultiverseCore;

public class MVCoreIntegration {
	public static boolean initialized = false;
	private static MultiverseCore mvcore = null;
	public static void initialize() {
		mvcore = (MultiverseCore) Bukkit.getPluginManager().getPlugin("MultiverseCore");
		initialized = true;
	}

	public static void createWorld(String worldName, World.Environment environment, String seed, WorldType worldType, boolean generateStructures, String customGenerator) {
		mvcore.getMVWorldManager().addWorld(worldName, environment, seed, worldType, generateStructures, customGenerator);
	}
	
	public static void deleteWorld(String worldName) {
		// Unload process handled automatically by method invoking
		mvcore.getMVWorldManager().deleteWorld(worldName);
	}
	
	public static MultiverseCore getAPIInstance() {
		return mvcore;
	}
}
