//package com.ferri.arnus.unidentifiedenchantments.mixin;
//
//import java.util.List;
//
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Redirect;
//
//import com.ferri.arnus.unidentifiedenchantments.HideEnchantsEvents;
//import com.ferri.arnus.unidentifiedenchantments.capability.HiddenEnchantProvider;
//import com.ferri.arnus.unidentifiedenchantments.compat.enchantingoverhauled.HideEnchantingOverhauledEvents;
//import com.mojang.blaze3d.vertex.PoseStack;
//
//import johnsmith.enchantingoverhauled.items.EnchantedTomeItem;
//import johnsmith.enchantingoverhauled.screens.ModEnchantmentScreen;
//import net.minecraft.ChatFormatting;
//import net.minecraft.core.Registry;
//import net.minecraft.network.chat.Component;
//import net.minecraft.network.chat.TextComponent;
//import net.minecraft.network.chat.TranslatableComponent;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.enchantment.Enchantment;
//import net.minecraft.world.item.enchantment.EnchantmentHelper;
//
//@Mixin(ModEnchantmentScreen.class)
//public class ModEnchantmentScreenMixin {
//
//	@Redirect(at = @At(value = "INVOKE", target = "Ljohnsmith/enchantingoverhauled/screens/ModEnchantmentScreen;renderComponentTooltip(Lcom/mojang/blaze3d/vertex/PoseStack;Ljava/util/List;II)V"), method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;IIF)V")
//	public void hide(ModEnchantmentScreen screen, PoseStack poseStack, List<Component> list, int mouseX, int mouseY) {
//		ItemStack item = ((ModEnchantmentScreen) (Object) this).getMenu().slots.get(0).getItem();
//		ItemStack book = ((ModEnchantmentScreen) (Object) this).getMenu().slots.get(2).getItem();
//		book.getCapability(HiddenEnchantProvider.ENCHANTMENTS).ifPresent(cap -> {
//			for (Enchantment enchantment: cap.getHiddenMap().keySet()) {
//				if (book.getItem() instanceof EnchantedTomeItem) {
//					if (!HideEnchantingOverhauledEvents.getEnchantments(book).containsKey(enchantment)) {
//						continue;
//					}
//					Registry.ENCHANTMENT.getOptional(EnchantmentHelper.getEnchantmentId(enchantment)).ifPresent((p_41708_) -> {
//						if (!list.isEmpty() && ((TranslatableComponent)list.get(0)).getArgs()[0].equals(p_41708_.getFullname(HideEnchantingOverhauledEvents.getEnchantments(book).get(enchantment)))) {
//							list.set(0, new TextComponent(cap.getHiddenMap().get(enchantment)).withStyle(HideEnchantsEvents.ROOT_STYLE).withStyle(ChatFormatting.GRAY).append(" ").append(new TranslatableComponent("enchantment.level." + HideEnchantingOverhauledEvents.getEnchantments(book).get(enchantment))));
//						}
//					});
//				} else {
//					if (!EnchantmentHelper.getEnchantments(book).containsKey(enchantment)) {
//						continue;
//					}
//					Registry.ENCHANTMENT.getOptional(EnchantmentHelper.getEnchantmentId(enchantment)).ifPresent((p_41708_) -> {
//						if (!list.isEmpty() && ((TranslatableComponent)list.get(0)).getArgs()[0].equals(p_41708_.getFullname(EnchantmentHelper.getEnchantments(book).get(enchantment)))) {
//							list.set(0, new TextComponent(cap.getHiddenMap().get(enchantment)).withStyle(HideEnchantsEvents.ROOT_STYLE).withStyle(ChatFormatting.GRAY).append(" ").append(new TranslatableComponent("enchantment.level." + EnchantmentHelper.getEnchantments(book).get(enchantment))));
//						}
//					});
//				}
//			}
//		});
//		item.getCapability(HiddenEnchantProvider.ENCHANTMENTS).ifPresent(cap -> {
//			for (Enchantment enchantment: cap.getHiddenMap().keySet()) {
//				if (item.getItem() instanceof EnchantedTomeItem) {
//					if (!HideEnchantingOverhauledEvents.getEnchantments(item).containsKey(enchantment)) {
//						continue;
//					}
//					Registry.ENCHANTMENT.getOptional(EnchantmentHelper.getEnchantmentId(enchantment)).ifPresent((p_41708_) -> {
//						if (!list.isEmpty() && list.get(0) instanceof TranslatableComponent ) {
//							String string1 = p_41708_.getFullname(HideEnchantingOverhauledEvents.getEnchantments(item).get(enchantment)).getString();
//							String string2 = ((TranslatableComponent)list.get(0)).getString();
//							if (string1.substring(0, string1.lastIndexOf(" ")).equals(string2.substring(0, string2.lastIndexOf(" ")))) {
//								list.set(0, new TextComponent(cap.getHiddenMap().get(enchantment)).withStyle(HideEnchantsEvents.ROOT_STYLE).withStyle(ChatFormatting.GRAY).append(" ").append(new TranslatableComponent("enchantment.level." + HideEnchantingOverhauledEvents.getEnchantments(item).get(enchantment))));
//							}
//						}
//					});
//				} else {
//					if (!EnchantmentHelper.getEnchantments(item).containsKey(enchantment)) {
//						continue;
//					}
//					Registry.ENCHANTMENT.getOptional(EnchantmentHelper.getEnchantmentId(enchantment)).ifPresent((p_41708_) -> {
//						if (!list.isEmpty() && list.get(0) instanceof TranslatableComponent ) {
//							String string1 = p_41708_.getFullname(EnchantmentHelper.getEnchantments(item).get(enchantment)).getString();
//							String string2 = ((TranslatableComponent)list.get(0)).getString();
//							if (string1.substring(0, string1.lastIndexOf(" ")).equals(string2.substring(0, string2.lastIndexOf(" ")))) {
//								list.set(0, new TextComponent(cap.getHiddenMap().get(enchantment)).withStyle(HideEnchantsEvents.ROOT_STYLE).withStyle(ChatFormatting.GRAY).append(" ").append(new TranslatableComponent("enchantment.level." + EnchantmentHelper.getEnchantments(item).get(enchantment))));
//							}
//						}
//					});
//				}
//			}
//		});
//		screen.renderComponentTooltip(poseStack, list, mouseX, mouseY);
//	}
//}
