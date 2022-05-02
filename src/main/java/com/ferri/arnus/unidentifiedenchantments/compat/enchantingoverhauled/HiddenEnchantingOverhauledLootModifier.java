//package com.ferri.arnus.unidentifiedenchantments.compat.enchantingoverhauled;
//
//import java.util.List;
//import java.util.Optional;
//
//import com.ferri.arnus.unidentifiedenchantments.capability.HiddenEnchantProvider;
//import com.google.gson.JsonObject;
//
//import johnsmith.enchantingoverhauled.items.EnchantedTomeItem;
//import net.minecraft.core.Registry;
//import net.minecraft.nbt.ListTag;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.enchantment.Enchantment;
//import net.minecraft.world.level.storage.loot.LootContext;
//import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
//import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
//import net.minecraftforge.common.loot.LootModifier;
//
//public class HiddenEnchantingOverhauledLootModifier extends LootModifier{
//
//	protected HiddenEnchantingOverhauledLootModifier(LootItemCondition[] conditionsIn) {
//		super(conditionsIn);
//	}
//
//	@Override
//	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
//		for (ItemStack stack : generatedLoot) {
//			if (stack.getItem() instanceof EnchantedTomeItem) {
//				stack.getCapability(HiddenEnchantProvider.ENCHANTMENTS).ifPresent(cap -> {
//					ListTag enchantments = EnchantedTomeItem.getEnchantments(stack);
//					for (int i = 0; i < enchantments.size(); i++) {
//						Optional<Enchantment> optional = Registry.ENCHANTMENT.getOptional(new ResourceLocation(enchantments.getCompound(i).getString("id")));
//						optional.ifPresent(o -> cap.add(o));
//					}
//				});
//			}
//		}
//		return generatedLoot;
//	}
//	
//	static class Serializer extends GlobalLootModifierSerializer<HiddenEnchantingOverhauledLootModifier> {
//		
//		@Override
//		public HiddenEnchantingOverhauledLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
//			return new HiddenEnchantingOverhauledLootModifier(ailootcondition);
//		}
//
//		@Override
//		public JsonObject write(HiddenEnchantingOverhauledLootModifier instance) {
//			return makeConditions(instance.conditions);
//		}
//	}
//}
