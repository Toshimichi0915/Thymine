package me.hp20.giz5.mixin;

import me.hp20.giz5.Giz5Mod;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
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
        if (Giz5Mod.getOptions().fastSneak) {
            if (this.focusedEntity != null) {
                lastCameraY = cameraY;
                cameraY = focusedEntity.getStandingEyeHeight();
            }
            info.cancel();
        }
    }
}
