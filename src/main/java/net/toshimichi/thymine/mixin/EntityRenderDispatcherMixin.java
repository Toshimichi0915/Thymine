package net.toshimichi.thymine.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexRendering;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.toshimichi.thymine.ThymineMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderer;render(Lnet/minecraft/client/render/entity/state/EntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"), method = "render(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/EntityRenderer;)V")
    public void render(Entity entity, double x, double y, double z, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, EntityRenderer<? extends Entity, ? extends EntityRenderState> renderer, CallbackInfo ci) {
        if (ThymineMod.getOptions().showHitBox && !entity.isInvisible() && !(entity instanceof ClientPlayerEntity)) {
            Box box = entity.getBoundingBox().offset(-entity.getX(), -entity.getY(), -entity.getZ());
            VertexRendering.drawBox(matrices, vertexConsumers.getBuffer(RenderLayer.getLines()), box, 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}
