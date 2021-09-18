package com.gmail.calorious.api.objects;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class TimedLyrics implements Runnable {
	private String text = "";
	private long delay = 20L; // IN TICKS
	private ChatColor color = null;
	private PacketPlayOutTitle length = null;
	private Plugin plugin = null;
	private Player[] players = null;
	private LyricInfo lyricInfo = null;
	
	public TimedLyrics(Plugin plugin) { 
		this.plugin = plugin;
	}

	@Override
	public void run() {
		PacketPlayOutTitle notitle = new PacketPlayOutTitle(EnumTitleAction.TITLE,
			    ChatSerializer.a("{\"text\":\"  \",\"bold\":true,\"color\":\"white\"}"));
		PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE,
			    ChatSerializer.a("{\"text\":\"" + text + "\",\"bold\":true,\"color\":\"" + color.name().toLowerCase() + "\"}"));
		for(Player player : players) {
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(notitle);
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitle);
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
		}
	}
	
	public LyricInfo getLyricInfo() {
		if(lyricInfo != null) return lyricInfo.updateTimedLyrics(this); // If lyricInfo is not null, return the new LyricInfo object returned by updating TimedLyrics.
		return new LyricInfo(this);
	}
	
	public TimedLyrics setPlayers(Player[] players) {
		this.players = players;
		return this;
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public TimedLyrics setText(String text) {
		this.text = text;
		return this;
	}
	
	public TimedLyrics setDelay(double delayInSeconds) { // Converts from double delayInSeconds to default delay (in ticks)
		 DecimalFormat format = new DecimalFormat("0.#");
		 
		 // Automatically changed to ticks and set.
		 this.delay = Long.valueOf(String.valueOf(format.format(delayInSeconds * 20)));
		 return this;
	}
	
	public void playLyrics() {
		if(players == null) throw new RuntimeException("Missing players for TimedLyrics object!");
		if(text.equalsIgnoreCase("") || text.isEmpty()) throw new RuntimeException("Missing text for TimedLyrics object!");
		if(color == null) throw new RuntimeException("Missing color for TimedLyrics object!");
		if(length == null) throw new RuntimeException("Missing length for TimedLyrics object!");
		if(plugin == null) throw new RuntimeException("Missing plugin for TimedLyrics object!");
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, this, delay);
	}
	
	public TimedLyrics setLength(int fadeIn, int onScreen, int fadeOut) { // auto convert onScreen to ticks
		length = new PacketPlayOutTitle(fadeIn, onScreen * 20, fadeOut);
		return this;
	}
	
	public TimedLyrics setColor(ChatColor color) {
		this.color = color;
		return this;
	}
	
	public ChatColor getColor() {
		return color;
	}
	
	public double getDelay() { // Without parameters, return delay in ticks.
		return delay;
	}
	
	public double getDelay(TimeUnit unit) {
		double seconds = (delay / 20); // Divide by 20 to convert from ticks to seconds.
		if(unit == TimeUnit.SECONDS) {
			return seconds; 
		}
		if(unit == TimeUnit.MINUTES) {
			return (seconds / 60);
		}
		if(unit == TimeUnit.HOURS) {
			return (seconds / 3600);
		}
		if(unit == TimeUnit.DAYS) {
			return (seconds / 86400);
		}
		if(unit == TimeUnit.MILLISECONDS) {
			return (seconds * 1000);
		}
		if(unit == TimeUnit.MICROSECONDS) {
			return (seconds * 1000000);
		}
		if(unit == TimeUnit.NANOSECONDS) {
			return (seconds * 1000000000);
		}
		return seconds; // Chances are this won't be called, but if it does, return seconds by default instead of ticks.
	}
	
	public String getText() {
		return text;
	}
}
