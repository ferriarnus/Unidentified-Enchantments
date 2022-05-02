//package com.ferri.arnus.unidentifiedenchantments.mixin;
//
//import java.util.Map;
//
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import com.ferri.arnus.unidentifiedenchantments.capability.HiddenEnchantProvider;
//
//import johnsmith.enchantingoverhauled.inventory.ModEnchantmentMenu;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.NonNullList;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.inventory.Slot;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.enchantment.Enchantment;
//import net.minecraft.world.level.Level;
//
//@Mixin(ModEnchantmentMenu.class)
//public class ModEnchantmentMenuMixin {
//
//	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/Container;setChanged()V"), method = "lambda$clickMenuButton$2(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;ILnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;ILnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V")
//	void stayhidden(ItemStack itemstack, ItemStack itemstack3, int pid, Player pPlayer, ItemStack itemstack1, int i,Level level, BlockPos pos, CallbackInfo info) {
//		NonNullList<Slot> slots = ((ModEnchantmentMenu)(Object) this).slots;
//		if (!slots.get(2).getItem().isEmpty()) {
//			slots.get(0).getItem().getCapability(HiddenEnchantProvider.ENCHANTMENTS).ifPresent(cap1 -> {
//				slots.get(2).getItem().getCapability(HiddenEnchantProvider.ENCHANTMENTS).ifPresent(cap2 -> {
//					Map<Enchantment, String> hiddenMap = cap1.getHiddenMap();
//					hiddenMap.putAll(cap2.getHiddenMap());
//					cap1.setHiddenMap(hiddenMap);
//				});
//			});
//		}
//	}
//}
