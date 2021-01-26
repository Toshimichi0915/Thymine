package me.hp20.giz5.mixin;

import me.hp20.giz5.Giz5Mod;
import me.hp20.giz5.ToggleSprintHud;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    private final ToggleSprintHud toggleSprintHud = new ToggleSprintHud();

    @Inject(at = @At("TAIL"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;F)V")
    public void render(MatrixStack stack, float f, CallbackInfo info) {
        if (!Giz5Mod.getOptions().toggleSprintHud.isHidden() && Giz5Mod.getOptions().toggleSprint)
            toggleSprintHud.render(stack);
    }
}
