package com.gmail.calorious.api.builder;

import java.util.List;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gmail.calorious.api.spigot.util.EnchantmentWrapper;
import com.gmail.calorious.api.spigot.util.MsgUtils;

public class MetaBuilder {
    private ItemMeta meta;
    private ItemStack item;
    private ItemBuilder builder;

    public MetaBuilder(ItemStack item, ItemBuilder builder) {
	this.meta = item.getItemMeta();
	this.item = item;
	this.builder = builder;
    }

    public MetaBuilder withName(String name) {
	meta.setDisplayName(MsgUtils.color(name));
	return this;
    }

    public MetaBuilder withLore(List<String> lore) {
	meta.setLore(lore);
	return this;
    }

    public MetaBuilder withEnchant(EnchantmentWrapper enchWrapper) {
	meta.addEnchant(enchWrapper.getEnchantment(), enchWrapper.getLevel(), enchWrapper.getIgnoreLevel());
	return this;
    }

    public MetaBuilder withItemFlag(ItemFlag itemFlag) {
	meta.addItemFlags(itemFlag);
	return this;
    }

    public MetaBuilder isUnbreakable(boolean unbreakable) {
	meta.spigot().setUnbreakable(unbreakable);
	return this;
    }

    public ItemMeta build() {
	return meta;
    }

    public ItemBuilder toItemBuilder() {
	item.setItemMeta(meta);
	return builder;
    }

    public ItemStack buildItemStack() {
	item.setItemMeta(meta);
	return item;
    }
}
