package com.gmail.calorious.api.javacord.util;

import org.javacord.api.entity.server.BoostLevel;

public class ServerBoost {
	private int boostCount;
	private BoostLevel boostLevel;
	public ServerBoost(int boostCount, BoostLevel boostLevel) {
		this.boostLevel = boostLevel;
		this.boostCount = boostCount;
	}
	
	public int getBoostCount() {
		return boostCount;
	}
	
	public BoostLevel getBoostLevel() {
		return boostLevel;
	}
}
