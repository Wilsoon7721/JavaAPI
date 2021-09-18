package com.gmail.calorious;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class JavaAPI_Base extends JavaPlugin {
    private static JavaAPI_Base instance;

    @Override
    public void onEnable() {
	Bukkit.getConsoleSender().sendMessage("&aJavaAPI has been initialized.");
	getCommand("javaapi").setExecutor(new JavaAPICommand(this));
	instance = this;
	new BukkitRunnable() {
	    @Override
	    public void run() {
		APIRegistration.printAPIRegisters(Bukkit.getConsoleSender());
	    }
	}.runTask(this);
    }

    public static JavaAPI_Base getInstance() {
	return instance;
    }
}
