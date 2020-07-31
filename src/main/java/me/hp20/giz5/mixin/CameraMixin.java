package me.hp20.giz5.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(Camera.class)
public class CameraMixin {

    @Shadow
    private Entity focusedEntity;

    @Shadow
    private float lastCameraY;

    @Shadow
    private float cameraY;

    /**
     * @author hp20
     * @reason Updates eye height instantly
     */
    @Overwrite
    public void updateEyeHeight() {
        if (this.focusedEntity != null) {
            lastCameraY = cameraY;
            switch (focusedEntity.getPose()) {
                case CROUCHING:
                    cameraY = 1.27F;
                    break;
                case SWIMMING:
                case FALL_FLYING:
                case SPIN_ATTACK:
                    cameraY = 0.4F;
                    break;
                case SLEEPING:
                    cameraY = 0.2F;
                    break;
                default:
                    cameraY = 1.62F;
            }
        }
    }
}
