package com.ferri.arnus.unidentifiedenchantments.loot;

import com.ferri.arnus.unidentifiedenchantments.enchantment.EnchantmentRegistry;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class CurseLootModifier extends LootModifier{

	public static final Supplier<Codec<CurseLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, CurseLootModifier::new)));

	protected CurseLootModifier(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Override
	protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		for (ItemStack stack : generatedLoot) {
			Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
			if(0.15 + 0.1*enchantments.size() > new Random().nextDouble() && (!EnchantmentHelper.getAvailableEnchantmentResults(1, stack, true).isEmpty() || stack.getItem() instanceof EnchantedBookItem)) {
				ArrayList<RegistryObject<? extends Enchantment>> list = (ArrayList<RegistryObject<? extends Enchantment>>) EnchantmentRegistry.CURSELIST.clone();
				if (stack.getItem() instanceof EnchantedBookItem) {
					Enchantment pEnchantment = list.get(new Random().nextInt(list.size())).get();
					EnchantedBookItem.addEnchantment(stack, new EnchantmentInstance(pEnchantment, new Random().nextInt(pEnchantment.getMaxLevel())));
				} else {
					Collections.shuffle(list);
					for (RegistryObject<? extends Enchantment> enchantment: list) {
						if (enchantment.get().canEnchant(stack)) {
							stack.enchant(enchantment.get(), new Random().nextInt(enchantment.get().getMaxLevel()));
							break;
						}
					}
				}
			}
		}
		return generatedLoot;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC.get();
	}

}
