package net.qualitycard.burningembers.event;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import net.qualitycard.burningembers.BurningEmbers;
import net.qualitycard.burningembers.effect.ModEffects;

public class InfernalHudRendererCallback implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext context, float v) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.currentScreen != null || client.player != null) {
            if (client.player.hasStatusEffect(ModEffects.INFERNAL.value())) {
                int alpha = 128;  //  This will make the texture 50% opaque (128/255 = 0.5)
                int red = 255; // Red component of the color (if you want to tint the texture)
                int green = 255; // Green component of the color
                int blue = 255; // Blue component of the color
                int color = (alpha << 24) | (red << 16) | (green << 8) | blue;
                Identifier texture = new Identifier("burningembers", "textures/screen/infernal_outline.png");
                // texture, x, y, u, v, width, height, textureWidth, textureHeight

                float screenWidth = context.getScaledWindowWidth();
                float screenHeight = context.getScaledWindowHeight();
                context.drawTexture(texture,
                        0, 0,
                        (int)(screenWidth), (int)(screenHeight),
                        0, 0,
                        (int)(screenWidth), (int)(screenHeight),
                        (int)(screenWidth), (int)(screenHeight));

            }
        }
    }
}
