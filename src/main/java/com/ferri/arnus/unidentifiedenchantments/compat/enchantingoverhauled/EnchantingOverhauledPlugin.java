package com.ferri.arnus.unidentifiedenchantments.compat.enchantingoverhauled;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import net.minecraftforge.fml.loading.FMLLoader;

public class EnchantingOverhauledPlugin implements IMixinConfigPlugin{

	@Override
	public void onLoad(String mixinPackage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRefMapperConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		if(FMLLoader.getLoadingModList().getModFileById("enchanting_overhauled") != null) {
        	return true;
        }
		return false;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getMixins() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// TODO Auto-generated method stub
		
	}

}
