package com.gmail.calorious.api.spigot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.calorious.api.spigot.util.MsgUtils;

public class JavaAPI_Base extends JavaPlugin {
    private static JavaAPI_Base instance;

    @Override
    public void onEnable() {
    	printIcon("Initializing...");
    	MsgUtils.sendConsoleMessage(" ");
    	MsgUtils.sendConsoleMessage("&c&lPlease note that all methods ran by JavaAPI Hooks are ran with a plugin instance belonging to JavaAPI.");
    	getCommand("javaapi").setExecutor(new JavaAPICommand(this));
    	instance = this;
    	new BukkitRunnable() {
    		@Override
    		public void run() {
    			APIRegistration.printAPIRegisters(Bukkit.getConsoleSender());
    		}
    	}.runTask(this);
    }
    
    
    private void printIcon(String message) {
    	MsgUtils.sendConsoleMessage("&b&l    ___________     _________        _________     ");
    	MsgUtils.sendConsoleMessage("&b&l   |  _______  |   |   ____  |      |___   ___|    ");
    	MsgUtils.sendConsoleMessage("&b&l   | |	      | |   |  |____| |          | |        ");
    	MsgUtils.sendConsoleMessage("&b&l   | |_______| |   |  _______|          | |        		JavaAPI v" + getDescription().getVersion());
    	MsgUtils.sendConsoleMessage("&b&l   |  _______  |   | |		             | |        		  " + message);
    	MsgUtils.sendConsoleMessage("&b&l   | |       | |   | |                  | |        ");
    	MsgUtils.sendConsoleMessage("&b&l   | |	      | |   | |             _____| |_____   ");
    	MsgUtils.sendConsoleMessage("&b&l   | |		  |	|   |_|       	   |_____________|  ");
    }

    public static JavaAPI_Base getInstance() {
    	return instance;
    }
}
