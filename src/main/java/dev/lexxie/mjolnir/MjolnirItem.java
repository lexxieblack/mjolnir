package dev.lexxie.mjolnir;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class MjolnirItem extends Item {

	public MjolnirItem( Properties properties ) {
		super(properties);
	}

	@Override
	public ItemUseAnimation getUseAnimation( ItemStack itemStack ) {
		return ItemUseAnimation.TRIDENT;
	}

	@Override
	public int getUseDuration( ItemStack itemStack, LivingEntity user ) {
		return 72000;
	}

	@Override
	public boolean releaseUsing( ItemStack itemStack, Level level, LivingEntity entity, int remainingTime ) {
		if( entity instanceof Player player ) {
			int timeHeld = this.getUseDuration(itemStack, entity) - remainingTime;
			if( timeHeld < 10 ) return false;

			Holder<SoundEvent> sound = SoundEvents.TRIDENT_THROW;
			if( level instanceof ServerLevel serverLevel ) {
				itemStack.consume(1, player);
				BlockPos pos = BlockPos.containing(player.getEyePosition());
				return spawnMob(player, itemStack, level, pos);

				//				ItemStack thrownItemStack = itemStack.consumeAndReturn(1, player);
				//				MjolnirEntity trident = Projectile.spawnProjectileFromRotation(MjolnirEntity::new, serverLevel,
				//				thrownItemStack,
				//						player, 0.0F, 2.5F, 1.0F);
				//				if( player.hasInfiniteMaterials() ) {
				//					trident.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
				//				}
				//
				//				level.playSound(null, trident, sound.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
				//				return true;
			} else return false;
		} else return false;
	}

	private static boolean spawnMob( LivingEntity user, ItemStack itemStack, Level level, BlockPos spawnPos ) {
//		EntityType<?> type = getType(itemStack);
		EntityType<?> type = Mjolnir.MJOLNIR_ENTITY_TYPE.get();
		if( type == null ) {
			return false;
		}
		type.spawn((ServerLevel) level, itemStack, user, spawnPos, EntitySpawnReason.SPAWN_ITEM_USE, false, false);
		return true;
	}

	public static @Nullable EntityType<?> getType( ItemStack itemStack ) {
		TypedEntityData<EntityType<?>> entityData = itemStack.get(DataComponents.ENTITY_DATA);
		return entityData != null ? entityData.type() : null;
	}

	@Override
	public InteractionResult use( Level level, Player player, InteractionHand hand ) {
		ItemStack itemInHand = player.getItemInHand(hand);
		if( itemInHand.nextDamageWillBreak() ) {
			return InteractionResult.FAIL;
		} else if( EnchantmentHelper.getTridentSpinAttackStrength(itemInHand, player) > 0.0F && !player.isInWaterOrRain() ) {
			return InteractionResult.FAIL;
		} else {
			player.startUsingItem(hand);
			return InteractionResult.CONSUME;
		}
	}
}
