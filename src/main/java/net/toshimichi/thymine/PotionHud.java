package net.toshimichi.thymine;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.profiler.Profilers;

public class PotionHud {

    public void render(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        Profilers.get().push("potionHud");
        TextRenderer renderer = client.textRenderer;

        int x = ThymineMod.getOptions().potionHudOptions.getX();
        int y = ThymineMod.getOptions().potionHudOptions.getY();
        int index = 0;
        for (StatusEffectInstance effect : client.player.getStatusEffects()) {
            if (effect.isInfinite()) continue;

            int seconds = effect.getDuration() / 20;
            int minutes = seconds / 60;
            if (minutes > 60) continue;

            String text = String.format("%02d:%02d", minutes, seconds % 60);
            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, InGameHud.getEffectTexture(effect.getEffectType()), x + 7, y + 7 + (index * 25), 18, 18);
            context.drawTextWithShadow(renderer, text, x + 28, y + 12 + (index * 25),
                    ThymineMod.getOptions().potionHudOptions.color);
            if (effect.getAmplifier() > 0) {
                context.drawTextWithShadow(renderer, Integer.toString(effect.getAmplifier() + 1), x + 20, y + 18 + (index * 25),
                        ThymineMod.getOptions().potionHudOptions.color);
            }
            index++;
        }
        Profilers.get().pop();
    }
}
