package com.ferri.arnus.unidentifiedenchantments.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class UnidentifiedEnchantmentsConfig {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static ForgeConfigSpec SPEC;
	public static ConfigValue<Integer> MAXLEVEL;
	
	static {
		MAXLEVEL = BUILDER.comment("The needed amount of levels for the scroll of identification to activate").define("Max level", 5);
		SPEC = BUILDER.build();
	}
	
}
