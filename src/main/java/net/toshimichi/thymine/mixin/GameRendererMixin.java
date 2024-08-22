package net.toshimichi.thymine.mixin;

import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.render.GameRenderer;
import net.toshimichi.thymine.ThymineMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
abstract public class GameRendererMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/SimpleOption;getValue()Ljava/lang/Object;", ordinal = 0), method = "renderWorld", require = 0)
    public Object bobView(SimpleOption<Boolean> instance) {
        return !ThymineMod.getOptions().noScreenBobbing;
    }
}
