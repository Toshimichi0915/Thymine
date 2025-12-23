package net.toshimichi.thymine.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.toshimichi.thymine.ThymineMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(KeyBinding.class)
public class KeyBindingMixin {

    @Inject(at = @At("HEAD"), method = "isPressed", cancellable = true)
    public void isPressed(CallbackInfoReturnable<Boolean> cir) {
        if (ThymineMod.getOptions().toggleSprint && ThymineMod.getOptions().sprint && ((Object) this) == MinecraftClient.getInstance().options.sprintKey) {
            cir.setReturnValue(true);
        }
    }
}
