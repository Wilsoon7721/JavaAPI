package com.gmail.calorious.api.spigot.plugins.coreprotect;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.calorious.api.spigot.JavaAPI_Base;
import com.gmail.calorious.api.spigot.util.MsgUtils;

import net.coreprotect.CoreProtect;

public class CPIntegration {
	public static boolean initialized = false;
	private static CoreProtect cp = null;
	public static void initialize() {
		cp = (CoreProtect) Bukkit.getPluginManager().getPlugin("CoreProtect");
		initialized = true;
	}
	
	public static void rollback(Location rollbackLocation, int radius, double timetoRollbackInMinutes) {
		if(!initialized) {
			MsgUtils.sendConsoleMessage("&cAttempted to call CoreProtect integration method - Integration not intialized.");
			return;
		}
		int seconds = (int) Math.round(timetoRollbackInMinutes * 60);
		new BukkitRunnable() {
			@Override
			public void run() {
				cp.getAPI().performRollback(seconds, null, null, null, null, null, radius, rollbackLocation);
			}
		}.runTaskAsynchronously(JavaAPI_Base.getInstance());
		
	}
	
	public static void rollback(Location rollbackLocation, int radius, Set<Player> players, double timetoRollbackInMinutes) {
		if(!initialized) {
			MsgUtils.sendConsoleMessage("&cAttempted to call CoreProtect integration method - Integration not intialized.");
			return;
		}
		int seconds = (int) Math.round(timetoRollbackInMinutes * 60);
		List<String> playerNames = new ArrayList<>();
		for(Player player : players) {
			playerNames.add(player.getName());
		}
		new BukkitRunnable() {
			public void run() {
				cp.getAPI().performRollback(seconds, playerNames, null, null, null, null, radius, rollbackLocation);
			}
		}.runTaskAsynchronously(JavaAPI_Base.getInstance());
	}
	
	public static void restore(Location rollbackLocation, int radius, double timetoRollbackInMinutes) {
		if(!initialized) {
			MsgUtils.sendConsoleMessage("&cAttempted to call CoreProtect integration method - Integration not intialized.");
			return;
		}
		int seconds = (int) Math.round(timetoRollbackInMinutes * 60);
		new BukkitRunnable() {
			public void run() {
				cp.getAPI().performRestore(seconds, null, null, null, null, null, radius, rollbackLocation);
			}
		}.runTaskAsynchronously(JavaAPI_Base.getInstance());
		
	}
	
	public static void restore(Location rollbackLocation, int radius, Set<Player> players, double timetoRollbackInMinutes) {
		if(!initialized) {
			MsgUtils.sendConsoleMessage("&cAttempted to call CoreProtect integration method - Integration not intialized.");
			return;
		}
		int seconds = (int) Math.round(timetoRollbackInMinutes * 60);
		List<String> playerNames = new ArrayList<>();
		for(Player player : players) {
			playerNames.add(player.getName());
		}
		new BukkitRunnable() {
			public void run() {
				cp.getAPI().performRestore(seconds, playerNames, null, null, null, null, radius, rollbackLocation);
			}
		}.runTaskAsynchronously(JavaAPI_Base.getInstance());
		
	}
	
	public static void blockLookup(Block block, double time) {
		if(!initialized) {
			MsgUtils.sendConsoleMessage("&cAttempted to call CoreProtect integration method - Integration not intialized.");
			return;
		}
		int seconds = (int) Math.round(time * 60);
		new BukkitRunnable() {
			public void run() {
				cp.getAPI().blockLookup(block, seconds);
			}
		}.runTaskAsynchronously(JavaAPI_Base.getInstance());
		
	}
	
	public static void lookup(Location rollbackLocation, int radius, double timetoRollbackInMinutes) {
		if(!initialized) {
			MsgUtils.sendConsoleMessage("&cAttempted to call CoreProtect integration method - Integration not intialized.");
			return;
		}
		int seconds = (int) Math.round(timetoRollbackInMinutes * 60);
		new BukkitRunnable() {
			public void run() {
				cp.getAPI().performLookup(seconds, null, null, null, null, null, radius, rollbackLocation);
			}
		}.runTaskAsynchronously(JavaAPI_Base.getInstance());
		
	}
	
	public static void lookup(Location rollbackLocation, int radius, Set<Player> players, double timetoRollbackInMinutes) {
		if(!initialized) {
			MsgUtils.sendConsoleMessage("&cAttempted to call CoreProtect integration method - Integration not intialized.");
			return;
		}
		int seconds = (int) Math.round(timetoRollbackInMinutes * 60);
		List<String> playerNames = new ArrayList<>();
		for(Player player : players) {
			playerNames.add(player.getName());
		}
		new BukkitRunnable() {
			public void run() {
				cp.getAPI().performLookup(seconds, playerNames, null, null, null, null, radius, rollbackLocation);	
			}
		}.runTaskAsynchronously(JavaAPI_Base.getInstance());

	}
	
	public static CoreProtect getAPIInstance() {
		return cp;
	}
}
