package net.toshimichi.thymine.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.toshimichi.thymine.ArmorHud;
import net.toshimichi.thymine.PotionHud;
import net.toshimichi.thymine.ThymineMod;
import net.toshimichi.thymine.ToggleSprintHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    private final ToggleSprintHud toggleSprintHud = new ToggleSprintHud();
    private final PotionHud potionHud = new PotionHud();
    private final ArmorHud armorHud = new ArmorHud();

    @Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;F)V")
    public void render(MatrixStack stack, float f, CallbackInfo info) {
        if (!ThymineMod.getOptions().toggleSprintHud.isHidden() && ThymineMod.getOptions().toggleSprint) {
            toggleSprintHud.render(stack, f);
        }
        if (ThymineMod.getOptions().potionHud) {
            potionHud.render(stack, f);
        }
        if (ThymineMod.getOptions().armorHud) {
            armorHud.render(stack, f);
        }
    }

    @Inject(at = @At("HEAD"), method = "renderStatusEffectOverlay", cancellable = true)
    public void renderStatusEffects(MatrixStack matrices, CallbackInfo ci) {
        if (ThymineMod.getOptions().noStatusOverlay) {
            ci.cancel();
        }
    }
}
