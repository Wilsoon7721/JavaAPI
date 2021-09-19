package com.gmail.calorious.api.spigot.builder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class ItemBuilder {
    private ItemStack itemstack;

    public ItemBuilder(ItemStack item) {
	this.itemstack = item;
    }

    public ItemBuilder(Material material) {
	this.itemstack = new ItemStack(material);
    }

    public ItemBuilder(Material material, int amount) {
	this.itemstack = new ItemStack(material, amount);
    }

    public ItemBuilder withItem(ItemStack item) {
	this.itemstack = item;
	return this;
    }

    public ItemBuilder withAmount(int amount) {
	itemstack.setAmount(amount);
	return this;
    }

    public ItemBuilder withDurability(int durability) {
	itemstack.setDurability((short) durability);
	return this;
    }

    public MetaBuilder toMetaCreation() {
	return new MetaBuilder(itemstack, this);
    }

    public ItemBuilder withData(MaterialData data) {
	validateInit();
	itemstack.setData(data);
	return this;
    }

    void validateInit() {
	if(itemstack == null) {
	    throw new IllegalStateException("Item is not yet initiated (Missing material)");
	}
    }

    public ItemStack build() {
	return itemstack;
    }
}
