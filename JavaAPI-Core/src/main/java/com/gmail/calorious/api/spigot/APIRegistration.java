package com.gmail.calorious.api.spigot;

import java.util.Map;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.calorious.api.spigot.util.APIClassWrapper;

public class APIRegistration {
    private static TreeMap<String, APIClassWrapper> classes = new TreeMap<>();
    public static final double VERSION = Double.parseDouble(JavaAPI_Base.getInstance().getDescription().getVersion());

    public static void registerNewPluginDependency(String pluginFriendlyName, Class<? extends JavaPlugin> mainclass, boolean customVersion, double apiVersion) {
    	if(!customVersion) {
    		apiVersion = VERSION;
    	}
	if(mainclass.getCanonicalName().equalsIgnoreCase("com.gmail.calorious.JavaAPI_Base")) {
	    Bukkit.getLogger().warning(pluginFriendlyName + " tried to register a class name similar to JavaAPI.");
	    return;
	}
	for(String s : classes.keySet()) {
	    if(pluginFriendlyName.equals(s)) {
		Bukkit.getLogger().warning(pluginFriendlyName + " is already registered by another main class.");
		return;
	    }
	}
	for(APIClassWrapper wrapper : classes.values()) {
	    if(wrapper.getMainClass().getCanonicalName().equalsIgnoreCase(mainclass.getCanonicalName())) {
		Bukkit.getLogger().warning(pluginFriendlyName
			+ " tried to register a class that is already registered by another API-Hook.");
		return;
	    }
	}

	classes.put(pluginFriendlyName, new APIClassWrapper(mainclass, apiVersion));
	return;
    }

    protected static void printAPIRegisters(CommandSender sender) {
	if(classes.isEmpty()) {
	    sender.sendMessage("JavaAPI has no hooks.");
	    return;
	}
	for(Map.Entry<String, APIClassWrapper> entry : classes.entrySet()) {
	    sender.sendMessage("JavaAPI is hooked into the following plugins: ");
	    sender.sendMessage("   =-= " + entry.getKey() + " : " + entry.getValue().getMainClass().getCanonicalName()
		    + " - using API version " + entry.getValue().getAPIVersion());
	}
    }

}
