package com.ferri.arnus.unidentifiedenchantments.loot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

import org.jetbrains.annotations.NotNull;

import com.ferri.arnus.unidentifiedenchantments.enchantment.EnchantmentRegistry;
import com.google.gson.JsonObject;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.RegistryObject;

public class CurseLootModifier extends LootModifier{

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
					EnchantedBookItem.addEnchantment(stack, new EnchantmentInstance(list.get(new Random().nextInt(list.size())).get(), 1));
				} else {
					Collections.shuffle(list);
					for (RegistryObject<? extends Enchantment> enchantment: list) {
						if (enchantment.get().canEnchant(stack)) {
							stack.enchant(enchantment.get(), 1);
							break;
						}
					}
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
