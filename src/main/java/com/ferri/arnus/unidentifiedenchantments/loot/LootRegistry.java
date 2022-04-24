package com.ferri.arnus.unidentifiedenchantments.loot;

import com.ferri.arnus.unidentifiedenchantments.UnidentifiedEnchantments;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LootRegistry {
	
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, UnidentifiedEnchantments.MODID);
	
	public static void register() {
		GLM.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	public static final RegistryObject<HiddenLootModifier.Serializer> HIDDENLOOT = GLM.register("hiddenloot", HiddenLootModifier.Serializer::new);
	public static final RegistryObject<CurseLootModifier.Serializer> CURSELOOT = GLM.register("curseloot", CurseLootModifier.Serializer::new);
	public static final RegistryObject<HiddenLootModifier.Serializer> compat = GLM.register("hiddenenchantingoverhauledloot", HiddenLootModifier.Serializer::new);

}
