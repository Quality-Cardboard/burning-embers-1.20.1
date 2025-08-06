package net.qualitycard.burningembers.item.custom;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
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
                if (entity instanceof LivingEntity livingEntity && stack.getOrCreateNbt().getBoolean("activated")) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.INFERNAL.value(), 100, 0));
                }

                // Activation code
                hits += 1;
                if (hits > 4) {
                    stack.getOrCreateNbt().putBoolean("activated", true);
                    hits = 0;
                } else {
                    stack.getOrCreateNbt().putBoolean("activated", false);
                }


                // Summon particles
                Vec3d originalPos = Vec3d.ofCenter(entity.getBlockPos());

                Color startingColor = new Color(154, 0, 0);
                Color endingColor = new Color(20, 32, 255);
                ParticleSpawnPacket packet = new ParticleSpawnPacket(originalPos, startingColor.getRGB(), endingColor.getRGB());
                PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                packet.toBytes(buf);

                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                ServerPlayNetworking.send(serverPlayer, ModPackets.PARTICLE_SPAWN_ID, buf);
                serverPlayer.sendMessage(Text.of("Particle has spawned"), true);

            }
        return super.onLeftClickEntity(stack, player, entity);
    }
}
