package com.gmail.calorious.api.spigot.util;

import org.bukkit.enchantments.Enchantment;

public class EnchantmentWrapper {
    Enchantment enchantment;
    int level;
    boolean ignoreLevelRestriction = false;

    public EnchantmentWrapper(Enchantment ench, int level) {
	this.enchantment = ench;
	this.level = level;
	if(level > ench.getMaxLevel())
	    ignoreLevelRestriction = true;
    }

    public int getLevel() {
	return level;
    }

    public Enchantment getEnchantment() {
	return enchantment;
    }

    public boolean getIgnoreLevel() {
	return ignoreLevelRestriction;
    }
}
