package com.ferri.arnus.unidentifiedenchantments.capability;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Maps;

import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class HiddenEnchantments implements IHiddenEnchantments{
	
	private Map<Enchantment,String> hiddenmap = new HashMap<Enchantment, String>();
	private final Random random = new Random();
	private final String[] words = new String[]{"the", "elder", "scrolls", "klaatu", "berata", "niktu", "xyzzy", "bless", "curse", "light", "darkness", "fire", "air", "earth", "water", "hot", "dry", "cold", "wet", "ignite", "snuff", "embiggen", "twist", "shorten", "stretch", "fiddle", "destroy", "imbue", "galvanize", "enchant", "free", "limited", "range", "of", "towards", "inside", "sphere", "cube", "self", "other", "ball", "mental", "physical", "grow", "shrink", "demon", "elemental", "spirit", "animal", "creature", "beast", "humanoid", "undead", "fresh", "stale", "phnglui", "mglwnafh", "cthulhu", "rlyeh", "wgahnagl", "fhtagn", "baguette"};

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		ListTag listtag = new ListTag();
		
		for(Enchantment enchantment : hiddenmap.keySet()) {
			if (enchantment != null) {
				CompoundTag storeEnchantment = new CompoundTag();
				storeEnchantment.putString("id", String.valueOf((Object)EnchantmentHelper.getEnchantmentId(enchantment)));
				storeEnchantment.putString("text", hiddenmap.get(enchantment));
				listtag.add(storeEnchantment);
			}
		}
		if (listtag.isEmpty()) {
			tag.remove("HiddenEnchantments");
		} else {
			tag.put("HiddenEnchantments", listtag);
		}
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		Map<Enchantment, String> map = Maps.newLinkedHashMap();
		ListTag listtag = nbt.getList("HiddenEnchantments", 10);
		
		for(int i = 0; i < listtag.size(); ++i) {
			CompoundTag compoundtag = listtag.getCompound(i);
			Registry.ENCHANTMENT.getOptional(EnchantmentHelper.getEnchantmentId(compoundtag)).ifPresent((enc) -> {
				map.put(enc, compoundtag.getString("text"));
			});
		}
		hiddenmap = map;
	}
	
	@Override
	public Map<Enchantment,String> getHiddenMap() {
		return hiddenmap;
	}
	
	public void add(Enchantment enchantment) {
		hiddenmap.put(enchantment, getRandomName(75));
	}
	
	public String getRandomName(int pMaxWidth) {
	      StringBuilder stringbuilder = new StringBuilder();
	      int i = random.nextInt(2) + 3;

	      for(int j = 0; j < i; ++j) {
	         if (j != 0) {
	            stringbuilder.append(" ");
	         }

	         stringbuilder.append(Util.getRandom(this.words, this.random));
	      }
	      stringbuilder.setLength(pMaxWidth);
	      return stringbuilder.toString().replace('\0', ' ').strip();
	   }

}
