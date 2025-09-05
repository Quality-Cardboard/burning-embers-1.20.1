package net.qualitycard.burningembers.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.qualitycard.burningembers.item.ModItems;

import java.util.List;

public class BurningEmbersServerTick implements ServerTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftServer minecraftServer) {
        ServerWorld serverWorld = minecraftServer.getOverworld();
        List<ServerPlayerEntity> a  = serverWorld.getPlayers();
        for (PlayerEntity target : a) {
            if (target.getEquippedStack(EquipmentSlot.HEAD).getItem() == ModItems.MECHANICAL_FIRE_MASK
                    && target.getEquippedStack(EquipmentSlot.CHEST).getItem() == ModItems.MECHANICAL_FIRE_CHESTPLATE
                    && target.getEquippedStack(EquipmentSlot.LEGS).getItem() == ModItems.MECHANICAL_FIRE_LEGGINGS
                    && target.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.MECHANICAL_FIRE_BOOTS) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 2));
            }
        }
    }
}
