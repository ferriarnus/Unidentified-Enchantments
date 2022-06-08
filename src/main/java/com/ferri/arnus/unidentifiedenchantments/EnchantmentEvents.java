package com.ferri.arnus.unidentifiedenchantments;

import java.util.Random;
import java.util.UUID;

import com.ferri.arnus.unidentifiedenchantments.enchantment.EnchantmentRegistry;
import com.ferri.arnus.unidentifiedenchantments.entity.EntityRegistry;
import com.ferri.arnus.unidentifiedenchantments.entity.FakeCreeper;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player.BedSleepingProblem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = UnidentifiedEnchantments.MODID)
public class EnchantmentEvents {
		
	private static final UUID SPEED_MODIFIER_CURSE_UUID = UUID.fromString("530c11c4-22b0-43da-9805-32decc530a82");
	
	@SubscribeEvent
	static void madness(PlayerTickEvent event) {
		if (EnchantmentHelper.getRandomItemWith(EnchantmentRegistry.MADNESSCURSE.get(), event.player) != null && event.player.level.getGameTime() % 1200 == 0 && event.phase == Phase.END && !event.player.level.isClientSide && new Random().nextDouble() < 0.1) {
			FakeCreeper creeper = EntityRegistry.FAKECREEPER.get().create(event.player.level);
			creeper.setPlayer(event.player);
			for(int i = 0; i < 16; ++i) {
				double d3 = event.player.getX() + (event.player.level.random.m_188500_() - 0.5D) * 16.0D;
				double d4 = Mth.clamp(event.player.getY() + (double)(event.player.level.random.m_188503_(16) - 8), (double)event.player.level.getMinBuildHeight(), (double)(event.player.level.getMinBuildHeight() + ((ServerLevel)event.player.level).getLogicalHeight() - 1));
				double d5 = event.player.getZ() + (event.player.level.random.m_188500_() - 0.5D) * 16.0D;
				if (creeper.randomTeleport(d3, d4, d5, false)) {
					break;
				}
			}
			event.player.level.addFreshEntity(creeper);
		}
	}
	
	@SubscribeEvent
	static void explode(ExplosionEvent.Detonate event) {
		if (event.getExplosion().getSourceMob() instanceof FakeCreeper) {
			event.getAffectedEntities().clear();
			event.getAffectedBlocks().clear();
		}
	}
	
	@SubscribeEvent
	static void weight(LivingEquipmentChangeEvent event) {
		if (EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.WEIGHTCURSE.get(), event.getTo()) != 0 && EnchantmentHelper.getRandomItemWith(EnchantmentRegistry.WEIGHTCURSE.get(), event.getEntityLiving()) != null) {
			AttributeInstance attributeinstance = event.getEntityLiving().getAttribute(ForgeMod.SWIM_SPEED.get());
			if (attributeinstance != null && attributeinstance.getModifier(SPEED_MODIFIER_CURSE_UUID) == null) {
				attributeinstance.addTransientModifier(new AttributeModifier(SPEED_MODIFIER_CURSE_UUID,"curse weight", -0.40, AttributeModifier.Operation.MULTIPLY_TOTAL));
			}
		}
		if (EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.WEIGHTCURSE.get(), event.getFrom()) >= 0 && EnchantmentHelper.getRandomItemWith(EnchantmentRegistry.WEIGHTCURSE.get(), event.getEntityLiving()) == null) {
			AttributeInstance attributeinstance = event.getEntityLiving().getAttribute(ForgeMod.SWIM_SPEED.get());
			if (attributeinstance != null) {
				if(attributeinstance.getModifier(SPEED_MODIFIER_CURSE_UUID) != null) {
					attributeinstance.removeModifier(SPEED_MODIFIER_CURSE_UUID);
				}
			}
		}
	}
	
	@SubscribeEvent
	static void sleep(PlayerSleepInBedEvent event) {
		if (EnchantmentHelper.getRandomItemWith(EnchantmentRegistry.INSOMNIACURSE.get(), event.getEntityLiving()) != null) {
			event.setResult(BedSleepingProblem.NOT_SAFE);
		}
	}
}
