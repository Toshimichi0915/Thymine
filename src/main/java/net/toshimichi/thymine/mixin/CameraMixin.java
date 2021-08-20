package net.toshimichi.thymine.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.toshimichi.thymine.ThymineMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public class CameraMixin {

    @Shadow
    private Entity focusedEntity;

    @Shadow
    private float lastCameraY;

    @Shadow
    private float cameraY;

    @Inject(at = @At("HEAD"), method = "updateEyeHeight()V", cancellable = true)
    public void updateEyeHeight(CallbackInfo info) {
        if (ThymineMod.getOptions().fastSneak) {
            if (this.focusedEntity != null) {
                lastCameraY = cameraY;
                cameraY = focusedEntity.getStandingEyeHeight();
            }
            info.cancel();
        }
    }
}
