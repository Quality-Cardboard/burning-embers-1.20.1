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
        List<ServerPlayerEntity> players  = serverWorld.getPlayers();
        for (PlayerEntity target : players) {
            if (target.getEquippedStack(EquipmentSlot.HEAD).getItem() == ModItems.ARDENT_MASK
                    && target.getEquippedStack(EquipmentSlot.CHEST).getItem() == ModItems.ARDENT_CHESTPLATE
                    && target.getEquippedStack(EquipmentSlot.LEGS).getItem() == ModItems.ARDENT_LEGGINGS
                    && target.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.ARDENT_BOOTS) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 1, false, true));
            }
        }
    }
}
