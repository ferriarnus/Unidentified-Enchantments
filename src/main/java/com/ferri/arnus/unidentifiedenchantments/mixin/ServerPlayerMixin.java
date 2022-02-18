package com.ferri.arnus.unidentifiedenchantments.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.ferri.arnus.unidentifiedenchantments.enchantment.EnchantmentRegistry;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {

	@Redirect(at = @At(value = "INVOKE", target = "Ljava/util/List;isEmpty()Z"), method = "startSleepInBed(Lnet/minecraft/core/BlockPos;)Lcom/mojang/datafixers/util/Either;", remap = false)
	public boolean insomnia(List<Monster> list) {
		return list.isEmpty() && EnchantmentHelper.getRandomItemWith(EnchantmentRegistry.INSOMNIACURSE.get(), (ServerPlayer)(Object)this) == null;
	}
}
