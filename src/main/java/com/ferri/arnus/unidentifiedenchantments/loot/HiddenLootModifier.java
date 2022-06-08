package com.ferri.arnus.unidentifiedenchantments.loot;

import org.jetbrains.annotations.NotNull;

import com.ferri.arnus.unidentifiedenchantments.capability.HiddenEnchantProvider;
import com.google.gson.JsonObject;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

public class HiddenLootModifier extends LootModifier{

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
	
	static class Serializer extends GlobalLootModifierSerializer<HiddenLootModifier> {
		
		@Override
		public HiddenLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
			return new HiddenLootModifier(ailootcondition);
		}

		@Override
		public JsonObject write(HiddenLootModifier instance) {
			return makeConditions(instance.conditions);
		}
	}
}
