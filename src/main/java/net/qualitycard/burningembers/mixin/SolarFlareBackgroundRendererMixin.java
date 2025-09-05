package net.qualitycard.burningembers.mixin;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.List;

@Mixin(BackgroundRenderer.class)
public class SolarFlareBackgroundRendererMixin {
    @Shadow
    private static float red;
    @Shadow
    private static float green;
    @Shadow
    private static float blue;
    @Shadow
    private static long lastWaterFogColorUpdateTime = -1L;

    @Inject( method = {"render"}, at = {@At("TAIL")})
    private static void burningembers$renderSolarFlareFog(Camera camera, float tickDelta, ClientWorld world, int viewDistance, float skyDarkness, CallbackInfo ci) {
        Entity entity = camera.getFocusedEntity();
        red = 0.5f;
        green = 0.1f;
        blue = 0.15f;
        lastWaterFogColorUpdateTime = -1L;
        RenderSystem.clearColor(red, green, blue, 0.0F);
    }

}
