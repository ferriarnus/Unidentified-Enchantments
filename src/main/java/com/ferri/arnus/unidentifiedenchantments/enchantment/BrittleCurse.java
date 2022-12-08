package com.ferri.arnus.unidentifiedenchantments.enchantment;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Map;

public class BrittleCurse extends Enchantment {

    protected BrittleCurse(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
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
    public void doPostHurt(LivingEntity pTarget, Entity pAttacker, int pLevel) {
        Map.Entry<EquipmentSlot, ItemStack> brittle = EnchantmentHelper.getRandomItemWith(EnchantmentRegistry.BRITTLECURSE.get(), pTarget);
        brittle.getValue().setDamageValue(brittle.getValue().getDamageValue() + pTarget.level.random.nextInt(3));
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        ItemStack brittle = pAttacker.getMainHandItem();
        brittle.setDamageValue(brittle.getDamageValue() + pTarget.level.random.nextInt(3));
    }
}
