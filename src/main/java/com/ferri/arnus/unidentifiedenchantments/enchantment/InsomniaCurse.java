package com.ferri.arnus.unidentifiedenchantments.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import net.minecraft.world.item.enchantment.Enchantment.Rarity;

public class InsomniaCurse extends Enchantment{

	public InsomniaCurse(Rarity p_44676_, EnchantmentCategory p_44677_, EquipmentSlot[] p_44678_) {
		super(p_44676_, p_44677_, p_44678_);
	}
	
	@Override
	public boolean isCurse() {
		return true;
	}
	
	@Override
	public boolean isTradeable() {
		return false;
	}

}
