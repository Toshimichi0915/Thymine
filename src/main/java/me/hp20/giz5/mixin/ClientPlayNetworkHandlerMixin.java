package me.hp20.giz5.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin implements ClientPlayPacketListener {

    @Shadow
    private ClientWorld world;

    @Shadow
    private MinecraftClient client;

    /**
     * @author hp20
     * @reason discords pose related packets of the player sent by a server
     */
    @Overwrite
    public void onEntityTrackerUpdate(EntityTrackerUpdateS2CPacket packet) {
        NetworkThreadUtils.forceMainThread(packet, this, client);
        Entity entity = this.world.getEntityById(packet.id());
        if (entity != null && entity.equals(client.player))
            packet.getTrackedValues().removeIf(p -> p.getData().getType().equals(TrackedDataHandlerRegistry.ENTITY_POSE));

        if (entity != null && packet.getTrackedValues() != null) {
            entity.getDataTracker().writeUpdatedEntries(packet.getTrackedValues());
        }

    }
}
