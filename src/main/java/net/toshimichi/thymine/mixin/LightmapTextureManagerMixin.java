package net.toshimichi.thymine.mixin;

import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.render.LightmapTextureManager;
import net.toshimichi.thymine.ThymineMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LightmapTextureManager.class)
public class LightmapTextureManagerMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/SimpleOption;getValue()Ljava/lang/Object;", ordinal = 2), method = "update", require = 0)
    public Object update(SimpleOption<Double> instance) {
        if (ThymineMod.getOptions().fullBright) {
            return 1000D;
        } else {
            return instance.getValue();
        }
    }
}
