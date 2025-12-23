package net.toshimichi.thymine.mixin;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.DrawStyle;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.ObjectAllocator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.debug.gizmo.GizmoDrawing;
import net.toshimichi.thymine.ThymineMod;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/debug/DebugRenderer;render(Lnet/minecraft/client/render/Frustum;DDDF)V"), method = "render")
    public void drawHitbox(ObjectAllocator allocator, RenderTickCounter tickCounter, boolean renderBlockOutline, Camera camera, Matrix4f positionMatrix, Matrix4f basicProjectionMatrix, Matrix4f projectionMatrix, GpuBufferSlice fogBuffer, Vector4f fogColor, boolean renderSky, CallbackInfo ci) {
        if (!ThymineMod.getOptions().showHitBox) return;
        MinecraftClient client = MinecraftClient.getInstance();

        for (Entity entity : client.world.getEntities()) {
            if (entity instanceof ClientPlayerEntity) continue;
            if (entity.isInvisible()) continue;

            float tickProgress = tickCounter.getTickProgress(false);
            Vec3d entityPos = entity.getEntityPos();
            Vec3d lerpedPos = entity.getLerpedPos(tickProgress);
            Vec3d offset = lerpedPos.subtract(entityPos);

            GizmoDrawing.box(entity.getBoundingBox().offset(offset), DrawStyle.stroked(-1));
        }
    }
}
