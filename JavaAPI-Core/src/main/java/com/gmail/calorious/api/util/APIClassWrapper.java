package com.gmail.calorious.api.util;

import org.bukkit.plugin.java.JavaPlugin;

public class APIClassWrapper {
    private double APIVersion;
    private Class<? extends JavaPlugin> mainClass;

    public APIClassWrapper(Class<? extends JavaPlugin> mainClass, double APIVersion) {
	this.mainClass = mainClass;
	this.APIVersion = APIVersion;
    }

    public Class<? extends JavaPlugin> getMainClass() {
	return mainClass;
    }

    public double getAPIVersion() {
	return APIVersion;
    }
}
