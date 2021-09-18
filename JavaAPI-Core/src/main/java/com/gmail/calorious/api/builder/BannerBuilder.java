package com.gmail.calorious.api.builder;

import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.List;

public class BannerBuilder extends MetaBuilder {
    private ItemStack item;
    private BannerMeta meta;

    public BannerBuilder(ItemStack item, ItemBuilder builder) {
	super(item, builder);
	this.item = item;
	if(!checkValidItem())
	    throw new ClassCastException("MetaBuilder's item cannot be cast to BannerBuilder.");
	meta = (BannerMeta) item.getItemMeta();
    }

    public BannerBuilder withPattern(Pattern pattern) {
	meta.addPattern(pattern);
	return this;
    }

    public BannerBuilder setPattern(int index, Pattern pattern) {
	meta.setPattern(index, pattern);
	return this;
    }

    @Override
    public BannerMeta build() {
	return meta;
    }

    @Override
    public ItemStack buildItemStack() {
	item.setItemMeta(meta);
	return item;
    }

    public BannerBuilder withPattern(List<Pattern> patterns) {
	meta.setPatterns(patterns);
	return this;
    }

    private boolean checkValidItem() {
	if(item.getType() == Material.BANNER || item.getType() == Material.STANDING_BANNER
		|| item.getType() == Material.WALL_BANNER)
	    return true;
	return false;
    }

}
