package net.toshimichi.thymine.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.toshimichi.thymine.ThymineMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
abstract public class GameRendererMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;bobView(Lnet/minecraft/client/util/math/MatrixStack;F)V"), method = "renderWorld")
    public void bobView(GameRenderer instance, MatrixStack matrices, float tickDelta) {
        if (!ThymineMod.getOptions().noScreenBobbing) {
            bobView(matrices, tickDelta);
        }
    }

    @Shadow
    protected abstract void bobView(MatrixStack matrices, float tickDelta);
}
