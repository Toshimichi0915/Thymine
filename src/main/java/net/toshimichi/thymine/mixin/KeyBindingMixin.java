package net.toshimichi.thymine.mixin;

import net.minecraft.client.option.KeyBinding;
import net.toshimichi.thymine.ThymineMod;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(KeyBinding.class)
public class KeyBindingMixin {

    @Final
    @Shadow
    private String translationKey;

    @Inject(at = @At("HEAD"), method = "isPressed", cancellable = true)
    public void isPressed(CallbackInfoReturnable<Boolean> cir) {
        if (ThymineMod.getOptions().toggleSprint && translationKey.equals("key.sprint")) {
            cir.setReturnValue(ThymineMod.getOptions().sprint);
        }
    }
}
