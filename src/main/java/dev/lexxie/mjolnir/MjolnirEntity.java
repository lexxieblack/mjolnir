package dev.lexxie.mjolnir;

import com.geckolib.animatable.GeoEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.util.GeckoLibUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class MjolnirEntity extends PathfinderMob implements GeoEntity {
	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	protected MjolnirEntity( EntityType<? extends PathfinderMob> entityType, Level level ) {
		super(entityType, level);
	}

	@Override
	public void registerControllers( final AnimatableManager.ControllerRegistrar controllers ) {

	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}
}
