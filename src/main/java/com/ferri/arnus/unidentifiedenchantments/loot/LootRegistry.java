package com.ferri.arnus.unidentifiedenchantments.loot;

import com.ferri.arnus.unidentifiedenchantments.UnidentifiedEnchantments;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LootRegistry {
	
	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, UnidentifiedEnchantments.MODID);
	
	public static void register() {
		GLM.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	public static final RegistryObject<Codec<HiddenLootModifier>> HIDDENLOOT = GLM.register("hiddenloot", HiddenLootModifier.CODEC);
	public static final RegistryObject<Codec<CurseLootModifier>> CURSELOOT = GLM.register("curseloot", CurseLootModifier.CODEC);

}
