package com.ferri.arnus.unidentifiedenchantments.capability;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class HiddenEnchantments implements IHiddenEnchantments{
	
	private Map<Enchantment,String> hiddenmap = new HashMap<Enchantment, String>();
	private final ItemStack stack;
	private final RandomSource random = RandomSource.m_216327_();
	private final String[] words = new String[]{"the", "elder", "scrolls", "klaatu", "berata", "niktu", "xyzzy", "bless", "curse", "light", "darkness", "fire", "air", "earth", "water", "hot", "dry", "cold", "wet", "ignite", "snuff", "embiggen", "twist", "shorten", "stretch", "fiddle", "destroy", "imbue", "galvanize", "enchant", "free", "limited", "range", "of", "towards", "inside", "sphere", "cube", "self", "other", "ball", "mental", "physical", "grow", "shrink", "demon", "elemental", "spirit", "animal", "creature", "beast", "humanoid", "undead", "fresh", "stale", "phnglui", "mglwnafh", "cthulhu", "rlyeh", "wgahnagl", "fhtagn", "baguette"};

	public HiddenEnchantments(ItemStack stack) {
		this.stack = stack;
	}
	
	public void setHiddenMap(Map<Enchantment,String> hiddenmap) {
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
			stack.getOrCreateTag().remove("HiddenEnchantments");
		} else {
			stack.getOrCreateTag().put("HiddenEnchantments", listtag);
		}
	}

	public Map<Enchantment,String> getHiddenMap() {
		Map<Enchantment, String> map = Maps.newLinkedHashMap();
		ListTag listtag = stack.getOrCreateTag().getList("HiddenEnchantments", 10);
		
		for(int i = 0; i < listtag.size(); ++i) {
			CompoundTag compoundtag = listtag.getCompound(i);
			Registry.ENCHANTMENT.getOptional(EnchantmentHelper.getEnchantmentId(compoundtag)).ifPresent((enc) -> {
				map.put(enc, compoundtag.getString("text"));
			});
		}
		return map;
	}
	
	public void add(Enchantment enchantment) {
		Map<Enchantment, String> map = this.getHiddenMap();
		map.put(enchantment, getRandomName(75));
		this.setHiddenMap(map);
		
	}
	
	public void clearHiddenMap() {
		stack.getOrCreateTag().remove("HiddenEnchantments");
	}
	
	public String getRandomName(int pMaxWidth) {
	      StringBuilder stringbuilder = new StringBuilder();
	      int i = random.m_188503_(2) + 3;

	      for(int j = 0; j < i; ++j) {
	         if (j != 0) {
	            stringbuilder.append(" ");
	         }

	         stringbuilder.append(Util.m_214670_(this.words, this.random));
	      }
	      stringbuilder.setLength(pMaxWidth);
	      return stringbuilder.toString().replace('\0', ' ').strip();
	   }

}
