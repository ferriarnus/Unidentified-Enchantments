package com.ferri.arnus.unidentifiedenchantments.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class ExpStorage implements INBTSerializable<CompoundTag>{
	private int level;
	private int maxlevel = 5;
	
	public void addLevel(int level) {
		if (this.level < this.maxlevel) {
			this.level += level;
		}
	}
	
	public void setMaxLevel(int maxlevel) {
		this.maxlevel = maxlevel;
	}
	
	public boolean isFilled() {
		return level >= maxlevel;
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		tag.putInt("level", level);
		tag.putInt("maxlevel", maxlevel);
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		this.level = nbt.getInt("level");
		this.maxlevel = nbt.getInt("maxlevel");
	}

}
