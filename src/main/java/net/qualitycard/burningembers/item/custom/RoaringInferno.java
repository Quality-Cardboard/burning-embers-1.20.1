package net.qualitycard.burningembers.item.custom;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.qualitycard.burningembers.BurningEmbers;
import net.qualitycard.burningembers.effect.ModEffects;
import net.qualitycard.burningembers.lodestone.packets.ModPackets;
import net.qualitycard.burningembers.lodestone.packets.ParticleSpawnPacket;

import java.awt.*;

public class RoaringInferno extends SwordItem {
    public RoaringInferno(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    public static int hits = 0;

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
            World world = player.getWorld();
            if (!world.isClient()) {

                // Activation code
                hits += 1;
                if (hits == 1) {
                    stack.getOrCreateNbt().putBoolean("hit_1", true);
                    stack.getOrCreateNbt().putBoolean("hit_2", false);
                    stack.getOrCreateNbt().putBoolean("hit_3", false);
                } if (hits == 2) {
                    stack.getOrCreateNbt().putBoolean("hit_1", false);
                    stack.getOrCreateNbt().putBoolean("hit_2", true);
                    stack.getOrCreateNbt().putBoolean("hit_3", false);
                } if (hits == 3) {
                    stack.getOrCreateNbt().putBoolean("hit_1", false);
                    stack.getOrCreateNbt().putBoolean("hit_2", false);
                    stack.getOrCreateNbt().putBoolean("hit_3", true);
                } if (hits > 3 && entity instanceof LivingEntity target) {
                    target.addStatusEffect(new StatusEffectInstance(ModEffects.INFERNAL.value(), 100, 0));
                }


                // Summon particles
                Vec3d originalPos = Vec3d.ofCenter(entity.getBlockPos());

                Color startingColor = new Color(255, 0, 0);
                Color endingColor = new Color(155, 0, 0);
                ParticleSpawnPacket packet = new ParticleSpawnPacket(originalPos, startingColor.getRGB(), endingColor.getRGB());
                PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                packet.toBytes(buf);

                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                ServerPlayNetworking.send(serverPlayer, ModPackets.PARTICLE_SPAWN_ID, buf);

            }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return super.getUseAction(stack);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack item = user.getMainHandStack();
        if (hits > 3) {
            item.getOrCreateNbt().putBoolean("hit_1", false);
            item.getOrCreateNbt().putBoolean("hit_2", false);
            item.getOrCreateNbt().putBoolean("hit_3", false);
            hits = 0;
        }
        return super.use(world, user, hand);
    }
}
