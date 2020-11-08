package me.hp20.giz5.mixin;

import me.hp20.giz5.Giz5Mod;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StatusEffectInstance.class)
public class StatusEffectInstanceMixin {

    @Inject(at = @At("HEAD"), method = "shouldShowIcon()Z", cancellable = true)
    public void shouldShowIcon(CallbackInfoReturnable<Boolean> info) {
        if (Giz5Mod.getOptions().forceIcon) {
            info.setReturnValue(true);
            info.cancel();
        }
    }
}
