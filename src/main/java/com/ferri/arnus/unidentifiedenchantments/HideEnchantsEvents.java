package com.ferri.arnus.unidentifiedenchantments;

import com.ferri.arnus.unidentifiedenchantments.capability.ExpStorageProvider;
import com.ferri.arnus.unidentifiedenchantments.capability.HiddenEnchantProvider;
import com.ferri.arnus.unidentifiedenchantments.item.ItemRegistry;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class HideEnchantsEvents {
	public static final ResourceLocation ALT_FONT = new ResourceLocation("minecraft", "alt");
	public static final Style ROOT_STYLE = Style.EMPTY.withFont(ALT_FONT);
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	static void hidetoltip(ItemTooltipEvent event) {
		event.getItemStack().getCapability(HiddenEnchantProvider.ENCHANTMENTS).ifPresent(cap -> {
			for (Enchantment enchantment: cap.getHiddenMap().keySet()) {
				if (!EnchantmentHelper.getEnchantments(event.getItemStack()).containsKey(enchantment)) {
					continue;
				}
				Registry.ENCHANTMENT.getOptional(EnchantmentHelper.getEnchantmentId(enchantment)).ifPresent((p_41708_) -> {
					int index = event.getToolTip().indexOf(p_41708_.getFullname(EnchantmentHelper.getEnchantments(event.getItemStack()).get(enchantment)));
					event.getToolTip().set(index, new TextComponent(cap.getHiddenMap().get(enchantment)).withStyle(ROOT_STYLE).withStyle(ChatFormatting.GRAY).append(" ").append(new TranslatableComponent("enchantment.level." + EnchantmentHelper.getEnchantments(event.getItemStack()).get(enchantment))));
				});
			}
		});
	}
	
	@SubscribeEvent
	static void attachcaps(AttachCapabilitiesEvent<ItemStack> event) {
    	if (event.getObject().isEnchantable() || event.getObject().isEnchanted() || event.getObject().getItem() instanceof EnchantedBookItem) {
    		event.addCapability(new ResourceLocation(UnidentifiedEnchantments.MODID, "hiddenenchantments"), new HiddenEnchantProvider(event.getObject()));
    	}
    	if( event.getObject().is(ItemRegistry.SCROLLOFIDENTIFICATION.get())) {
    		event.addCapability(new ResourceLocation(UnidentifiedEnchantments.MODID, "exp_storage"), new ExpStorageProvider());
    	}
    }
}
