package net.toshimichi.thymine.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.toshimichi.thymine.ThymineMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(at = @At("HEAD"), method = "hasStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z", cancellable = true)
    public void hasStatusEffect(StatusEffect effect, CallbackInfoReturnable<Boolean> info) {
        if (ThymineMod.getOptions().fullBright) {
            LivingEntity player = MinecraftClient.getInstance().player;
            if (player != null && player.equals(this) && effect == StatusEffects.NIGHT_VISION) {
                info.setReturnValue(true);
                info.cancel();
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "getStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Lnet/minecraft/entity/effect/StatusEffectInstance;", cancellable = true)
    public void getStatusEffect(StatusEffect effect, CallbackInfoReturnable<StatusEffectInstance> info) {
        if (ThymineMod.getOptions().fullBright) {
            LivingEntity player = MinecraftClient.getInstance().player;
            if (player != null && player.equals(this) && effect == StatusEffects.NIGHT_VISION) {
                info.setReturnValue(new StatusEffectInstance(StatusEffects.NIGHT_VISION, Integer.MAX_VALUE));
                info.cancel();
            }
        }
    }

}
