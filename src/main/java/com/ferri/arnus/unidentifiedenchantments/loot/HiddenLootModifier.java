package com.ferri.arnus.unidentifiedenchantments.loot;

import com.ferri.arnus.unidentifiedenchantments.capability.HiddenEnchantProvider;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class HiddenLootModifier extends LootModifier{

	public static final Supplier<Codec<HiddenLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, HiddenLootModifier::new)));

	protected HiddenLootModifier(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Override
	protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		for (ItemStack stack : generatedLoot) {
			if (stack.isEnchanted() || stack.getItem() instanceof EnchantedBookItem) {
				stack.getCapability(HiddenEnchantProvider.ENCHANTMENTS).ifPresent(cap -> {
					for (Enchantment enchantment : EnchantmentHelper.getEnchantments(stack).keySet()) {
						cap.add(enchantment);
					}
				});
			}
		}
		return generatedLoot;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC.get();
	}

}
