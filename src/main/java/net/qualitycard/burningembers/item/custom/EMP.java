package net.qualitycard.burningembers.item.custom;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.qualitycard.burningembers.damage_type.ModDamageTypes;
import net.qualitycard.burningembers.sound.ModSounds;

import java.awt.*;

public class EMP extends Item {
    public EMP(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {
            stack.getOrCreateNbt().putBoolean("deactivated", player.getItemCooldownManager().isCoolingDown(this));

        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            user.tiltScreen( 0.1, 0.1);

            var entitiesList = world.getEntitiesByType(TypeFilter.instanceOf(LivingEntity.class), new Box(user.getX() - 4, user.getY() - 4, user.getZ() - 4, user.getX() + 4, user.getY() + 999, user.getZ() + 2), EntityPredicates.VALID_ENTITY);
            for (Entity selectedEntity : entitiesList) {
                if (selectedEntity != user) {
                    if (selectedEntity instanceof LivingEntity livingEntity) {
                        livingEntity.damage(ModDamageTypes.of(world, ModDamageTypes.ELECTRICITY), 2f);
                        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 1));
                        if (selectedEntity instanceof PlayerEntity player) {
                            player.getItemCooldownManager().set(player.getMainHandStack().getItem(), 200);
                            player.getItemCooldownManager().set(player.getEquippedStack(EquipmentSlot.HEAD).getItem(), 200);
                            player.getItemCooldownManager().set(player.getEquippedStack(EquipmentSlot.CHEST).getItem(), 200);
                            player.getItemCooldownManager().set(player.getEquippedStack(EquipmentSlot.LEGS).getItem(), 200);
                            player.getItemCooldownManager().set(player.getEquippedStack(EquipmentSlot.FEET).getItem(), 200);

                            player.tiltScreen(0.1, 0.1);
                        }
                    }
                    selectedEntity.addVelocity(0, -5, 0);
                    selectedEntity.pushAwayFrom(user);
                }
            }
            world.playSound(null, BlockPos.ofFloored(user.getPos()), ModSounds.EMP_EXPLOSION, SoundCategory.MASTER, 10f, 1f);
            user.getItemCooldownManager().set(this, 10);

        }
        return super.use(world, user, hand);
    }
}
