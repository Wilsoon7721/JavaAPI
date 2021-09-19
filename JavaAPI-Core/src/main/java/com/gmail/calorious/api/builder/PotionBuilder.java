package com.gmail.calorious.api.builder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionBuilder extends MetaBuilder {
    private ItemStack item;
    private PotionMeta meta;

    public PotionBuilder(ItemStack item, ItemBuilder builder) {
	super(item, builder);
	this.item = item;
	if(!checkValidItem())
	    throw new ClassCastException("MetaBuilder's item cannot be cast to PotionBuilder.");
	meta = (PotionMeta) item.getItemMeta();
    }

    public PotionBuilder withPotionEffect(PotionEffectType type, int seconds, int amplifier, boolean particles) {
	PotionEffect effect = new PotionEffect(type, seconds, amplifier, false, particles);
	meta.addCustomEffect(effect, true); // Auto overwrite is true
	return this;
    }

    public ItemStack withNoEffects() {
	// WARNING: IGNORES ALL OTHER POTIONBUILDER CHANGE
	// Returns water bottle
	return new ItemStack(Material.POTION);
    }

    @Override
    public ItemStack buildItemStack() {
	item.setItemMeta(meta);
	return item;
    }

    @Override
    public PotionMeta build() {
	return meta;
    }

    public PotionBuilder withPotionEffect(PotionEffect effect) {
	meta.addCustomEffect(effect, true);
	return this;
    }

    private boolean checkValidItem() {
	if(item.getType() == Material.POTION)
	    return true;
	return false;
    }

}
