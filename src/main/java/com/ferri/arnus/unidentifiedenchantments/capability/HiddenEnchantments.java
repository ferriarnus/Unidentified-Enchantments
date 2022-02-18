package com.ferri.arnus.unidentifiedenchantments.capability;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.EnchantmentNames;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class HiddenEnchantments implements IHiddenEnchantments{
	
	private Map<Enchantment,String> hiddenmap = new HashMap<Enchantment, String>();

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
		hiddenmap.put(enchantment, EnchantmentNames.getInstance().getRandomName(Minecraft.getInstance().font, 86).getString());
	}

}
