package com.ferri.arnus.unidentifiedenchantments;

import com.ferri.arnus.unidentifiedenchantments.enchantment.EnchantmentRegistry;
import com.ferri.arnus.unidentifiedenchantments.entity.EntityRegistry;
import com.ferri.arnus.unidentifiedenchantments.entity.FakeCreeper;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = UnidentifiedEnchantments.MODID)
public class EnchantmentEvents {
		
	@SubscribeEvent
	static void madness(PlayerTickEvent event) {
		if (EnchantmentHelper.getRandomItemWith(EnchantmentRegistry.MADNESSCURSE.get(), event.player) != null && event.player.level.getGameTime() % 120 == 0 && event.side.isServer() && event.phase == Phase.END) {
			FakeCreeper creeper = EntityRegistry.FAKECREEPER.get().create(event.player.level);
			event.player.level.addFreshEntity(creeper);
			creeper.setPos(event.player.position());
			creeper.setPlayer(event.player);
		}
	}
}
