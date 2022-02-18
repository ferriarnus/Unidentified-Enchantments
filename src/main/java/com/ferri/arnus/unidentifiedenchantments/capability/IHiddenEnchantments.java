package com.ferri.arnus.unidentifiedenchantments.capability;

import java.util.Map;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.util.INBTSerializable;

public interface IHiddenEnchantments extends INBTSerializable<CompoundTag>{
	
	public Map<Enchantment,String> getHiddenMap();
	
	public void add(Enchantment enchantment);
}
