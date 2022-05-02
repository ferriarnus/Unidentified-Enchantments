package com.ferri.arnus.unidentifiedenchantments.command;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.bridge.game.PackType;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.SharedConstants;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraft.world.level.storage.WorldData;
import net.minecraftforge.common.ForgeInternalHandler;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifierManager;

public class GlobalLootTableCommand {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("globalloot").executes(e -> sendLoot(e, "")).requires(r -> r.hasPermission(3))
				.then(Commands.argument("replace", GlobalLootArgument.string()).executes(e -> sendLoot(e, GlobalLootArgument.getString(e, "replace")))));
	}
	
	static int sendLoot(CommandContext<CommandSourceStack> commandContext, String s) {
		JsonObject j = new JsonObject();
		JsonArray array = new JsonArray();
		for (ResourceLocation r: collectLoot()) {
			if (!r.toString().equals("unidentifiedenchantments:hiddenloot")) {
				array.add(r.toString());
			}
		}
		array.add("unidentifiedenchantments:hiddenloot");
		j.addProperty("replace", true);
		j.add("entries", array);
		JsonElement jsonTree = GSON.toJsonTree(j);
		try {
			if (s.equals("replace")) {
				ensurePackDefinitionExists(commandContext.getSource().getServer().getWorldPath(LevelResource.DATAPACK_DIR).resolve("unidentifiedenchantmentsautopack"));
				Path path = commandContext.getSource().getServer().getWorldPath(LevelResource.DATAPACK_DIR).resolve("unidentifiedenchantmentsautopack/data/forge/loot_modifiers/global_loot_modifiers.json");
				Files.createDirectories(path.getParent());
				BufferedWriter bufferedwriter = Files.newBufferedWriter(path);
				bufferedwriter.write(GSON.toJson(jsonTree));
				bufferedwriter.close();
				//reload
				WorldData worlddata = commandContext.getSource().getServer().getWorldData();
				Collection<String> collection = commandContext.getSource().getServer().getPackRepository().getSelectedIds();
		        Collection<String> collection1 = discoverNewPacks(commandContext.getSource().getServer().getPackRepository(), worlddata, collection);
		        commandContext.getSource().getServer().reloadResources(collection1);
			} else if (s.equals("")) {
				Path path = Paths.get("config/global_loot_modifiers.json");
				Files.createDirectories(path.getParent());
				BufferedWriter bufferedwriter = Files.newBufferedWriter(path);
				bufferedwriter.write(GSON.toJson(jsonTree));
				bufferedwriter.close();
			} else if (s.equals("delete")) {
				Path path = commandContext.getSource().getServer().getWorldPath(LevelResource.DATAPACK_DIR).resolve("unidentifiedenchantmentsautopack/data/forge/loot_modifiers/global_loot_modifiers.json");
				Files.deleteIfExists(path);
				//reload
				WorldData worlddata = commandContext.getSource().getServer().getWorldData();
				Collection<String> collection = commandContext.getSource().getServer().getPackRepository().getSelectedIds();
		        Collection<String> collection1 = discoverNewPacks(commandContext.getSource().getServer().getPackRepository(), worlddata, collection);
		        commandContext.getSource().getServer().reloadResources(collection1);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	public static ArrayList<ResourceLocation> collectLoot() {
		try {
			Field field = ForgeInternalHandler.class.getDeclaredField("INSTANCE");
			field.setAccessible(true);
			LootModifierManager manager = (LootModifierManager) field.get(null);
			
			Field declaredField = LootModifierManager.class.getDeclaredField("registeredLootModifiers");
			declaredField.setAccessible(true);
			Map<ResourceLocation, IGlobalLootModifier> registeredLootModifiers = (Map<ResourceLocation, IGlobalLootModifier>) declaredField.get(manager);
			return new ArrayList<ResourceLocation>(registeredLootModifiers.keySet());
		} catch (Exception e) {
			System.err.println(e);
		}
		return new ArrayList<ResourceLocation>();
	}
	
	private static void ensurePackDefinitionExists(Path packRoot) {
		Path metaFile = packRoot.resolve("pack.mcmeta");
		if (!Files.exists(metaFile)) {
			JsonObject pack = new JsonObject();
			pack.addProperty("pack_format", SharedConstants.getCurrentVersion().getPackVersion(PackType.DATA));
			pack.addProperty("description", "Datapack generated by the Unidentified enchantments mod");
			
			JsonObject root = new JsonObject();
			root.add("pack", pack);
			
			try {
				Files.createDirectories(packRoot);
				try (BufferedWriter writer = Files.newBufferedWriter(metaFile)) {
					writer.write(GSON.toJson(root));
				}
			}
			catch (IOException e) {
				
			}
		}
	}
	
	private static Collection<String> discoverNewPacks(PackRepository p_138223_, WorldData p_138224_, Collection<String> p_138225_) {
		p_138223_.reload();
		Collection<String> collection = Lists.newArrayList(p_138225_);
		Collection<String> collection1 = p_138224_.getDataPackConfig().getDisabled();
		
		for(String s : p_138223_.getAvailableIds()) {
			if (!collection1.contains(s) && !collection.contains(s)) {
				collection.add(s);
			}
		}
		
		return collection;
	}
	
	private static class GlobalLootArgument implements ArgumentType<String> {
		
		public GlobalLootArgument() {
			// TODO Auto-generated constructor stub
		}
		
		public static GlobalLootArgument string() {
			return new GlobalLootArgument();
		}
		
		public static String getString(final CommandContext<?> context, final String name) {
	        return context.getArgument(name, String.class);
	    }

		@Override
		public String parse(StringReader reader) throws CommandSyntaxException {
			return reader.readUnquotedString();
		}
		
		@Override
		public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context,
				SuggestionsBuilder builder) {
			builder.suggest("replace");
			builder.suggest("delete");
			return builder.buildFuture();
		}
		
	}
}
