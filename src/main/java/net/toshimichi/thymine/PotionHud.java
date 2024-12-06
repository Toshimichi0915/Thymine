package net.toshimichi.thymine;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.profiler.Profilers;

public class PotionHud {

    public void render(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        Profilers.get().push("potionHud");
        TextRenderer renderer = client.textRenderer;

        float x = ThymineMod.getOptions().potionHudOptions.getX();
        float y = ThymineMod.getOptions().potionHudOptions.getY();
        int index = 0;
        for (StatusEffectInstance effect : client.player.getStatusEffects()) {
            if (effect.isInfinite()) continue;

            int seconds = effect.getDuration() / 20;
            int minutes = seconds / 60;
            if (minutes > 60) continue;

            String text = String.format("%02d:%02d", minutes, seconds % 60);
            Sprite sprite = client.getStatusEffectSpriteManager().getSprite(effect.getEffectType());
            context.drawSpriteStretched(RenderLayer::getGuiTextured, sprite, (int) x, (int) y + (index * 25), 18, 18, ColorHelper.getWhite(1));
            context.drawTextWithShadow(renderer, text, (int) (x + 21), (int) (y + 5 + (index * 25)),
                    ThymineMod.getOptions().potionHudOptions.color);
            if (effect.getAmplifier() > 0) {
                context.drawTextWithShadow(renderer, Integer.toString(effect.getAmplifier() + 1), (int) (x + 13), (int) (y + 11 + (index * 25)),
                        ThymineMod.getOptions().potionHudOptions.color);
            }
            index++;
        }
        Profilers.get().pop();
    }
}
