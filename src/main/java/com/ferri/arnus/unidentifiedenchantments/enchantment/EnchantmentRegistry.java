package com.ferri.arnus.unidentifiedenchantments.enchantment;

import com.ferri.arnus.unidentifiedenchantments.UnidentifiedEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantment.Rarity;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

public class EnchantmentRegistry {

	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, UnidentifiedEnchantments.MODID);
	private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
	public static ArrayList<RegistryObject<? extends Enchantment>> CURSELIST = new ArrayList<>();
	
	public static void register() {
		ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	public static final RegistryObject<HungerCurse> HUNGERCURSE = ENCHANTMENTS.register("hungercurse", () -> new HungerCurse(Rarity.RARE, EnchantmentCategory.BREAKABLE, EquipmentSlot.values()));
	public static final RegistryObject<WeightCurse> WEIGHTCURSE = ENCHANTMENTS.register("weightcurse", () -> new WeightCurse(Rarity.RARE, EnchantmentCategory.ARMOR, ARMOR_SLOTS));
	public static final RegistryObject<InsomniaCurse> INSOMNIACURSE = ENCHANTMENTS.register("insomniacurse", () -> new InsomniaCurse(Rarity.RARE, EnchantmentCategory.ARMOR, ARMOR_SLOTS));
	public static final RegistryObject<VanityCurse> VANITYCURSE = ENCHANTMENTS.register("vanitycurse", () -> new VanityCurse(Rarity.RARE, EnchantmentCategory.BREAKABLE, EquipmentSlot.values()));
	public static final RegistryObject<MadnessCurse> MADNESSCURSE = ENCHANTMENTS.register("madnesscurse", () -> new MadnessCurse(Rarity.RARE, EnchantmentCategory.ARMOR, ARMOR_SLOTS));
	public static final RegistryObject<BrittleCurse> BRITTLECURSE = ENCHANTMENTS.register("brittlecurse", () -> new BrittleCurse(Rarity.RARE, EnchantmentCategory.BREAKABLE, EquipmentSlot.values()));

	static {
		CURSELIST.add(HUNGERCURSE);
		CURSELIST.add(WEIGHTCURSE);
		CURSELIST.add(INSOMNIACURSE);
		CURSELIST.add(VANITYCURSE);
		CURSELIST.add(MADNESSCURSE);
		CURSELIST.add(BRITTLECURSE);
	}
	
}
