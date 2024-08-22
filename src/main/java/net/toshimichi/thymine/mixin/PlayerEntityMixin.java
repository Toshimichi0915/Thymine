package net.toshimichi.thymine.mixin;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.toshimichi.thymine.ThymineMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("RETURN"), method = "getBaseDimensions", cancellable = true)
    public void getBaseDimensions(EntityPose pose, CallbackInfoReturnable<EntityDimensions> cir) {
        if (ThymineMod.getOptions().softSneak && pose == EntityPose.CROUCHING) {
            cir.setReturnValue(cir.getReturnValue().withEyeHeight(1.4F));
        }
    }
}
