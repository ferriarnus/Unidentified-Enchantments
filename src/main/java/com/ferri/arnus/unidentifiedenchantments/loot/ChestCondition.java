package com.ferri.arnus.unidentifiedenchantments.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

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
			return lootContext.getQueriedLootTableId().getPath().startsWith("chests");
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
