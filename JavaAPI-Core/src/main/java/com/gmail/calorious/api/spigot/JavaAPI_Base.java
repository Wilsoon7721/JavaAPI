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
    	MsgUtils.sendConsoleMessage("&b   ___________    _________     _________ ");
    	MsgUtils.sendConsoleMessage("&b  |  _______  |  |   ____  |   |___   ___|");
    	MsgUtils.sendConsoleMessage("&b  | |	   | |  |  |____| |       | |");
    	MsgUtils.sendConsoleMessage("&b  | |_______| |  |  _______|       | |         &2JavaAPI v" + getDescription().getVersion());
    	MsgUtils.sendConsoleMessage("&b  |  _______  |  | |               | |           &3" + message);
    	MsgUtils.sendConsoleMessage("&b  | |       | |  | |               | |");
    	MsgUtils.sendConsoleMessage("&b  | |	   | |  | |           ____| |_____");
    	MsgUtils.sendConsoleMessage("&b  | |	   | |  |_|       	 |____________|");
    }

    public static JavaAPI_Base getInstance() {
    	return instance;
    }
}
