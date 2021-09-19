package com.gmail.calorious.api.builder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullBuilder extends MetaBuilder {
    private ItemStack item;
    private SkullMeta meta;

    public SkullBuilder(ItemStack item, ItemBuilder builder) {
	super(item, builder);
	this.item = item;
	if(!checkValidItem())
	    throw new ClassCastException("MetaBuilder's item cannot be cast to SkullBuilder.");
	meta = (SkullMeta) item.getItemMeta();
    }

    public SkullBuilder withOwner(String name) {
	meta.setOwner(name);
	return this;
    }

    @Override
    public ItemStack buildItemStack() {
	item.setItemMeta(meta);
	return item;
    }

    @Override
    public SkullMeta build() {
	return meta;
    }

    private boolean checkValidItem() {
	if(item.getType() == Material.SKULL_ITEM && item.getDurability() == 3)
	    return true;
	return false;
    }

}
