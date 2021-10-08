package com.gmail.calorious.api.spigot.objects;

import org.bukkit.Material;

public enum MusicDisc {
	RECORD_13(2256, Material.GOLD_RECORD), CAT(2557, Material.GREEN_RECORD), BLOCKS(2258, Material.RECORD_3), CHIRP(2259, Material.RECORD_4), FAR(2260, Material.RECORD_5), MALL(2261, Material.RECORD_6), MELLOHI(2262, Material.RECORD_7), STAL(2263, Material.RECORD_8), STRAD(2264, Material.RECORD_9), WARD(2265, Material.RECORD_10), RECORD_11(2266, Material.RECORD_11), WAIT(2267, Material.RECORD_12);
	
	
	private int itemId;
	private Material bukkitMaterial;
	MusicDisc(int itemId, Material bukkitMaterial) {
		this.itemId = itemId;
		this.bukkitMaterial = bukkitMaterial;
	}
	
	public Material getMaterial() {
		return bukkitMaterial;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public static MusicDisc fromMaterial(Material material) {
		for(MusicDisc disc : MusicDisc.values()) {
			if(disc.getMaterial() == material) {
				return disc;
			}
			continue;
		}
		throw new IllegalArgumentException("Corrupt material inserted.");
	}
	
	public static MusicDisc fromItemId(int itemId) {
		for(MusicDisc disc : MusicDisc.values()) {
			if(disc.getItemId() == itemId) {
				return disc;
			}
			continue;
		}
		throw new IllegalArgumentException("Corrupt material inserted.");
	}
}