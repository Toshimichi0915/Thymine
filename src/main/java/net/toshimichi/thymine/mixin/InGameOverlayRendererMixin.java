package net.toshimichi.thymine.mixin;

import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.toshimichi.thymine.ThymineMod;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/VertexConsumer;vertex(Lorg/joml/Matrix4f;FFF)Lnet/minecraft/client/render/VertexConsumer;"), method = "renderFireOverlay")
    private static VertexConsumer vertex(VertexConsumer instance, Matrix4f matrix, float x, float y, float z) {
        float modifier = (float) -ThymineMod.getOptions().lowFire / 2;
        return instance.vertex(matrix, x, y + modifier, z);
    }
}
