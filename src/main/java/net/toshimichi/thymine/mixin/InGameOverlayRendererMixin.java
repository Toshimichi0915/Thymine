package net.toshimichi.thymine.mixin;

import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.util.math.Matrix4f;
import net.toshimichi.thymine.ThymineMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/BufferBuilder;vertex(Lnet/minecraft/util/math/Matrix4f;FFF)Lnet/minecraft/client/render/VertexConsumer;"), method = "renderFireOverlay")
    private static VertexConsumer vertex(BufferBuilder bufferBuilder, Matrix4f matrix4f, float x, float y, float z) {
        float modifier = (float) -ThymineMod.getOptions().lowFire / 200;
        return bufferBuilder.vertex(matrix4f, x, y + modifier, z);
    }
}
