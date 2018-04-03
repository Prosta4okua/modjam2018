package com.rwtema.monkmod.advancements.criteria;

import com.rwtema.monkmod.data.MonkData;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.AxisAlignedBB;

public class MonkRequirementMeditateEndermen extends MonkRequirementTick {
	final int time;

	public MonkRequirementMeditateEndermen(int level, int time) {
		super(level);
		this.time = time;
	}

	@Override
	protected void doTick(EntityPlayerMP player, MonkData monkData) {
		AxisAlignedBB entityBoundingBox = player.getEntityBoundingBox();
		AxisAlignedBB grow = entityBoundingBox.grow(20);
		for (EntityEnderman enderman : player.world.getEntitiesWithinAABB(EntityEnderman.class, grow)) {
			if (enderman.isScreaming()) {
				monkData.setProgress(0);
				return;
			} else {
				if (monkData.increase(1, time)) {
					grantLevel(player);
				}
			}
		}
	}
}
