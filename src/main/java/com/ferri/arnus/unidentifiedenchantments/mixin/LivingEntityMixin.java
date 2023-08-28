package com.ferri.arnus.unidentifiedenchantments.mixin;

import com.ferri.arnus.unidentifiedenchantments.enchantment.EnchantmentRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow protected abstract void pushEntities();

    public LivingEntityMixin(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(method = "hasLineOfSight", at = @At("RETURN"), cancellable = true)
    public void unidentifiedenchantments$smell(Entity pEntity, CallbackInfoReturnable<Boolean> cir) {
        if (pEntity.level() != this.level()) {
            return;
        }
        Vec3 vec3 = new Vec3(this.getX(), this.getEyeY(), this.getZ());
        Vec3 vec31 = new Vec3(pEntity.getX(), pEntity.getEyeY(), pEntity.getZ());
        if (vec31.distanceTo(vec3) < 64.0D && pEntity instanceof LivingEntity living && EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistry.SMELLCURSE.get(), living) > 0) {
            cir.setReturnValue(true);
        }
    }
}
