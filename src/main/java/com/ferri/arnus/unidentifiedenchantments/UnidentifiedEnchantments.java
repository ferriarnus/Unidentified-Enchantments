package com.ferri.arnus.unidentifiedenchantments;

import com.ferri.arnus.unidentifiedenchantments.command.CommandRegistry;
import com.ferri.arnus.unidentifiedenchantments.command.GlobalLootTableCommand;
import com.ferri.arnus.unidentifiedenchantments.config.UnidentifiedEnchantmentsConfig;
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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

@Mod(UnidentifiedEnchantments.MODID)
public class UnidentifiedEnchantments {
    public static final String MODID = "unidentifiedenchantments";

    public UnidentifiedEnchantments() {
        ItemRegistry.register();
        EntityRegistry.register();
        EnchantmentRegistry.register();
        LootRegistry.register();
        CommandRegistry.register();
        
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::renders);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerAttributes);
        MinecraftForge.EVENT_BUS.addListener(this::registerCommands);
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, UnidentifiedEnchantmentsConfig.SPEC);
        
//        if(FMLLoader.getLoadingModList().getModFileById("enchanting_overhauled") != null) {
//        	MinecraftForge.EVENT_BUS.register(HideEnchantingOverhauledEvents.class);
//        	EnchantingOverhauledLootRegistry.register();
//        }
    }
    
	private void renders(FMLClientSetupEvent event) {
		EntityRenderers.register(EntityRegistry.FAKECREEPER.get(), CreeperRenderer::new);
	}
	
	private void registerAttributes(EntityAttributeCreationEvent event) {
    	event.put(EntityRegistry.FAKECREEPER.get(), Creeper.createAttributes().build());
    }
	
	private void registerCommands(RegisterCommandsEvent event) {
		GlobalLootTableCommand.register(event.getDispatcher());
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD, modid = MODID)
    public static class RegistryEvents {
    	@SubscribeEvent
        static void registerLootData(RegisterEvent event) {
    		event.register(Registry.LOOT_ITEM_REGISTRY, new ResourceLocation(UnidentifiedEnchantments.MODID,"chest_condition"), () -> ChestCondition.HIDDEN_CHEST);
        }    	
    }
}
