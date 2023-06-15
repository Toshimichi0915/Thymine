package net.toshimichi.thymine.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
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

    @Inject(at = @At("TAIL"), method = "render")
    public void render(DrawContext context, float tickDelta, CallbackInfo ci) {
        if (!ThymineMod.getOptions().toggleSprintHud.isHidden() && ThymineMod.getOptions().toggleSprint) {
            toggleSprintHud.render(context);
        }
        if (ThymineMod.getOptions().potionHud) {
            potionHud.render(context);
        }
        if (ThymineMod.getOptions().armorHud) {
            armorHud.render(context);
        }
    }

    @Inject(at = @At("HEAD"), method = "renderStatusEffectOverlay", cancellable = true)
    public void renderStatusEffects(DrawContext context, CallbackInfo ci) {
        if (ThymineMod.getOptions().noStatusOverlay) {
            ci.cancel();
        }
    }
}
