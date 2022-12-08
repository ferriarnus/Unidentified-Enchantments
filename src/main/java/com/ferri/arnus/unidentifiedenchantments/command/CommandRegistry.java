package com.ferri.arnus.unidentifiedenchantments.command;

import com.ferri.arnus.unidentifiedenchantments.UnidentifiedEnchantments;
import com.ferri.arnus.unidentifiedenchantments.command.GlobalLootTableCommand.GlobalLootArgument;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CommandRegistry {

	private static final DeferredRegister<ArgumentTypeInfo<?, ?>> COMMAND_ARGUMENT_TYPES = DeferredRegister.create(ForgeRegistries.COMMAND_ARGUMENT_TYPES, UnidentifiedEnchantments.MODID);
	
	public static void register() {
		COMMAND_ARGUMENT_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	public static RegistryObject<SingletonArgumentInfo<GlobalLootArgument>> GLA = COMMAND_ARGUMENT_TYPES.register("globalloot", () -> ArgumentTypeInfos.registerByClass(GlobalLootArgument.class, SingletonArgumentInfo.contextFree(GlobalLootArgument::string)));
}
