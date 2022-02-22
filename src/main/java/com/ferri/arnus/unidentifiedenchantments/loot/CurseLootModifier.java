package com.ferri.arnus.unidentifiedenchantments.loot;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ferri.arnus.unidentifiedenchantments.enchantment.EnchantmentRegistry;
import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

public class CurseLootModifier extends LootModifier{

	protected CurseLootModifier(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		for (ItemStack stack : generatedLoot) {
			Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
			if (!enchantments.isEmpty()) {
				if(0.15 + 0.1*enchantments.size() > new Random().nextDouble()) {
					stack.enchant(EnchantmentRegistry.CURSELIST.get(new Random().nextInt(EnchantmentRegistry.CURSELIST.size())).get(), 1);
				}
			}
		}
		return generatedLoot;
	}
	
static class Serializer extends GlobalLootModifierSerializer<CurseLootModifier> {
		
		@Override
		public CurseLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
			return new CurseLootModifier(ailootcondition);
		}

		@Override
		public JsonObject write(CurseLootModifier instance) {
			return makeConditions(instance.conditions);
		}
		
	}

}
