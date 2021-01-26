package me.hp20.giz5.mixin;

import me.hp20.giz5.Giz5Mod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    @Shadow
    private float equipProgressMainHand;

    @Shadow
    private float equipProgressOffHand;

    @Shadow
    private float prevEquipProgressMainHand;

    @Shadow
    private float prevEquipProgressOffHand;

    @Shadow
    private ItemStack mainHand;

    @Shadow
    private ItemStack offHand;

    @Shadow
    MinecraftClient client;

    /**
     * @author hp20
     * @reason to disable equip progress
     */
    @Overwrite
    public void updateHeldItems() {
        this.prevEquipProgressMainHand = this.equipProgressMainHand;
        this.prevEquipProgressOffHand = this.equipProgressOffHand;
        ClientPlayerEntity lv = this.client.player;
        ItemStack lv2 = lv.getMainHandStack();
        ItemStack lv3 = lv.getOffHandStack();
        if (ItemStack.areEqual(this.mainHand, lv2)) {
            this.mainHand = lv2;
        }
        if (ItemStack.areEqual(this.offHand, lv3)) {
            this.offHand = lv3;
        }
        if (lv.isRiding()) {
            this.equipProgressMainHand = MathHelper.clamp(this.equipProgressMainHand - 0.4f, 0.0f, 1.0f);
            this.equipProgressOffHand = MathHelper.clamp(this.equipProgressOffHand - 0.4f, 0.0f, 1.0f);
        } else {
            float f;
            if (Giz5Mod.getOptions().ignoreCooldown)
                f = 1;
            else
                f = lv.getAttackCooldownProgress(1.0f);
            this.equipProgressMainHand += MathHelper.clamp((this.mainHand == lv2 ? f * f * f : 0.0f) - this.equipProgressMainHand, -0.4f, 0.4f);
            this.equipProgressOffHand += MathHelper.clamp((float) (this.offHand == lv3 ? 1 : 0) - this.equipProgressOffHand, -0.4f, 0.4f);
        }
        if (this.equipProgressMainHand < 0.1f) {
            this.mainHand = lv2;
        }
        if (this.equipProgressOffHand < 0.1f) {
            this.offHand = lv3;
        }
    }
}
