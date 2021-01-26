package me.hp20.giz5.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import me.hp20.giz5.Giz5Mod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSystem.class)
public class RenderSystemMixin {

    @Inject(at = @At("TAIL"), method = "initGameThread(Z)V")
    private static void initGameThread(CallbackInfo info) {
        Giz5Mod.loadOptions();
    }
}
