package com.ferri.arnus.unidentifiedenchantments;

import com.ferri.arnus.unidentifiedenchantments.enchantment.EnchantmentRegistry;
import com.ferri.arnus.unidentifiedenchantments.entity.EntityRegistry;
import com.ferri.arnus.unidentifiedenchantments.item.ItemRegistry;
import com.ferri.arnus.unidentifiedenchantments.loot.ChestCondition;
import com.ferri.arnus.unidentifiedenchantments.loot.LootRegistry;

import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(UnidentifiedEnchantments.MODID)
public class UnidentifiedEnchantments {
    public static final String MODID = "unidentifiedenchantments";

    public UnidentifiedEnchantments() {
        ItemRegistry.register();
        EntityRegistry.register();
        EnchantmentRegistry.register();
        LootRegistry.register();
        
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::renders);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerAttributes);
    }
    
	private void renders(FMLClientSetupEvent event) {
		EntityRenderers.register(EntityRegistry.FAKECREEPER.get(), CreeperRenderer::new);
	}
	
	private void registerAttributes(EntityAttributeCreationEvent event) {
    	event.put(EntityRegistry.FAKECREEPER.get(), Creeper.createAttributes().build());
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD, modid = MODID)
    public static class RegistryEvents {
    	@SubscribeEvent
        static void registerLootData(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
            Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(UnidentifiedEnchantments.MODID,"chest_condition"), ChestCondition.HIDDEN_CHEST);
        }
    }
}
