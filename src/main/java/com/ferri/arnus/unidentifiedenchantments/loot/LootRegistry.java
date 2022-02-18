package com.ferri.arnus.unidentifiedenchantments.loot;

import com.ferri.arnus.unidentifiedenchantments.UnidentifiedEnchantments;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LootRegistry {
	
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, UnidentifiedEnchantments.MODID);
	
	public static void register() {
		GLM.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	public static final RegistryObject<HiddenLootModifier.Serializer> BLUEPRINTS = GLM.register("hiddenloot", HiddenLootModifier.Serializer::new);
	
}
