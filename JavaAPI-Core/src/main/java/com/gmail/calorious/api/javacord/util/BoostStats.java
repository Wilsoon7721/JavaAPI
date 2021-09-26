package com.gmail.calorious.api.javacord.util;

import org.javacord.api.entity.server.BoostLevel;

public class BoostStats {
	private BoostLevel level;
	private int boostCount;
	public BoostStats(int boostCount, BoostLevel level) {
		this.boostCount = boostCount;
		this.level = level;
	}
	
	public int getBoostCount() {
		return boostCount;
	}
	
	public BoostLevel getLevel() {
		return level;
	}
}
