package net.qualitycard.burningembers.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.qualitycard.burningembers.BurningEmbers;
import net.qualitycard.burningembers.effect.ModEffects;

public class RoaringInferno extends SwordItem {
    public RoaringInferno(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    private int hits = 0;

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (stack.getItem() instanceof RoaringInferno roaringInferno) {
            roaringInferno.hits += 1;
            updateTexture(roaringInferno, stack);
            BurningEmbers.LOGGER.info(String.valueOf(hits));

            if (roaringInferno.hits > 3) {
                target.addStatusEffect(new StatusEffectInstance(ModEffects.INFERNAL.value(), 100, 0));
            }
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        dash(world, user);
        return super.use(world, user, hand);
    }

    public void dash(World world, PlayerEntity user) {
        var entitiesList = world.getEntitiesByType(TypeFilter.instanceOf(LivingEntity.class), new Box(user.getX() - 4, user.getY() - 4, user.getZ() - 4, user.getX() + 4, user.getY() + 999, user.getZ() + 4), EntityPredicates.VALID_ENTITY);
        if (user.getMainHandStack().getItem() instanceof  RoaringInferno roaringInferno) {
            for (Entity selectedEntity : entitiesList) {
                if (selectedEntity != user) {
                    if (selectedEntity instanceof LivingEntity livingEntity) {
                        livingEntity.addVelocity(0, 1, 0);
                        if (roaringInferno.hits >= 3) {
                            roaringInferno.hits = 0;
                            updateTexture(roaringInferno, user.getMainHandStack());
                        }
                    }
                }
            }
        }
    }

    private void updateTexture(RoaringInferno roaringInferno, ItemStack stack) {
        if (roaringInferno.hits == 0) {
            stack.getOrCreateNbt().putBoolean("hit_1", false);
            stack.getOrCreateNbt().putBoolean("hit_2", false);
            stack.getOrCreateNbt().putBoolean("hit_3", false);
        }
        if (roaringInferno.hits == 1) {
            stack.getOrCreateNbt().putBoolean("hit_1", true);
            stack.getOrCreateNbt().putBoolean("hit_2", false);
            stack.getOrCreateNbt().putBoolean("hit_3", false);
        }
        if (roaringInferno.hits == 2) {
            stack.getOrCreateNbt().putBoolean("hit_1", false);
            stack.getOrCreateNbt().putBoolean("hit_2", true);
            stack.getOrCreateNbt().putBoolean("hit_3", false);
        }
        if (roaringInferno.hits >= 3) {
            stack.getOrCreateNbt().putBoolean("hit_1", false);
            stack.getOrCreateNbt().putBoolean("hit_2", false);
            stack.getOrCreateNbt().putBoolean("hit_3", true);
        }
    }
}
