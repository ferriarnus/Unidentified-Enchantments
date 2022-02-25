package com.ferri.arnus.unidentifiedenchantments.capability;

import java.util.Map;

import net.minecraft.world.item.enchantment.Enchantment;

public interface IHiddenEnchantments {
	
	public Map<Enchantment,String> getHiddenMap();
	
	public void setHiddenMap(Map<Enchantment,String> hiddenmap);
	
	public void add(Enchantment enchantment);
}
