package me.hp20.giz5.mixin;

import me.hp20.giz5.Giz5Mod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow
    private EntityType<?> type;

    @Shadow
    private DataTracker dataTracker;
    @Shadow
    private static TrackedData<EntityPose> POSE;

    @Inject(at = @At("HEAD"), method = "getPose()Lnet/minecraft/entity/EntityPose;", cancellable = true)
    public void getPose(CallbackInfoReturnable<EntityPose> info) {
        if (!((Object) this instanceof PlayerEntity)) return;
        if (((Object) this).equals(MinecraftClient.getInstance().player)) return;
        if (Giz5Mod.getOptions().antiSwim &&
                type == EntityType.PLAYER) {
            EntityPose pose = dataTracker.get(POSE);
            if (pose == EntityPose.SWIMMING)
                pose = EntityPose.STANDING;
            info.setReturnValue(pose);
            info.cancel();
        }
    }

}
