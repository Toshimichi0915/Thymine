package net.toshimichi.thymine.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.toshimichi.thymine.ThymineMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(at = @At("TAIL"), method = "<init>")
    public void init(RunArgs args, CallbackInfo ci) {
        ThymineMod.loadOptions();
    }
}
