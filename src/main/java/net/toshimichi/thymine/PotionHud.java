package net.toshimichi.thymine;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.Sprite;
import net.minecraft.entity.effect.StatusEffectInstance;

public class PotionHud {

    public void render(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.getProfiler().push("potionHud");
        TextRenderer renderer = client.textRenderer;

        float x = ThymineMod.getOptions().potionHudOptions.getX();
        float y = ThymineMod.getOptions().potionHudOptions.getY();
        int index = 0;
        for (StatusEffectInstance effect : client.player.getStatusEffects()) {

            int seconds = effect.getDuration() / 20;
            int minutes = seconds / 60;
            if (minutes > 60) continue;

            String text = String.format("%02d:%02d", minutes, seconds % 60);
            Sprite sprite = client.getStatusEffectSpriteManager().getSprite(effect.getEffectType());
            RenderSystem.setShaderTexture(0, sprite.getAtlasId());
            context.drawSprite((int) x, (int) y + (index * 25), 0, 18, 18, sprite);
            context.drawTextWithShadow(renderer, text, (int) (x + 21), (int) (y + 5 + (index * 25)),
                    ThymineMod.getOptions().potionHudOptions.color);
            if (effect.getAmplifier() > 0) {
                context.drawTextWithShadow(renderer, Integer.toString(effect.getAmplifier() + 1), (int) (x + 13), (int) (y + 11 + (index * 25)),
                        ThymineMod.getOptions().potionHudOptions.color);
            }
            index++;
        }
        client.getProfiler().pop();
    }
}
