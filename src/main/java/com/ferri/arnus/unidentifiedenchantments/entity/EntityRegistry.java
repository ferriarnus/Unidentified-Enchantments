package com.ferri.arnus.unidentifiedenchantments.entity;

import com.ferri.arnus.unidentifiedenchantments.UnidentifiedEnchantments;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistry {

	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, UnidentifiedEnchantments.MODID);
	
	public static void register() {
		ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	public static RegistryObject<EntityType<FakeCreeper>> FAKECREEPER = ENTITIES.register("fakecreeper", () -> EntityType.Builder.of(FakeCreeper::new, MobCategory.MONSTER).sized(0.6F, 1.7F).clientTrackingRange(8).build("fakecreeper"));
}
