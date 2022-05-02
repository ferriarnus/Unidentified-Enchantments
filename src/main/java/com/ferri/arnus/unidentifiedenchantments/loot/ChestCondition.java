package com.ferri.arnus.unidentifiedenchantments.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.CapabilityItemHandler;

public class ChestCondition implements LootItemCondition{
		public static final LootItemConditionType HIDDEN_CHEST = new LootItemConditionType(new ChestCondition.ConditionSerializer());
		
		private ChestCondition() {
			
		}

		@Override
		public LootItemConditionType getType() {
			return HIDDEN_CHEST;
		}

		@Override
		public boolean test(LootContext lootContext) {
			if (lootContext.getQueriedLootTableId().getPath().equals("dispensers")) return true;
			if (lootContext.getQueriedLootTableId().getPath().equals("barrels")) return true;
			if (lootContext.getQueriedLootTableId().getPath().equals("shulker_boxes")) return true;
			if (lootContext.getQueriedLootTableId().getPath().contains("urn_loot")) return true;
			if (lootContext.getQueriedLootTableId().getPath().contains("chests")) return true;
			if (lootContext.hasParam(LootContextParams.ORIGIN)) {
				Vec3 param = lootContext.getParam(LootContextParams.ORIGIN);
				BlockEntity blockEntity = lootContext.getLevel().getBlockEntity(new BlockPos(param));
				if (blockEntity == null) return false;
				if (blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).isPresent()) {
					return true;
				}
			}
			return false;
		}

		public static class ConditionSerializer implements Serializer<ChestCondition> {
			@Override
			public void serialize(JsonObject object, ChestCondition instance, JsonSerializationContext ctx) {

			}

			@Override
			public ChestCondition deserialize(JsonObject object, JsonDeserializationContext ctx) {
				return new ChestCondition();
			}
		}

}
