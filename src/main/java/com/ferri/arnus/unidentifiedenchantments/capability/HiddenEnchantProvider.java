package com.ferri.arnus.unidentifiedenchantments.capability;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class HiddenEnchantProvider implements ICapabilityProvider{
	
	private HiddenEnchantments hidden ;
	private LazyOptional<HiddenEnchantments> lazy = LazyOptional.of(() -> hidden);
	
	public static final Capability<IHiddenEnchantments> ENCHANTMENTS = CapabilityManager.get(new CapabilityToken<>(){});
	
	public HiddenEnchantProvider(ItemStack stack) {
		hidden = new HiddenEnchantments(stack);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == ENCHANTMENTS) {
			return lazy.cast();
		}
		return LazyOptional.empty();
	}
	
	@SubscribeEvent
	public static void register(RegisterCapabilitiesEvent event){
		event.register(HiddenEnchantments.class);
	}

}
