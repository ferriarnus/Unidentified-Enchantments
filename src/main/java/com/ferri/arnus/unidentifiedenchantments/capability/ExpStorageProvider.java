package com.ferri.arnus.unidentifiedenchantments.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ExpStorageProvider implements ICapabilitySerializable<CompoundTag>{
	private ExpStorage storage = new ExpStorage();
	private LazyOptional<ExpStorage> lazy = LazyOptional.of(() -> storage);
	
	public static final Capability<ExpStorage> EXP_STORAGE = CapabilityManager.get(new CapabilityToken<>(){});


	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == EXP_STORAGE) {
			return lazy.cast();
		}
		return LazyOptional.empty();
	}

	@Override
	public CompoundTag serializeNBT() {
		return storage.serializeNBT();
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		storage.deserializeNBT(nbt);
	}
	
	@SubscribeEvent
	public static void register(RegisterCapabilitiesEvent event){
		event.register(ExpStorage.class);
	}

}
