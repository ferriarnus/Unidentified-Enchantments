package com.ferri.arnus.unidentifiedenchantments.item;

import com.ferri.arnus.unidentifiedenchantments.UnidentifiedEnchantments;

import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, UnidentifiedEnchantments.MODID);
	
	public static void register() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	public static final RegistryObject<ScrollOfIdentification> SCROLLOFIDENTIFICATION = ITEMS.register("scrollofidentification", ScrollOfIdentification::new);

}
