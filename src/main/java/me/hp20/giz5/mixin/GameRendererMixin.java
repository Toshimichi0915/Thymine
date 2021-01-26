package me.hp20.giz5.mixin;

import me.hp20.giz5.Giz5Mod;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(at = @At("HEAD"), method = "bobViewWhenHurt(Lnet/minecraft/client/util/math/MatrixStack;F)V", cancellable = true)
    public void bobViewWhenHurt(MatrixStack arg, float f, CallbackInfo info) {
        if (Giz5Mod.getOptions().noHurtBobbing)
            info.cancel();
    }

}
