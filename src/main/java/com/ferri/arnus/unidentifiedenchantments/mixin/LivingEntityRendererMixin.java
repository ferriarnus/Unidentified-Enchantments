package com.ferri.arnus.unidentifiedenchantments.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.ferri.arnus.unidentifiedenchantments.entity.FakeCreeper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin implements RenderLayerParent<LivingEntity, EntityModel<LivingEntity>> {

	@Shadow
	private EntityModel<LivingEntity> model;

	@Inject(at = @At("HEAD"), method = "getRenderType(Lnet/minecraft/world/entity/LivingEntity;ZZZ)Lnet/minecraft/client/renderer/RenderType;", cancellable = true)
	public void invis(LivingEntity e, boolean b1, boolean b2, boolean b3, CallbackInfoReturnable<RenderType> info) {
		if (e instanceof FakeCreeper && !e.isInvisibleTo(Minecraft.getInstance().player)) {
			ResourceLocation resourcelocation = this.getTextureLocation(e);
			info.setReturnValue(this.model.renderType(resourcelocation));
		}
		if (e instanceof FakeCreeper && e.isInvisibleTo(Minecraft.getInstance().player)) {
			info.setReturnValue(null);
		}
	}
}
