package com.gmail.calorious.api.builder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import com.gmail.calorious.api.util.EnchantmentWrapper;

public class EnchantmentBookBuilder extends MetaBuilder {
    private ItemStack item;
    private EnchantmentStorageMeta meta;

    public EnchantmentBookBuilder(ItemStack item, ItemBuilder builder) {
	super(item, builder);
	this.item = item;
	if(!checkValidItem())
	    throw new ClassCastException("MetaBuilder's item cannot be cast to EnchantmentBookBulder.");
	meta = (EnchantmentStorageMeta) item.getItemMeta();
    }

    @Override
    public EnchantmentBookBuilder withEnchant(EnchantmentWrapper wrapper) {
	meta.addStoredEnchant(wrapper.getEnchantment(), wrapper.getLevel(), wrapper.getIgnoreLevel());
	return this;
    }

    @Override
    public ItemStack buildItemStack() {
	item.setItemMeta(meta);
	return item;
    }

    @Override
    public EnchantmentStorageMeta build() {
	return meta;
    }

    private boolean checkValidItem() {
	if(item.getType() == Material.ENCHANTED_BOOK)
	    return true;
	return false;
    }
}
