package com.ferri.arnus.unidentifiedenchantments.entity;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;

import java.util.UUID;

public class FakeCreeper extends Creeper implements IEntityAdditionalSpawnData{
	
	private UUID player = UUID.randomUUID();
	private float explosionRadius = 3;

	public FakeCreeper(EntityType<? extends Creeper> p_32278_, Level p_32279_) {
		super(EntityRegistry.FAKECREEPER.get(), p_32279_);
	}
	
	@Override
	public boolean isInvisible() {
		return true;
	}
	
	public void setPlayer(Player player) {
		this.player = player.getUUID();
	}
	
	@Override
	public boolean isInvisibleTo(Player p_20178_) {
		if (this.player == null) {
			return true;
		}
		if (p_20178_.getUUID().equals(this.player)) {
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
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, p -> p.getUUID().equals(player)));
	}

	@Override
	public void writeSpawnData(FriendlyByteBuf buffer) {
		buffer.writeUUID(this.player);
	}

	@Override
	public void readSpawnData(FriendlyByteBuf additionalData) {
		this.player = additionalData.readUUID();
	}
	
	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
