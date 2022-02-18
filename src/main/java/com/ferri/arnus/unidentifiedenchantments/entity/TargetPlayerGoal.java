package com.ferri.arnus.unidentifiedenchantments.entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;

public class TargetPlayerGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T>{

	private Player player;

	public TargetPlayerGoal(Mob p_26060_, Class<T> p_26061_, boolean p_26062_, Player player) {
		super(p_26060_, p_26061_, p_26062_);
		this.player = player;
	}
}
