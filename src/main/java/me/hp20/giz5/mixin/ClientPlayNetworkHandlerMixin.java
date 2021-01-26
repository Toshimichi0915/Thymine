package me.hp20.giz5.mixin;

import me.hp20.giz5.Giz5Mod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin implements ClientPlayPacketListener {

    @Shadow
    private ClientWorld world;

    @Shadow
    private MinecraftClient client;

    @Inject(at = @At("HEAD"), method = "onEntityTrackerUpdate(Lnet/minecraft/network/packet/s2c/play/EntityTrackerUpdateS2CPacket;)V", cancellable = true)
    public void onEntityTrackerUpdate(EntityTrackerUpdateS2CPacket packet, CallbackInfo info) {
        if (Giz5Mod.getOptions().shiftFix) {
            NetworkThreadUtils.forceMainThread(packet, this, client);
            Entity entity = this.world.getEntityById(packet.id());
            if (entity != null && packet.getTrackedValues() != null) {
                if (entity.equals(client.player))
                    packet.getTrackedValues().removeIf(p -> p.getData().getType().equals(TrackedDataHandlerRegistry.ENTITY_POSE));
                entity.getDataTracker().writeUpdatedEntries(packet.getTrackedValues());
            }
            info.cancel();
        }
    }
}
