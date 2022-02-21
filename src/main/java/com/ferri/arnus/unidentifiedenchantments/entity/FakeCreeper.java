package com.ferri.arnus.unidentifiedenchantments.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class FakeCreeper extends Creeper{
	
	private Player player;

	public FakeCreeper(EntityType<? extends Creeper> p_32278_, Level p_32279_) {
		super(EntityRegistry.FAKECREEPER.get(), p_32279_);
	}
	
	public void setPlayer(Player player) {
		this.player = player;
		System.out.println("set");
		System.out.println(this.player);
	}
	
	@Override
	public boolean isInvisible() {
		return true;
	}
	
	@Override
	public boolean isInvisibleTo(Player p_20178_) {
		if (this.player == null) {
			return true;
		}
		if (p_20178_.getUUID().equals(this.player.getUUID())) {
			return false;
		}
		return true;
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new SwellGoal(this));
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Ocelot.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, p -> p.equals(player)));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}

}
