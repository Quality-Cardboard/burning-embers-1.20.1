package net.qualitycard.burningembers.event;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.qualitycard.burningembers.effect.ModEffects;
import net.qualitycard.burningembers.item.ModItems;

import java.util.List;

public class BurningEmbersHudRenderer implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext context, float v) {
        MinecraftClient client = MinecraftClient.getInstance();
        float screenWidth = context.getScaledWindowWidth();
        float screenHeight = context.getScaledWindowHeight();

        // Renders Infernal Outline Screen Effect
        if (client.currentScreen != null || client.player != null) {
            if (client.player.hasStatusEffect(ModEffects.INFERNAL.value())) {
                Identifier texture = new Identifier("burningembers", "textures/screen/infernal_outline.png");
                // texture, x, y, u, v, width, height, textureWidth, textureHeight
                context.drawTexture(texture,
                        0, 0,
                        (int)(screenWidth), (int)(screenHeight),
                        0, 0,
                        (int)(screenWidth), (int)(screenHeight),
                        (int)(screenWidth), (int)(screenHeight));

            }
        }

        // Ardent Alloy Armor (Arson Meter)
        assert client.getServer() != null;
        ServerWorld serverWorld = client.getServer().getOverworld();
        List<ServerPlayerEntity> a  = serverWorld.getPlayers();
        for (PlayerEntity target : a) {
            if (target.getEquippedStack(EquipmentSlot.HEAD).getItem() == ModItems.ARDENT_MASK
                    && target.getEquippedStack(EquipmentSlot.CHEST).getItem() == ModItems.ARDENT_CHESTPLATE
                    && target.getEquippedStack(EquipmentSlot.LEGS).getItem() == ModItems.ARDENT_LEGGINGS
                    && target.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.ARDENT_BOOTS) {

                Identifier texture = new Identifier("burningembers", "textures/screen/arson_meter.png");
                // texture, x, y, u, v, width, height, textureWidth, textureHeight
                context.drawTexture(texture,
                        0, 0,
                        16, 16,
                        0, 0,
                        16, 16);
            }
        }
    }
}