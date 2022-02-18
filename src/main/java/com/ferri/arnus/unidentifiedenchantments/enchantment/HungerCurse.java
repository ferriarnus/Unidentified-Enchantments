package com.ferri.arnus.unidentifiedenchantments.enchantment;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class HungerCurse extends Enchantment{

	public HungerCurse(Rarity p_44676_, EnchantmentCategory p_44677_, EquipmentSlot[] p_44678_) {
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
	
	@Override
	public void doPostAttack(LivingEntity p_44686_, Entity p_44687_, int p_44688_) {
		if (p_44686_ instanceof Player player) {
			player.causeFoodExhaustion(0.4F);
		}
		super.doPostAttack(p_44686_, p_44687_, p_44688_);
	}
	
	@Override
	public void doPostHurt(LivingEntity p_44692_, Entity p_44693_, int p_44694_) {
		if (p_44692_ instanceof Player player) {
			player.causeFoodExhaustion(0.4F);
		}
		super.doPostHurt(p_44692_, p_44693_, p_44694_);
	}
}
