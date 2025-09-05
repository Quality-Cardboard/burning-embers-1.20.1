package net.qualitycard.burningembers.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.awt.*;

@Mixin (ClientWorld.class)
public class SolarFlareClientWorldMixin {

    @ModifyReturnValue(method = {"getSkyColor"}, at = {@At("RETURN")})
    public Vec3d burningembers$getSolarFlareSkyColor(Vec3d cameraPos) {
        return new Vec3d((double)255, (double)0, (double)0);
    }

    @ModifyReturnValue(method = {"getSkyBrightness"}, at = {@At("RETURN")})
    public float burningembers$getSolarFlareSkyBrightness(float tickDelta) {
        return 500.0F;
    }
}
