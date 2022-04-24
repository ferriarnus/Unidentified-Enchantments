package com.ferri.arnus.unidentifiedenchantments.compat.enchantingoverhauled;

import com.ferri.arnus.unidentifiedenchantments.UnidentifiedEnchantments;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EnchantingOverhauledLootRegistry {
	
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, UnidentifiedEnchantments.MODID);
	
	public static void register() {
		GLM.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	public static final RegistryObject<HiddenEnchantingOverhauledLootModifier.Serializer> HIDDENLOOT = GLM.register("hiddenenchantingoverhauledloot", HiddenEnchantingOverhauledLootModifier.Serializer::new);

}
