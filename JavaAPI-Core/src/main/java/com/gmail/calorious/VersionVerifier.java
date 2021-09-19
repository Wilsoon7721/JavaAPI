package com.gmail.calorious;

import org.bukkit.Bukkit;

import com.gmail.calorious.api.spigot.util.MsgUtils;

public class VersionVerifier {
  // Some plugins have access to >1.9 features
	// restrict with this
	private VersionVerifier() {} // Cannot instantiate
	
	public static String getServerBuildAndVersion() {
		// Bukkit Version returns: git-(Build) (MC: (Version))
		return Bukkit.getVersion();
	}
	
	public static String getServerVersion() {
		// Returns Version only
		return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	}
	
	public static boolean isServerAboveVersion(String version) {
		version = versionConverter(version);
		String serverVersion = versionConverter(getServerVersion());
		Version eServerVersion = null;
		Version eInputtedVersion = null;
		try {
			eServerVersion = Version.valueOf(serverVersion);
		} catch(IllegalArgumentException ex) {
			MsgUtils.sendConsoleMessage("&cError: Could not check if server is above version - Server Version could not be found in Version enum.");
			MsgUtils.sendConsoleMessage("&c - Attempted to look for: &c" + serverVersion);
		}
		try {
			eInputtedVersion = Version.valueOf(version);
		} catch(IllegalArgumentException ex) {
			MsgUtils.sendConsoleMessage("&cError: Could not check if server is above version - Inputted version could not be found in Version enum.");
			MsgUtils.sendConsoleMessage("&c - Attempted to look for: &c" + version);
		}
		return eInputtedVersion.getWeightage() > eServerVersion.getWeightage();
	}
	
	private static String versionConverter(String version) {
		String replacedVersion = version.replace('.', '_');
		StringBuffer buffer = new StringBuffer(replacedVersion);
		buffer.insert(0, "MC");
		// Should print out as MC(VERSION) [MC1_8_8]
		return buffer.toString();
	}
}
