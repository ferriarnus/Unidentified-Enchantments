package com.ferri.arnus.unidentifiedenchantments.compat.enchantingoverhauled;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.ferri.arnus.unidentifiedenchantments.UnidentifiedEnchantments;
import com.ferri.arnus.unidentifiedenchantments.capability.HiddenEnchantProvider;

import johnsmith.enchantingoverhauled.items.EnchantedTomeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HideEnchantingOverhauledEvents {
	
	private static final ResourceLocation ALT_FONT = new ResourceLocation("minecraft", "alt");
	private static final Style ROOT_STYLE = Style.EMPTY.withFont(ALT_FONT);

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	static void hidetoltip(ItemTooltipEvent event) {
		if (!(event.getItemStack().getItem() instanceof EnchantedTomeItem)) {
			return;
		}
		event.getItemStack().getCapability(HiddenEnchantProvider.ENCHANTMENTS).ifPresent(cap -> {
			for (Enchantment enchantment: cap.getHiddenMap().keySet()) {
				if (!getEnchantments(event.getItemStack()).containsKey(enchantment)) {
					continue;
				}
				Registry.ENCHANTMENT.getOptional(EnchantmentHelper.getEnchantmentId(enchantment)).ifPresent((p_41708_) -> {
					int index = event.getToolTip().indexOf(p_41708_.getFullname(getEnchantments(event.getItemStack()).get(enchantment)));
					event.getToolTip().set(index, new TextComponent(cap.getHiddenMap().get(enchantment)).withStyle(ROOT_STYLE).withStyle(ChatFormatting.GRAY).append(" ").append(new TranslatableComponent("enchantment.level." + getEnchantments(event.getItemStack()).get(enchantment))));
				});
			}
		});
	}
	
	@SubscribeEvent
	static void attachcaps(AttachCapabilitiesEvent<ItemStack> event) {
    	if (event.getObject().getItem() instanceof EnchantedTomeItem) {
    		event.addCapability(new ResourceLocation(UnidentifiedEnchantments.MODID, "hiddenenchantments"), new HiddenEnchantProvider());
    	}
    }
	
	public static Map<Enchantment, Integer> getEnchantments(ItemStack pStack) {
		Map<Enchantment, Integer> map = new HashMap<>();
		ListTag enchantments = EnchantedTomeItem.getEnchantments(pStack);
		for (int i = 0; i < enchantments.size(); i++) {
			Optional<Enchantment> optional = Registry.ENCHANTMENT.getOptional(new ResourceLocation(enchantments.getCompound(i).getString("id")));
			int lvl = enchantments.getCompound(i).getInt("lvl");
			optional.ifPresent(o -> map.put(o, lvl));
		}
		return map;
	}
}
