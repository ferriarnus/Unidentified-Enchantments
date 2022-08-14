package com.ferri.arnus.unidentifiedenchantments.item;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ferri.arnus.unidentifiedenchantments.capability.ExpStorage;
import com.ferri.arnus.unidentifiedenchantments.capability.ExpStorageProvider;
import com.ferri.arnus.unidentifiedenchantments.capability.HiddenEnchantProvider;
import com.ferri.arnus.unidentifiedenchantments.capability.IHiddenEnchantments;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;

import net.minecraft.world.item.Item.Properties;

public class ScrollOfIdentification extends Item{

	public ScrollOfIdentification() {
		super(new Properties().stacksTo(1).tab(CreativeModeTab.TAB_COMBAT));
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
		ItemStack stack = p_41433_.getItemInHand(p_41434_);
		LazyOptional<ExpStorage> capability = stack.getCapability(ExpStorageProvider.EXP_STORAGE);
		if (capability.isPresent()) {
			if (!capability.resolve().get().isFilled()) {
				if (p_41433_.experienceLevel > 0) {
					p_41433_.giveExperienceLevels(-1);
					capability.resolve().get().addLevel(1);
				}
				return InteractionResultHolder.consume(stack);
			}
		}
		return super.use(p_41432_, p_41433_, p_41434_);
	}
	
	@Override
	public InteractionResult useOn(UseOnContext p_41427_) {
		ItemStack stack = p_41427_.getItemInHand();
		LazyOptional<ExpStorage> capability = stack.getCapability(ExpStorageProvider.EXP_STORAGE);
		if (capability.isPresent()) {
			if (!capability.resolve().get().isFilled()) {
				if (p_41427_.getPlayer().experienceLevel > 0) {
					p_41427_.getPlayer().giveExperienceLevels(-1);
					capability.resolve().get().addLevel(1);
				}
				return InteractionResult.CONSUME;
			}
		}
		return super.useOn(p_41427_);
	}
	
	@Override
	public boolean overrideStackedOnOther(ItemStack p_150888_, Slot p_150889_, ClickAction p_150890_,
			Player p_150891_) {
		LazyOptional<IHiddenEnchantments> capability = p_150889_.getItem().getCapability(HiddenEnchantProvider.ENCHANTMENTS);
		if (capability.isPresent() && !capability.resolve().get().getHiddenMap().isEmpty() && this.isFoil(p_150888_)) {
			Map<Enchantment, String> hiddenMap = capability.resolve().get().getHiddenMap();
			p_150888_.setCount(0);
			Object[] array = hiddenMap.keySet().toArray();
			hiddenMap.remove(array[new Random().nextInt(array.length)]);
			capability.resolve().get().setHiddenMap(hiddenMap);
			return true;
		}
		return super.overrideStackedOnOther(p_150888_, p_150889_, p_150890_, p_150891_);
	}
	
	@Override
	public boolean isFoil(ItemStack p_41453_) {
		LazyOptional<ExpStorage> capability = p_41453_.getCapability(ExpStorageProvider.EXP_STORAGE);
		if (capability.isPresent()) {
			if (capability.resolve().get().isFilled()) {
				return true;
			}
		}
		return super.isFoil(p_41453_);
	}
	
	@Override
	public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents,
			TooltipFlag pIsAdvanced) {
		int level = 0;
		int max = 0;
		LazyOptional<ExpStorage> capability = pStack.getCapability(ExpStorageProvider.EXP_STORAGE);
		if (capability.isPresent()) {
			ExpStorage expStorage = capability.resolve().get();
			level = expStorage.getLevel();
			max = expStorage.getMaxLevel();
		}
		if (pStack.hasFoil()) {
			pTooltipComponents.add(Component.m_237115_("item.unidentifiedenchantments.scrollofidentification.desc").withStyle(ChatFormatting.GRAY));
		} else {
			pTooltipComponents.add(Component.m_237110_("item.unidentifiedenchantments.scrollofidentification.level", level , max).withStyle(ChatFormatting.GRAY));
		}
	}

}
