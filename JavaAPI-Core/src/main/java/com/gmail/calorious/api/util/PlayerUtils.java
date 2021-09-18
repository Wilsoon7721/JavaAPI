package com.gmail.calorious.api.util;

import org.bukkit.entity.Player;

public class PlayerUtils {
    public static boolean isOnline(Player player) {
	return player.isOnline();
    }

    public static boolean isOpped(Player player) {
	return player.isOp();
    }

    public static boolean isAuthorized(Player player, String permission) {
	return player.hasPermission(permission);
    }
}
