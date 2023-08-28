package com.ferri.arnus.unidentifiedenchantments.enchantment;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ClumsyCurse extends Enchantment {
    protected ClumsyCurse(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
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
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        if (pAttacker.level().random.nextFloat() < 0.05 + pLevel * 0.02) {
            ItemStack mainHandItem = pAttacker.getMainHandItem().copy();
            ItemEntity itemEntity = new ItemEntity(pAttacker.level(), pAttacker.position().x, pAttacker.position().y, pAttacker.position().z, mainHandItem);
            itemEntity.setPickUpDelay(35 + pLevel*5);
            if (pAttacker.level().addFreshEntity(itemEntity)) {
                pAttacker.getMainHandItem().setCount(0);
            }
        }
    }
}
