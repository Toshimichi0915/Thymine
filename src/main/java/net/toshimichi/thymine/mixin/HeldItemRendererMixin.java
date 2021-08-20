package net.toshimichi.thymine.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.toshimichi.thymine.ThymineMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getAttackCooldownProgress(F)F"),
            method = "updateHeldItems")
    public float getAttackCooldownProgress(ClientPlayerEntity entity, float baseTime) {
        if (ThymineMod.getOptions().ignoreCooldown)
            return 1;
        else
            return entity.getAttackCooldownProgress(baseTime);
    }

}
