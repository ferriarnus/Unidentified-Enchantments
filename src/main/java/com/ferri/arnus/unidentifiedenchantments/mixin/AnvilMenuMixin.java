package com.ferri.arnus.unidentifiedenchantments.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.ferri.arnus.unidentifiedenchantments.capability.HiddenEnchantProvider;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.EnchantedBookItem;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin extends ItemCombinerMenu {

	public AnvilMenuMixin(MenuType<?> p_39773_, int p_39774_, Inventory p_39775_, ContainerLevelAccess p_39776_) {
		super(p_39773_, p_39774_, p_39775_, p_39776_);
	}
	
	@Inject(at = @At("RETURN"), method = "createResult()V")
	public void createResult(CallbackInfo callback) {
		if (this.inputSlots.getItem(1).getItem() instanceof EnchantedBookItem) {
			if (this.inputSlots.getItem(0).isBookEnchantable(this.inputSlots.getItem(1))) {
				this.inputSlots.getItem(1).getCapability(HiddenEnchantProvider.ENCHANTMENTS).ifPresent(book -> {
					if (!book.getHiddenMap().isEmpty()) {
						this.resultSlots.getItem(0).getCapability(HiddenEnchantProvider.ENCHANTMENTS).ifPresent(stack -> {
							stack.getHiddenMap().putAll(book.getHiddenMap());
						});
					}
				});
			}
		}
	}

}
